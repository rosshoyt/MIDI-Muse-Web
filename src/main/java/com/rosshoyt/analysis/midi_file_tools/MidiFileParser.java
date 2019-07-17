package com.rosshoyt.analysis.midi_file_tools;


import com.rosshoyt.analysis.midi_file_tools.exceptions.InvalidMidiFileException;
import com.rosshoyt.analysis.midi_file_tools.kaitai.StandardMidiFile;
import com.rosshoyt.analysis.model.MidiFile;
import com.rosshoyt.analysis.model.MidiHeaderProperties;
import com.rosshoyt.analysis.model.RawAnalysis;

import com.rosshoyt.analysis.utils.MidiFileUtils;
import io.kaitai.struct.ByteBufferKaitaiStream;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.sound.midi.*;
import java.io.*;
import java.util.Date;
import java.util.Map;


/**
 *
 */
public interface MidiFileParser {
   public StandardMidiFile parse(MultipartFile multipartFile) throws IOException;
   public StandardMidiFile parse(File file) throws IOException;
   public StandardMidiFile parse(byte[] data);
}

/*
   //OLD CODE
   public RawAnalysis parseMidi(File midiFile) throws InvalidMidiDataException, IOException {
      RawAnalysis analysis = new RawAnalysis();

      MidiFileFormat midiFileFormat = MidiSystem.getMidiFileFormat(midiFile);

      analysis.setMidiFileFormatType(midiFileFormat.getType());
      // TODO manually parse header to get numTracks here (in order of appearence in MThd)
      analysis.midiFileFormat.getDivisionType();


      //

      //SortedMidiFileData sortedMidiFileData;
      //this.midiFileFormat = MidiSystem.getMidiFileFormat(midiFile);
      Sequence sequence = MidiSystem.getSequence(midiFile);
      analysis.setNumTracks(midiFileFormat.);
      this.tracks = sequence.getTracks();

      //main MIDI analysis container - (global)
      sortedMidiFileData = new SortedMidiFileData(tracks.length);
      //DEBUG
      System.out.println("Parsing Midi midiFile..." +
            "\nMidi Header (MidiHeaderProperties) Properties:" + midiFileFormat.properties()
      );
      //Extract data from MidiFileFormat object
      sortedMidiFileData.setHeader(getHeaderData(this.midiFileFormat));


      */
