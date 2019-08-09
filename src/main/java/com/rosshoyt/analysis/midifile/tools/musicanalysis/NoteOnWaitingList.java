package com.rosshoyt.analysis.midifile.tools.musicanalysis;


import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOffEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOnEvent;
import com.rosshoyt.analysis.model.musical.Note;
import com.rosshoyt.analysis.model.musical.RhythmicDuration;

import javax.sound.midi.InvalidMidiDataException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class which holds Midi NoteOn messages waiting for their NoteOff's, enabling creation
 * of custom NoteObjects with defined start / end time
 */
public class NoteOnWaitingList {

   private Queue<_NoteOnEvent>[] hangingNotes;
   private static final int NUMBER_OF_MIDINOTE_PITCHES = 128;


   public NoteOnWaitingList() {
      hangingNotes = new LinkedList[NUMBER_OF_MIDINOTE_PITCHES];
      for(int i = 0; i < hangingNotes.length; i++) {
         hangingNotes[i] = new LinkedList<>();
      }
   }

   public void addNoteOn(_NoteOnEvent noteOn) {
      hangingNotes[noteOn.getNote()].add(noteOn);
   }

   public Note getNoteFromNoteOffEvent(_NoteOffEvent noteOff, Note note)  {
      if(noteOff.getNote() >= 0 && noteOff.getNote() <= 127) {
         _NoteOnEvent noteOn = hangingNotes[noteOff.getNote()].poll();
         if (noteOn != null) {
            note.setPitch(noteOn.getNote());
            note.setVelocityOn(noteOn.getVelocity());
            note.setVelocityOff(noteOff.getVelocity());
            note.setGridOn(noteOn.getTick());
            note.setGridOff(noteOff.getTick());
            return note;
         }
      }
      return null;
   }

}
