package com.rosshoyt.analysis.model.musical;


import lombok.Data;

import javax.persistence.Entity;
import java.time.Duration;
@Data
@Entity
public class EffectiveDuration extends RhythmicDuration {
   private Duration timeDuration;

//   private MidiEvent susPedal;
}
