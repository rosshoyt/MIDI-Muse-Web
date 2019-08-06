package com.rosshoyt.analysis.midifile.tools.handlers;


import com.rosshoyt.analysis.midifile.tools.kaitai.StandardMidiFile;
import com.rosshoyt.analysis.model.kaitai.smf._TrackEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOffEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOnEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events.controller_events._SustainPedalEvent;

public class MidiEventHandler {
   public static _TrackEvent handleMidiEvent(StandardMidiFile.TrackEvent event) {
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

}
