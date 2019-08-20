package com.rosshoyt.analysis.model.raw.midi_events;

import com.rosshoyt.analysis.model.raw._TrackEvent;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class _PitchBendEvent extends _TrackEvent {


   private Integer bendValue;

   private Integer adjBendValue;

   private int b1;
   private int b2;

}
