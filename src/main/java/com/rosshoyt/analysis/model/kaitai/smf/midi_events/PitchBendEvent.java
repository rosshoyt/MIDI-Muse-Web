package com.rosshoyt.analysis.model.kaitai.smf.midi_events;

import com.rosshoyt.analysis.model.kaitai.smf.MidiEvent;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PitchBendEvent extends MidiEvent {


   private Integer bendValue;

   private Integer adjBendValue;

   private int b1;
   private int b2;

}
