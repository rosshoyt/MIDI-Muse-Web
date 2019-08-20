package com.rosshoyt.analysis.model.raw.midi_events;

import com.rosshoyt.analysis.model.raw._TrackEvent;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public abstract class _ControllerEvent extends _TrackEvent {

   private int controller;
   private int value;

}