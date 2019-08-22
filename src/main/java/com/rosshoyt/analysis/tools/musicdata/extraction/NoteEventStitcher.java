package com.rosshoyt.analysis.tools.musicdata.extraction;


import com.rosshoyt.analysis.model.raw.RawAnalysisStatistics;
import com.rosshoyt.analysis.model.raw.midi_events.RawNoteOffEvent;
import com.rosshoyt.analysis.model.raw.midi_events.RawNoteOnEvent;
import com.rosshoyt.analysis.model.music.Note;
import com.rosshoyt.analysis.model.raw.midi_events.abstractions.RawNoteEvent;

import java.util.*;

/**
 * Class which holds Midi NoteOn messages waiting for their NoteOff's, enabling creation
 * of custom NoteObjects with defined start / end time
 */

public class NoteEventStitcher {

   /**
    * Private constructor to prevent instantiation
    */
   private NoteEventStitcher(){}

   public static SortedSet<Note> stitchNotes(int numTracks, RawAnalysisStatistics rawAnalysisStatistics, List<RawNoteEvent> noteEvents) {
      TrackChannelListeners trackChannelListeners = new TrackChannelListeners(numTracks);
      SortedSet<Note> notes = new TreeSet<>();
      System.out.println("TODO reimplement Note Stitch algorithm");
/*
      // Prepare iteration over note arrays
      Iterator<RawNoteOnEvent> noteOnEventIterator = noteOnEvents.iterator();
      Iterator<RawNoteOffEvent> noteOffEventIterator = noteOffEvents.iterator();
      RawNoteOnEvent noteOn = noteOnEventIterator.next();
      RawNoteOffEvent noteOff = noteOffEventIterator.next();

      // Prepare note-joining loop
//      int noteCreationTarget = Math.min(noteOnEvents.size(), noteOffEvents.size());
//      System.out.println("Note Creation Target = " + noteCreationTarget);
//      int notesCreated = 0;
//      boolean finalLoop = false;
      // Bring noteOff iterator to event happening after noteOn event, if needed
//      while(notesCreated < noteCreationTarget){ // TODO have to ensure equivalent #s note ons/offs, first noteOn happens before first noteOff
         do{


            trackChannelListeners.addNoteOn(noteOn);

            if(noteOnEventIterator.hasNext()) noteOn = noteOnEventIterator.next();
            else finalLoop = true;
         } while(noteOn.getTick() <= noteOff.getTick() && !finalLoop);
         // Loop through remaining NoteOffs that occur before next NoteOn
         do {
            Optional<RawNoteOnEvent> noteOnResult = trackChannelListeners.getNoteOnEventFromNoteOffEvent(noteOff); // TODO add try/catch for custom exception when multiple note ons happen on a track/channel listener before a note off
            if (noteOnResult.isPresent()) {
               System.out.println("Found Note On for Note Off");
               Note note = new Note();
               note.setPitch          (noteOnResult.get().getNote());
               note.setVelocityOn     (noteOnResult.get().getVelocity());
               note.setTick           (noteOnResult.get().getTick());
               note.setMidiChannel    (noteOnResult.get().getChannel());
               note.setMidiTrackNumber(noteOnResult.get().getTrackNumber());
               note.setVelocityOff    (noteOff.getVelocity());
               note.setTickOff        (noteOff.getTick());
               System.out.println("Created " + note);
               notes.add(note);
               notesCreated++;
            } else {
               System.out.println("Error creating note from NoteOn and NoteOff");
               noteCreationTarget--;
            }
            if(noteOffEventIterator.hasNext()) noteOff = noteOffEventIterator.next();
         }while (noteOff.getTick() < noteOn.getTick() && !finalLoop);
      }*/
      return notes;
   }

}

