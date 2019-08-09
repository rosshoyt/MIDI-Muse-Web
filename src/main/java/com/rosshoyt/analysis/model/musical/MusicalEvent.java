package com.rosshoyt.analysis.model.musical;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MusicalEvent {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long id;
   //@OneToOne(fetch = FetchType.EAGER)
   //private RhythmicDuration rhythmicDuration;
   private long gridOn;
   private long gridOff;

   private int midiTrackNumber; //
   private int midiChannel; //

}
