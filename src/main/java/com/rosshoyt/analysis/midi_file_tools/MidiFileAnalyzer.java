package com.rosshoyt.analysis.midi_file_tools;




import com.rosshoyt.analysis.midi_file_tools.kaitai.StandardMidiFile;

import com.rosshoyt.analysis.model.MusicalAnalysis;
import com.rosshoyt.analysis.model.RawAnalysis;

import java.util.ArrayList;


/**
 * High level file analyzer.
 * Works with one uploaded .mid/.midi file to extract useful information and return
 *
 * SQL database-persistable analysis data
 */

public class MidiFileAnalyzer {
   

   private MidiFileValidator midiFileValidator;
   /**
    *
    */
   public MidiFileAnalyzer() {

      this.midiFileValidator = new MidiFileValidator();
   }

   public static RawAnalysis analyzeRaw(StandardMidiFile smf) {
      RawAnalysis raw = new RawAnalysis();
      raw.setMidiFileFormatType(smf.hdr().format());
      raw.setNumTracks(smf.hdr().numTracks());
      raw.setDivisionType(smf.hdr().division());


      raw.setNumMidiMessages(countNumMidiEvents(smf.tracks()));
      return raw;
   }
   private static int countNumMidiEvents(ArrayList<StandardMidiFile.Track> tracks){
      int numMidiEvents = 0;
      for(StandardMidiFile.Track track : tracks){
         numMidiEvents += track.events().event().size();
      }
      System.out.println(numMidiEvents + " total Midi events");
      return numMidiEvents;
   }

   public static MusicalAnalysis analyzeMusic(StandardMidiFile smf) {
      MusicalAnalysis mus = new MusicalAnalysis();

      System.out.println("ANALYZING SMF");

      if(smf.hdr().format() != 2) {
         int trackCounter = 0;
         int totalNoteCount = 0;
         for(StandardMidiFile.Track track : smf.tracks()){
            System.out.println("ANALYZING TRACK #" + trackCounter);//: " + event.eventType());

            //StandardMidiFile.TrackEvents trackEvents = track.events();
            //System.out.println("TrackEvents raw: " + trackEvents.);
            int trackEventCounter = 0;
            for(StandardMidiFile.TrackEvent event : track.events().event()){
               switch (event.eventType()) {
                  case 224:
                     //StandardMidiFile.NoteOnEvent noteOnEvent = new StandardMidiFile.NoteOnEvent(event._io(), event);
                     System.out.println("Trc#" + trackCounter /*+ " @" + event.vTime().value()*/ + " TrcEvent# " + trackEventCounter + " NOTE_ON");//[note = " + noteOnEvent.note() + ", vel = " + noteOnEvent.velocity() + "]");
                     totalNoteCount++;
                     //StandardMidiFile.NoteOffEvent noteOnEvent = event(StandardMidiFile.NoteOnEvent);
                     //System.out.println("Track #" + trackCounter +" TrackEvent# " + trackEventCounter + " NOTE_ON [note = " + noteOnEvent.note() + ", vel = " + noteOnEvent.velocity() + "]");
                     break;
                  case 128:
                     System.out.println("Trc#" + trackCounter /*+ " @" + event.vTime().value() */+ " TrcEvent# " + trackEventCounter + " NOTE_OFF");//[note = " + noteOnEvent.note() + ", vel = " + noteOnEvent.velocity() + "]");

                     //StandardMidiFile.NoteOffEvent noteOffEvent = new StandardMidiFile.NoteOffEvent(event._io(), event, smf);
                     //System.out.println("Track #" + trackCounter +" TrackEvent# " + trackEventCounter + " NOTE_ON [note = " + noteOffEvent.note() + ", vel = " + noteOffEvent.velocity() + "]");
                     break;
               }

               trackEventCounter++;
            }

            trackCounter++;
         }
         mus.setTotalNotes(totalNoteCount);
      }
      return mus;
   }
//   public enum TrackEventType{
//      PitchBendEvent(224),
//      NoteOnEvent(144),
//      ChannelPressureEvent(208),
//      ProgramChangeEvent(192),
//      PolyphonicPressureEvent(160),
//      ControllerEvent(176),
//      NoteOffEvent(128);
//
//      final int value;
//      TrackEventType(int value) {
//         this.value = value;
//      }
//
//      public int getValue(){
//         return this.value;
//      }
//
//   }

//   public static MidiFileAnalysis addFileData(MidiFileAnalysis mfa, ParseResult parseResult){
//
//      return mfa;
//   }


//   private static MidiFileAnalysis addRawFileToMFA(MidiFileAnalysis mfa, Fidatale file){
//      byte[] data;
//      try{
//         data = FileUtils.getByteArray(file);
//      } catch(Exception e) {
//         e.printStackTrace();
//         return null;
//      }
//      MidiFile midiFile = new MidiFile();
//      midiFile.setId(mfa.getId());
//      midiFile.setData(data);
//      midiFile.setFileName(file.getName());
//      midiFile.setFileType(FileUtils.getExtension(file));
//      mfa.setMidiFile(midiFile);
//      return mfa;
//   }



//   public static MidiFile getPersistableMidiFile(){
//      // TODO refactor -> find new location for this functionality
//      System.out.println("...Adding Binary File Data to the MidiFileAnalysis object for DB storage...");
//
//      MidiFile midiFile = new MidiFile();
//
//      midiFileAnalysis.setMidiFile(midiFile);
//
//
//   }



}