/**
       * Begin parse with information obtained in Header
       * TODO custom header parsing
       *//*

      //analysis.setDivisionType(sequence.getDivisionType());
      //analysis.setResolution(sequence.getResolution());
      //ShortMessageTrackAnalysis[] shortMessageAnalyses = new ShortMessageTrackAnalysis[tracks.length];
      //MetaMessageTrackAnalysis[] metaMessageAnalyses = new MetaMessageTrackAnalysis[tracks.length];
      //analysis.setNumTracks(tracks.length);
      int trackNumber = 0;
      for(Track track : tracks){
         System.out.println("Starting parse of Track # " + trackNumber + ". " + track.size() + " events in track");
         //ShortMessageHandler smHandler = new ShortMessageHandler(this.sequence, trackNumber);
         //MetaMessageHandler mmHandler = new MetaMessageHandler(this.sequence);
         for(int i = 0; i < track.size(); i++){
            MidiEvent midiEvent = track.get(i);

            long eventTick = midiEvent.getTick();
            MidiMessage midiMessage = midiEvent.getMessage();

            if(midiMessage instanceof ShortMessage){
               //smHandler.addSM((ShortMessage)midiMessage, eventTick, trackNumber);
               handleShortMessage((ShortMessage)midiMessage, midiEvent, eventTick, trackNumber);
            } else if(midiMessage instanceof MetaMessage){
               //mmHandler.addMM((MetaMessage)midiMessage, eventTick, trackNumber);
               //TODO: add sys-ex message handling
            } else if(midiMessage instanceof SysexMessage){
               System.out.println("@" + eventTick + " Sysex Message (usupported)");
            }
         }
         //trackNumber
         //shortMessageAnalyses[trackNumber] = smHandler.getAnalysis();
         //metaMessageAnalyses[trackNumber] = mmHandler.getAnalysis();
         trackNumber++;
      }
      //analysis.setShortMessageTrackAnalyses(shortMessageAnalyses);
      //analysis.setMetaMessageTrackAnalyses(metaMessageAnalyses);

      return sortedMidiFileData;
   }

   public MidiHeaderProperties getHeaderData(MidiFileFormat midiFileFormat) {
      Map<String, Object> properties = midiFileFormat.properties();
      MidiHeaderProperties headerData = new MidiHeaderProperties();

      headerData.setMidiFileType(midiFileFormat.getType());

      //Extract Optional Header Properties
      for (String key : properties.keySet()) {
         switch (key) {
            case "title":
               headerData.setTitle(properties.get(key).toString());
               break;
            case "author":
               headerData.setAuthor(properties.get(key).toString());
               break;
            case "date":
               headerData.setDate((Date)properties.get(key));
               break;
            case "copyright":
               headerData.setCopyrightInfo(properties.get(key).toString());
               break;
            case "comment":
               headerData.setComment(properties.get(key).toString());
            default:
               break;
         }
      }
      return headerData;
   }


      //for(int i = 0; i < Math.max(properties.size(), )) {
      //}


   private void handleShortMessage(ShortMessage shortMessage, MidiEvent midiEvent, long tick, int trackNumber) {
      int channel = shortMessage.getChannel();
      int data1 = shortMessage.getData1();
      int data2 = shortMessage.getData2();
      switch (shortMessage.getCommand()) {
         case ShortMessage.NOTE_ON:

            System.out.println("#DEBUG @" + MidiTimeUtils.microsec2hours_mins_secs_milli(MidiTimeUtils.tick2microsecond(this.sequence, tick))
                  + "||Track#" + trackNumber + " SM NoteOn = " + MusicTools.getNoteName(data1) + " | ");
            sortedMidiFileData.addNoteOn(midiEvent, trackNumber);

            //shortMessageTrackAnalysis.addNoteOn(trackNumber, shortMessage);
            //processNoteOn(channel, tick, shortMessage);
            break;

         case ShortMessage.NOTE_OFF:
            System.out.println("#DEBUG @" + MidiTimeUtils.microsec2hours_mins_secs_milli(MidiTimeUtils.tick2microsecond(this.sequence, tick))
                  + "||Track# " + trackNumber + " SM NoteOff = " + MusicTools.getNoteName(trackNumber) + " | ");
            sortedMidiFileData.addNoteOff(midiEvent, trackNumber);
            //shortMessageTrackAnalysis.addNoteOff(trackNumber, shortMessage);
            //processNoteOff(channel, tick, shortMessage);
            break;
         case ShortMessage.CONTROL_CHANGE:
            addControlChange(shortMessage, midiEvent, tick, channel);
            break;
         default:
            break;

      }
   }
   private void addControlChange(ShortMessage shortMessage, MidiEvent midiEvent, long tick, int trackNumber) {
      switch(shortMessage.getData1()){
         //TODO create ShortMessage ControlChange ENUM reference
         case 64: //sustain pedal
            if(shortMessage.getData2() >= 64){
               System.out.println("@" + MidiTimeUtils.microsec2hours_mins_secs_milli(MidiTimeUtils.tick2microsecond(this.sequence, tick)) + " SM SUSTAIN PEDAL ON" );
               //shortMessageTrackAnalysis.addSustainPedal(trackNumber, shortMessage);
               //TODO shortMessage.addSustainPedal(new SustainPedalEvent())
               sortedMidiFileData.addSusPedalOn(midiEvent, trackNumber);
            } else if(shortMessage.getData2() <= 63) {
               System.out.println("@" + MidiTimeUtils.microsec2hours_mins_secs_milli(MidiTimeUtils.tick2microsecond(this.sequence, tick)) + " SM SUSTAIN PEDAL OFF" );
               //shortMessageTrackAnalysis.addSustainPedal(trackNumber, shortMessage);
               sortedMidiFileData.addSusPedalOff(midiEvent, trackNumber);
            } //TODO Determine if need to handle getData2() values <63 && >64
            //See https://www.midi.org/specifications-old/item/table-3-control-change-messages-data-bytes-2
      }
   }

*/



