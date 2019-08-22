package com.rosshoyt.analysis.model.music;


import com.rosshoyt.analysis.model.abstractions.BaseReferencingEntity;
import lombok.Data;

import javax.persistence.Entity;
import java.time.Duration;
@Data
@Entity
public class EffectiveDuration extends RhythmicDuration {

   private Duration timeDuration;

//   private MidiEvent susPedal;
}
