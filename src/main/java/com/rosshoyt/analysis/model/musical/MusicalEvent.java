package com.rosshoyt.analysis.model.musical;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class MusicalEvent {
   @Id
   private Long id;
   @OneToOne
   private RhythmicDuration rhythmicDuration;
   // Filtering fields TODO could refactor to lazy-loading many to one mapping?
   private int midiTrackNumber; //
   private int midiChannel; //

}
