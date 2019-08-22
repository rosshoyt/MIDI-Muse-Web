package com.rosshoyt.analysis.model.music;

import com.rosshoyt.analysis.model.abstractions.BaseReferencingEntity;
import lombok.*;

import javax.persistence.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Track extends BaseReferencingEntity {


   private String trackName;
   // List<GeneralMidiPatch> patch; // TODO SUPPORT GENERAL MIDI SPEC

   int numNotes;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   Set<SustainPedal> sustainPedalList;



}
