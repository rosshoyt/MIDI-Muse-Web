package com.rosshoyt.analysis.model.music;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RhythmicDuration {
   @Id
   private Long id;

   private double gridOn; //   exact decimal location in MIDI timeline in beats
   private double gridOff;  // exact decimal location in MIDI timeline in beats


}

