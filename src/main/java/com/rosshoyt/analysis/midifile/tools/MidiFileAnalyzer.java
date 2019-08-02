package com.rosshoyt.analysis.midifile.tools;




import com.rosshoyt.analysis.midifile.tools.kaitai.StandardMidiFile;

import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.file.MidiFileDetail;
import com.rosshoyt.analysis.model.kaitai.smf.*;
import com.rosshoyt.analysis.model.kaitai.smf.meta_events.Tempo;
import com.rosshoyt.analysis.model.kaitai.smf.meta_events.TimeSignature;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOffEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOnEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events.controller_events._SustainPedalEvent;
import com.rosshoyt.analysis.model.musical.MusicalAnalysis;
import com.rosshoyt.analysis.model.musical.SustainPedal;


import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;


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
      mfd.setFileName(fileName);
      mfd.setFileExtension(extension);
      mfd.setFullFileName(fileName + "." + extension);
      return mfd;
   }

   public static RawAnalysis analyzeRaw(StandardMidiFile smf, MidiFileAnalysis mfa) {
      System.out.println("ANALYZING SMF -> RAW");
      Long fkMidiFileAnalysisId = mfa.getId(); // the global analysis key
      RawAnalysis raw = new RawAnalysis();
      raw.setId(fkMidiFileAnalysisId);

      // Parse SMF Header
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

         List<_TrackEventContainer> trackEventContainerList = new ArrayList<>();
         for (StandardMidiFile.TrackEvent event : smfTrack.events().event()) {
            Integer vTime = event.vTime().value();
            System.out.print("Track event @" + vTime + ": ");
            _TrackEventContainer container = new _TrackEventContainer();
            container.setTick(vTime);
            container.setChannel(event.channel());
            container.setTrackNumber(trackNumber);
            container.setFkMidiFileAnalysisId(fkMidiFileAnalysisId);


            if (event.eventHeader() == 255) {
               // Meta Message
               switch((int)event.metaEventBody().metaType().id()) { // TODO test this line
                  case 81: {
                     System.out.print("Meta Message Tempo Event\n");
                     //StandardMidiFile.MetaEventBody metaEventBody = (StandardMidiFile.MetaEventBody)event.metaEventBody();
                     Tempo tempo = parseTempoEvent(event.metaEventBody().body());
                     tempo.setContainer(container);
                     container.setTrackEvent(tempo);
                     trackEventContainerList.add(container);
                     break;
                  }
                  case 88: {
                     System.out.print("Meta Message Time Signature Event\n");
                     TimeSignature timeSignature = parseTimeSignatureEvent(event.metaEventBody().body());
                     timeSignature.setContainer(container);
                     container.setTrackEvent(timeSignature);
                     trackEventContainerList.add(container);
                     break;
                  }
               }
            } else if (event.eventHeader() == 240) {
               System.out.println("Sysex Message Event\n");
               // Sysex message
            } else {
               // Midi Message
               switch (event.eventType()) {
//                  case 224: {
//                     StandardMidiFile.PitchBendEvent pitchBendEvent = (StandardMidiFile.PitchBendEvent)event.eventBody();
//                     // TODO Support pitchbend influenced 'effectivePitch' field in model.music.Note
//                     break;
//                  }
                  case 144: {
                     System.out.print("Note On Message Event\n");
                     //StandardMidiFile.NoteOnEvent noteOnEvent = new StandardMidiFile.NoteOnEvent(event.eventBody()._io(), event, smf);
                     StandardMidiFile.NoteOnEvent smfNoteOnEvent = (StandardMidiFile.NoteOnEvent) event.eventBody();
                     _NoteOnEvent _noteOn = new _NoteOnEvent();
                     _noteOn.setNote(smfNoteOnEvent.note());
                     _noteOn.setVelocity(smfNoteOnEvent.velocity());
                     _noteOn.setContainer(container);
                     container.setTrackEvent(_noteOn);
                     trackEventContainerList.add(container);
                     break;
                  }
                  case 128: {
                     System.out.print("Note On Message Event\n");
                     StandardMidiFile.NoteOffEvent smfNoteOffEvent = (StandardMidiFile.NoteOffEvent) event.eventBody();
                     _NoteOffEvent _noteOff = new _NoteOffEvent();
                     _noteOff.setNote(smfNoteOffEvent.note());
                     _noteOff.setVelocity(smfNoteOffEvent.velocity());
                     _noteOff.setContainer(container);
                     container.setTrackEvent(_noteOff);
                     trackEventContainerList.add(container);
                     //System.out.println("Trc#" + trackNumber /*+ " @" + event.vTime().value() */ + " TrcEvent# " + trackEventCounter + " NOTE_OFF");//[note = " + noteOnEvent.note() + ", vel = " + noteOnEvent.velocity() + "]");
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
                     switch (controllerEvent.controller()) {
                        case 64: {
                           // Sustain Pedal
                           _SustainPedalEvent _sustainPedalEvent = new _SustainPedalEvent();
                           _sustainPedalEvent.setValue(controllerEvent.value());
                           _sustainPedalEvent.setContainer(container);
                           container.setTrackEvent(_sustainPedalEvent);
                           trackEventContainerList.add(container);
                           break;
                           /*if (controllerEvent.value() > 64) { // TODO doublecheck value of suspedal controller event message on vs off
                              _SusPedalOn susPedalOn = new SusPedalOn();
                           } else {
                              SusPedalOff susPedalOff = new SusPedalOff();
                           }*/
                        }
                        // TODO Support MidiController parsing values available @https://www.mixagesoftware.com/en/midikit/help/HTML/controllers.html
                     }
                     break;
                  }
               }
            }
         }
         _tracks.add(_track);
         trackNumber++;
      }
      raw.setTracks(_tracks);
      return raw;
   }

   private static Tempo parseTempoEvent(byte[] body) {
      // body.length (will?) be 3
      Tempo tempo = new Tempo();
      ByteBuffer wrapped = ByteBuffer.wrap(body); // big-endian by default
      tempo.setNumMicrosecondsPerQuarterNote(wrapped.getLong());
      return tempo;
   }

   private static TimeSignature parseTimeSignatureEvent(byte[] body) {
      // body.length() (will?) be 4
      // TODO check for correct value, throw exception
      TimeSignature timeSignature = new TimeSignature();
      timeSignature.setNumerator(body[0]);
      timeSignature.setDenominator((int)Math.pow((double)2, body[1])); // raise 2 to the value of body[1]
      timeSignature.setMidiClocksPerMetronomeClick(body[3]);
      timeSignature.setNum32ndNotesPerBeat(body[4]);
      return timeSignature;
   }

   private static _Header analyzeHeader(StandardMidiFile smf){
      _Header _Header = new _Header();
      _Header.setFormat(smf.hdr().format());
      _Header.setNumTracks(smf.hdr().numTracks());
      _Header.setDivision(smf.hdr().division());
      return _Header;
   }

   public static MusicalAnalysis analyzeMusic(RawAnalysis oldRawAnalysis) {
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
