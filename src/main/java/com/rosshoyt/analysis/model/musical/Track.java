package com.rosshoyt.analysis.model.musical;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Track {
   @Id
   private Long id;

   private String trackName;
   // List<GeneralMidiPatch> patch; // TODO SUPPORT GENERAL MIDI SPEC

   int numNotes;



   @OneToMany
   Set<SustainPedal> sustainPedalList;



}
