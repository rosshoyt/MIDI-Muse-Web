package com.rosshoyt.analysis.model.raw;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ControllerEvent extends MidiEvent {
   int controller;
   int value;
}
