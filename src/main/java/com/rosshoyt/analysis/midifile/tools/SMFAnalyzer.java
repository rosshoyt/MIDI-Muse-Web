package com.rosshoyt.analysis.midifile.tools;




import com.rosshoyt.analysis.midifile.tools.kaitai.StandardMidiFile;

import com.rosshoyt.analysis.model.file.FileByteData;
import com.rosshoyt.analysis.model.file.MidiFileDetail;
import com.rosshoyt.analysis.model.kaitai.smf.*;
import com.rosshoyt.analysis.model.kaitai.smf.meta_events.Tempo;
import com.rosshoyt.analysis.model.kaitai.smf.meta_events.TimeSignature;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOffEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOnEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events.controller_events._SustainPedalEvent;


import java.util.ArrayList;
import java.util.List;


/**
 * Middle level file analyzer.
 * Works with one uploaded .mid/.midi file to extract useful information and return
 * SQL database-persistable analysis data (RawAnalysis)
 */

public class SMFAnalyzer {


   public static MidiFileDetail getMidiFileDetail(String fileName, String extension, RawAnalysis rawAnalysis, byte[] fileData) {
      MidiFileDetail mfd = new MidiFileDetail();
      mfd.setId(rawAnalysis.getId());
      mfd.setFileName(fileName);
      mfd.setFileExtension(extension);
      mfd.setFullFileName(fileName + "." + extension);
      mfd.setFileByteData(new FileByteData(rawAnalysis.getId(), fileData));
      return mfd;
   }

   /**
    * Takes an empty RawAnalysis and analyzes KaitaiStrcut SMF
    * @param smf KaitaiStruct SMF parse result
    * @param raw Persistent entity to hold raw analysis
    * @param fkMidiFileAnalysisId id field of base database entry, the MidiFileAnalysis
    * @return raw RawAnalysis object containing analysis
    */
   public static RawAnalysis analyzeRaw(StandardMidiFile smf, Long fkMidiFileAnalysisId, RawAnalysis raw) {
      // Parse SMF Header
      raw.setId(fkMidiFileAnalysisId);
      raw.setHeader(analyzeHeader(smf));

      //raw.setNumMidiMessages(countNumMidiEvents(smf.tracks()));


      // TODO remove the original parse loop from analyzeMusic()
      //if (smf.hdr().format() != 2) {

      List<_Track> _tracks = new ArrayList<>();
      int trackNumber = 0;
      // Parse Track's events
      for (StandardMidiFile.Track smfTrack : smf.tracks()) {
         System.out.println("...Analyzing midi track #" + trackNumber + "... ");
         _Track _track = new _Track();
         _track.setTrackNumber(trackNumber);
         _track.setFkMidiFileAnalysisId(fkMidiFileAnalysisId);
         _track.setNumTrackEvents(smfTrack.events().event().size());

         Long currentTick = 0L;
         List<_TrackEvent> _trackEventContainerList = new ArrayList<>();
         for (StandardMidiFile.TrackEvent event : smfTrack.events().event()) {
            // Increment midi tick values;
            Integer vTime = event.vTime().value();
            currentTick += vTime;
            System.out.print("Track event @" + currentTick + ": ");


            // Set trackEvent to null
            _TrackEvent container = null;// = new _TrackEvent();


//            boolean eventIsSupported = true;
            if (event.eventHeader() == 255) {
               container = handleMetaEvent(event);
            } else if (event.eventHeader() == 240) {
               System.out.println("Sysex Message Event");
               //eventIsSupported = false;
               // Sysex message
            } else {
               container = handleMidiEvent(event);
            }
            //
            if(container != null) {

               container.setVTime(vTime);
               container.setTick(currentTick);
               container.setChannel(event.channel());
               container.setTrackNumber(trackNumber);
               container.setFkMidiFileAnalysisId(fkMidiFileAnalysisId);
               _trackEventContainerList.add(container);
            } else {
               System.out.print("...Unsupported Track event type.\n");
            }
         }
         _track.setTrackEventContainerList(_trackEventContainerList);
         _tracks.add(_track);
         trackNumber++;
      }
      raw.setTracks(_tracks);
      return raw;
   }

