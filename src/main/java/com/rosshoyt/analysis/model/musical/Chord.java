package com.rosshoyt.analysis.model.musical;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Chord {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;
   private String chordName;
   private Duration realTimelength;

   @ManyToMany(fetch = FetchType.LAZY)
   private Set<Note> notes;



}
