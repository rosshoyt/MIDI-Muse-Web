package com.rosshoyt.analysis.model.musical;

import com.rosshoyt.analysis.model.abstractions.TickOrderedEvent;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
public class MusicalEvent extends TickOrderedEvent {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long id;
   //@OneToOne(fetch = FetchType.EAGER)
   //private RhythmicDuration rhythmicDuration;
   //private long tickOn;
   private Long tickOff;

   private int midiTrackNumber; //
   private int midiChannel; //

}
