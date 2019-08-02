package com.rosshoyt.analysis.model.kaitai.smf.midi_events;

import com.rosshoyt.analysis.model.kaitai.smf.MidiEvent;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class ControllerEvent extends MidiEvent {

   private int controller;
   private int value;

}