package com.rosshoyt.analysis.model.raw.midi_events;

import com.rosshoyt.analysis.model.raw.abstractions.RawTrackEvent;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class RawPitchBendEvent extends RawTrackEvent {


   private Integer bendValue;

   private Integer adjBendValue;

   private int b1;
   private int b2;

}
