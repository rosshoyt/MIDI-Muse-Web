package com.rosshoyt.analysis.model.raw.midi_events;


import com.rosshoyt.analysis.model.raw._TrackEvent;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class _PolyphonicPressureEvent extends _TrackEvent {

   private int note;
   private int pressure;

}