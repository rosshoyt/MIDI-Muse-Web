package com.rosshoyt.analysis.model.musical;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
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
public class MusicalAnalysis {

   public MusicalAnalysis(MidiFileAnalysis midiFileAnalysis) {
      this.midiFileAnalysis = midiFileAnalysis;
   }

   @Id
   //@GeneratedValue(strategy = GenerationType.SEQUENCE)
   private long id;

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
