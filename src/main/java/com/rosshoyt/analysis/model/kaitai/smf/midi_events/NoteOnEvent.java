package com.rosshoyt.analysis.model.kaitai.smf.midi_events;

import com.rosshoyt.analysis.model.kaitai.smf.MidiEvent;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NoteOnEvent extends MidiEvent {

   private int note;
   private int velocity;

}