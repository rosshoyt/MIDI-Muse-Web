package com.rosshoyt.analysis.model.musical;

import com.rosshoyt.analysis.model.raw.MidiEvent;

import java.time.Duration;

public class EffectiveDuration extends RhythmicDuration {
   private Duration timeDuration;

   private MidiEvent susPedal;
}