   private static _TrackEvent handleMidiEvent(StandardMidiFile.TrackEvent event) {
      switch (event.eventType()) {

         case 144: {
            System.out.print("Note On Message Event\n");
            //StandardMidiFile.NoteOnEvent noteOnEvent = new StandardMidiFile.NoteOnEvent(event.eventBody()._io(), event, smf);
            StandardMidiFile.NoteOnEvent smfNoteOnEvent = (StandardMidiFile.NoteOnEvent) event.eventBody();
            _NoteOnEvent _noteOn = new _NoteOnEvent();
            _noteOn.setNote(smfNoteOnEvent.note());
            _noteOn.setVelocity(smfNoteOnEvent.velocity());
            return _noteOn;
         }
         case 128: {
            System.out.print("Note On Message Event\n");
            StandardMidiFile.NoteOffEvent smfNoteOffEvent = (StandardMidiFile.NoteOffEvent) event.eventBody();
            _NoteOffEvent _noteOff = new _NoteOffEvent();
            _noteOff.setNote(smfNoteOffEvent.note());
            _noteOff.setVelocity(smfNoteOffEvent.velocity());
            return _noteOff;
         }
//                  case 224: {
//                     StandardMidiFile.PitchBendEvent pitchBendEvent = (StandardMidiFile.PitchBendEvent)event.eventBody();
//                     // TODO Support pitchbend influenced 'effectivePitch' field in model.music.Note
//                     break;
//                  }

         case 176: {
            StandardMidiFile.ControllerEvent controllerEvent = (StandardMidiFile.ControllerEvent) event.eventBody();
            System.out.println("Controller Event #" + controllerEvent.controller());
            switch (controllerEvent.controller()) {
               case 64: {
                  // Sustain Pedal
                  _SustainPedalEvent _sustainPedalEvent = new _SustainPedalEvent();
                  _sustainPedalEvent.setValue(controllerEvent.value());
                  return _sustainPedalEvent;
                  // TODO Support MidiController parsing values available @https://www.mixagesoftware.com/en/midikit/help/HTML/controllers.html
                  // TODO also refactor Controller Event parsing to other method
               }
               default: {
                  break;
               }
//                  case 208: {
//                     this.eventBody = new ChannelPressureEvent(this._io, this, _root);
//                     break;
//                  }
//                  case 192: {
//                     this.eventBody = new ProgramChangeEvent(this._io, this, _root);
//                     break;
//                  }
//                  case 160: {
//                     this.eventBody = new PolyphonicPressureEvent(this._io, this, _root);
//                     break;
//                  }
            }
         }
         default: {
            break;
         }
      }
      return null;
   }

   private static _TrackEvent handleMetaEvent(StandardMidiFile.TrackEvent event) {
      //System.out.println(" Meta Message Encountered"); TODO REFACTOR ENUM TO PRINT TYPE HERE (THIS LINE)
      if(event.metaEventBody() != null && event.metaEventBody().metaType() != null) {
         switch ((int)event.metaEventBody().metaType().id()) { // TODO test this line
            case 81: {
               System.out.print("Meta Message Tempo Event\n");
               return parseTempoEvent(event.metaEventBody().body());
            }
            case 88: {
               System.out.print("Meta Message Time Signature Event\n");
               return parseTimeSignatureEvent(event.metaEventBody().body());
            }
            default: {
               System.out.println("Other Meta Message Encountered");
               return null;
            }
         }
      }
      System.out.println("MetaEventBody or MetaType was null");
      return null;
   }

   private static Tempo parseTempoEvent(byte[] body) {
      // body.length (will?) be 3
      Tempo tempo = new Tempo();
      System.out.println("Parsing tempo from byte[]: " + body);
      // TODO test tempo extraction from byte[]
      int x = (body[0] & 0xff) << 16 | (body[1] & 0xff) << 8 | (body[2] & 0xff);
      System.out.println("Extracted microseconds/quarternote = " + x);
      tempo.setNumMicrosecondsPerQuarterNote(x);
      return tempo;
   }

   private static TimeSignature parseTimeSignatureEvent(byte[] body) {
      // body.length() (will?) be 4
      // TODO check for correct value, throw exception
      TimeSignature timeSignature = new TimeSignature();
      timeSignature.setNumerator(body[0]);
      timeSignature.setDenominator((int)Math.pow((double)2, body[1])); // raise 2 to the value of body[1]
      timeSignature.setMidiClocksPerMetronomeClick(body[2]);
      timeSignature.setNum32ndNotesPerBeat(body[3]);
      System.out.println("Extracted Time Signature: " + timeSignature.getNumerator() + "/" + timeSignature.getDenominator());
      System.out.println("32nd notes/beat: " + timeSignature.getNum32ndNotesPerBeat() + ", Midi Clocks per Metronome beat: "
                           + timeSignature.getMidiClocksPerMetronomeClick());
      return timeSignature;
   }

   private static _Header analyzeHeader(StandardMidiFile smf){
      _Header _Header = new _Header();
      _Header.setFormat(smf.hdr().format());
      _Header.setNumTracks(smf.hdr().numTracks());
      _Header.setDivision(smf.hdr().division());
      return _Header;
   }



   private static int countNumMidiEvents(ArrayList<StandardMidiFile.Track> tracks){
      int numMidiEvents = 0;
      for(StandardMidiFile.Track track : tracks){
         numMidiEvents += track.events().event().size();
      }
      System.out.println(numMidiEvents + " total Midi events");
      return numMidiEvents;
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


}
