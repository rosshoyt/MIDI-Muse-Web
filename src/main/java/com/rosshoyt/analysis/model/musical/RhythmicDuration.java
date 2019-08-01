package com.rosshoyt.analysis.model.musical;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public abstract class RhythmicDuration {
   @Id
   private Long id;

   private double gridOn; //   exact decimal location in MIDI timeline in beats
   private double gridOff;  // exact decimal location in MIDI timeline in beats


}

