package com.rosshoyt.analysis.midifile.tools;




import com.rosshoyt.analysis.midifile.tools.kaitai.StandardMidiFile;

import com.rosshoyt.analysis.model.file.MidiFileDetail;
import com.rosshoyt.analysis.model.musical.MusicalAnalysis;
import com.rosshoyt.analysis.model.musical.SustainPedal;
import com.rosshoyt.analysis.model.raw.OldRawAnalysis;
import com.rosshoyt.analysis.model.raw.SusPedalOff;
import com.rosshoyt.analysis.model.raw.SusPedalOn;

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

   public MidiFileDetail getMidiFileDetail(StandardMidiFile smf, String fileName, String extension) {
      MidiFileDetail mfd = new MidiFileDetail();
      mfd.setMidiFileFormatType(smf.hdr().format());
      mfd.setNumTracks(smf.hdr().numTracks());
      mfd.setDivisionType(smf.hdr().division());
      mfd.setFileName(fileName);
      mfd.setFileExtension(extension);
      mfd.setFullFileName(fileName + "." + extension);
      return mfd;
   }

   public static OldRawAnalysis analyzeRaw(StandardMidiFile smf) {
      System.out.println("ANALYZING SMF - RAW");

      OldRawAnalysis raw = new OldRawAnalysis();


      raw.setNumMidiMessages(countNumMidiEvents(smf.tracks()));


      // TODO remove the original parse loop from analyzeMusic()
      if (smf.hdr().format() != 2) {

         int trackNumber = 0;
         int totalNoteCount = 0;
         for (StandardMidiFile.Track smfTrack : smf.tracks()) {
            System.out.println("...Analyzing midi track #" + trackNumber + "... ");
            int trackEventCounter = 0;
            for (StandardMidiFile.TrackEvent event : smfTrack.events().event()) {
               switch (event.eventType()) {
//                  case 224: {
//                     StandardMidiFile.PitchBendEvent pitchBendEvent = (StandardMidiFile.PitchBendEvent)event.eventBody();
//                     // TODO Support pitchbend influenced 'effectivePitch' field in model.music.Note
//                     break;
//                  }
                  case 144: {
                     //StandardMidiFile.NoteOnEvent noteOnEvent = new StandardMidiFile.NoteOnEvent(event.eventBody()._io(), event, smf);
                     StandardMidiFile.NoteOnEvent noteOnEvent = (StandardMidiFile.NoteOnEvent) event.eventBody();
                     System.out.println("Trc#" + trackNumber + " @" + event.vTime().value() + " TrcEvent# " + trackEventCounter + " NOTE_ON" + "[note = " + noteOnEvent.note() + ", vel = " + noteOnEvent.velocity() + "]");
                     totalNoteCount++;
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
                  case 176: {
                     StandardMidiFile.ControllerEvent controllerEvent = (StandardMidiFile.ControllerEvent) event.eventBody();
                     System.out.println("Controller Event #" + controllerEvent.controller());
                     switch(controllerEvent.controller()){
                        case 64: {
                           // Sustain Pedal
                           SustainPedal sustainPedal;
                           if (controllerEvent.value() > 64) { // TODO doublecheck value of suspedal controller event message on vs off
                              SusPedalOn susPedalOn = new SusPedalOn();
                           } else {
                              SusPedalOff susPedalOff = new SusPedalOff();
                           }
                        }
                        // TODO Support MidiController parsing values available @https://www.mixagesoftware.com/en/midikit/help/HTML/controllers.html
                     }
                     break;
                  }
                  case 128: {
                     StandardMidiFile.NoteOnEvent noteOffEvent = (StandardMidiFile.NoteOnEvent) event.eventBody();
                     System.out.println("Trc#" + trackNumber /*+ " @" + event.vTime().value() */ + " TrcEvent# " + trackEventCounter + " NOTE_OFF");//[note = " + noteOnEvent.note() + ", vel = " + noteOnEvent.velocity() + "]");
                     break;
                  }
               }

               trackEventCounter++;
            }

            trackNumber++;
         }



      }


      return raw;
   }


   public static MusicalAnalysis analyzeMusic(OldRawAnalysis oldRawAnalysis) {
      MusicalAnalysis mus = new MusicalAnalysis();

      System.out.println("TODO - MUSICAL ANALYSIS");


      //mus.setTotalNotes(totalNoteCount);

      return mus;
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
