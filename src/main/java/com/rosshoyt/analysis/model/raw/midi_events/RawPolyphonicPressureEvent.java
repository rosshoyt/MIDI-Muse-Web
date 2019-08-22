package com.rosshoyt.analysis.model.raw.midi_events;


import com.rosshoyt.analysis.model.raw.abstractions.RawTrackEvent;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class RawPolyphonicPressureEvent extends RawTrackEvent {

   private int note;
   private int pressure;

}