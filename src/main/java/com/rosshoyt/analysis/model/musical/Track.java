package com.rosshoyt.analysis.model.musical;

import lombok.*;

import javax.persistence.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Track {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long id;

   private String trackName;
   // List<GeneralMidiPatch> patch; // TODO SUPPORT GENERAL MIDI SPEC

   int numNotes;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   Set<SustainPedal> sustainPedalList;



}
