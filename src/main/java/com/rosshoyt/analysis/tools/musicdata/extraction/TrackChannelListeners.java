package com.rosshoyt.analysis.tools.musicdata.extraction;


import com.rosshoyt.analysis.model.raw.midi_events._NoteOffEvent;
import com.rosshoyt.analysis.model.raw.midi_events._NoteOnEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public final class TrackChannelListeners {

   private Map<Integer, MidiTrackListener> midiTrackListeners = new HashMap<>();

   public TrackChannelListeners(int numTracks) {
      for(int i = 0; i < numTracks; i++) midiTrackListeners.put(i, new MidiTrackListener());
   }

   public void addNoteOn(_NoteOnEvent noteOn) {

      //System.out.print("Adding " + noteOn + " to Track/Channel Listener "+ noteOn.getTrackNumber()+ "/" + noteOn.getChannel());

      midiTrackListeners.get(noteOn.getTrackNumber()).addNoteOnEvent(noteOn);
   }
   public Optional<_NoteOnEvent> getNoteOnEventFromNoteOffEvent(_NoteOffEvent noteOff)  {
      if(noteOff.getNote() >= 0 && noteOff.getNote() <= 127) { // TODO optimize corrupted midi data checking
         System.out.print("Getting noteOn for " + noteOff + " from Track/Channel Listener "+ noteOff.getTrackNumber()+ "/" + noteOff.getChannel());
         return midiTrackListeners.get(noteOff.getTrackNumber()).getNoteOnEventFromNoteOffEvent(noteOff);
      }
      return Optional.ofNullable(null);
   }
   private final class MidiTrackListener{
      private static final int NUMBER_OF_MIDI_CHANNELS = 16;
      private Map<Integer, MidiChannelListener> channelListenerMap = new HashMap<>();

      MidiTrackListener() {
         {
            for(int i = 0; i < NUMBER_OF_MIDI_CHANNELS; i++) channelListenerMap.put(i, new MidiChannelListener());
         }
      }
      private void addNoteOnEvent(_NoteOnEvent noteOnEvent){

         channelListenerMap.get(noteOnEvent.getTrackNumber()).addNoteOnEvent(noteOnEvent);//[noteOnEvent.getChannel()];
      }

      private Optional<_NoteOnEvent> getNoteOnEventFromNoteOffEvent(_NoteOffEvent noteOffEvent){
         //return hangingNotes[noteOffEvent.getChannel()][noteOffEvent.getNote()].poll();
         return channelListenerMap.get(noteOffEvent.getChannel()).getNoteOnEventFromNoteOffEvent(noteOffEvent);

      }
      private final class MidiChannelListener{
         private static final int NUMBER_OF_MIDI_NOTE_PITCHES = 128;
         private Map<Integer, MidiPitchQueue> noteQueueMap = new HashMap<>();

         MidiChannelListener() {
            for(int i = 0; i < NUMBER_OF_MIDI_NOTE_PITCHES; i++) noteQueueMap.put(i, new MidiPitchQueue());
         }
         void addNoteOnEvent(_NoteOnEvent noteOnEvent){
            noteQueueMap.get(noteOnEvent.getNote()).addNoteOnEvent(noteOnEvent);
         }
         Optional<_NoteOnEvent> getNoteOnEventFromNoteOffEvent(_NoteOffEvent noteOffEvent){
            return noteQueueMap.get(noteOffEvent.getNote()).getNoteOn();
         }

         private final class MidiPitchQueue {
            private Queue<_NoteOnEvent> noteOnEventQueue = new LinkedBlockingQueue<>();

            void addNoteOnEvent(_NoteOnEvent noteOnEvent){

               noteOnEventQueue.add(noteOnEvent);

            }
            boolean isEmpty(){
               return noteOnEventQueue.isEmpty();
            }
            Optional<_NoteOnEvent> getNoteOn(){
//               if(isEmpty()){
//
//               }
               return Optional.ofNullable(noteOnEventQueue.poll());
            }
         }
      }
   }
}

