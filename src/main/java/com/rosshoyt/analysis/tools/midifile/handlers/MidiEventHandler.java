package com.rosshoyt.analysis.tools.midifile.handlers;


import com.rosshoyt.analysis.model.internal.RawAnalysisStatisticsContainer;
import com.rosshoyt.analysis.tools.midifile.parsing.kaitai.StandardMidiFile;
import com.rosshoyt.analysis.model.raw.abstractions.RawTrackEvent;
import com.rosshoyt.analysis.model.raw.midi_events.RawNoteOffEvent;
import com.rosshoyt.analysis.model.raw.midi_events.RawNoteOnEvent;
import com.rosshoyt.analysis.model.raw.midi_events.controller_events.RawSustainPedalEvent;

public class MidiEventHandler {
   public static RawTrackEvent handleMidiEvent(StandardMidiFile.TrackEvent event, RawAnalysisStatisticsContainer rawAnalysisStatisticsContainer) {
      switch (event.eventType()) {
         case 144: {
            System.out.print("Note On Message Event\n");
            rawAnalysisStatisticsContainer.numNoteOnEvents++;
            StandardMidiFile.NoteOnEvent smfNoteOnEvent = (StandardMidiFile.NoteOnEvent) event.eventBody();
            RawNoteOnEvent _noteOn = new RawNoteOnEvent();
            _noteOn.setNote(smfNoteOnEvent.note());
            _noteOn.setVelocity(smfNoteOnEvent.velocity());
            return _noteOn;
         }
         case 128: {
            System.out.print("Note Off Message Event\n");
            rawAnalysisStatisticsContainer.numNoteOffEvents++;
            StandardMidiFile.NoteOffEvent smfNoteOffEvent = (StandardMidiFile.NoteOffEvent) event.eventBody();
            RawNoteOffEvent _noteOff = new RawNoteOffEvent();
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
            rawAnalysisStatisticsContainer.numControllerEvents++;
            switch (controllerEvent.controller()) {
               case 64: {
                  // Sustain Pedal
                  rawAnalysisStatisticsContainer.numSustainPedalEvents++;
                  RawSustainPedalEvent _sustainPedalEvent = new RawSustainPedalEvent();
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

}
