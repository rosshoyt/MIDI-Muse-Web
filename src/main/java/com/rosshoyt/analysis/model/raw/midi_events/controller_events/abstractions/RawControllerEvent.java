package com.rosshoyt.analysis.model.raw.midi_events.controller_events.abstractions;

import com.rosshoyt.analysis.model.raw.abstractions.RawTrackEvent;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class RawControllerEvent extends RawTrackEvent {

   private int controller;
   private int value;

}