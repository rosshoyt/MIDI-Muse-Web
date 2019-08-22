package com.rosshoyt.analysis.model.music;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.abstractions.BaseReferencingEntity;
import com.rosshoyt.analysis.model.abstractions.TickOrderedEventComparator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
public class MusicalAnalysis extends BaseReferencingEntity {

   public MusicalAnalysis(MidiFileAnalysis midiFileAnalysis) {
      this.midiFileAnalysis = midiFileAnalysis;
   }



   @OneToOne
   @JoinColumn(name = "id")
   @MapsId
   @ToString.Exclude
   @JsonBackReference
   private MidiFileAnalysis midiFileAnalysis;

   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   private MusicalArrangement musicalArrangement;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   @SortComparator(TickOrderedEventComparator.class)
   private SortedSet<Note> notes = new TreeSet<>();
   // private List<Track> tracks = new ArrayList<>();
//   @OneToMany
//   private List<Note> notes;

}
