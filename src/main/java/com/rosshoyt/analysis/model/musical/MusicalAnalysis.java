package com.rosshoyt.analysis.model.musical;

import com.rosshoyt.analysis.model.MidiFileAnalysis;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
   private MidiFileAnalysis midiFileAnalysis;

   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   private MusicalArrangement musicalArrangement;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   private List<Note> noteList = new ArrayList<>();
   // private List<Track> tracks = new ArrayList<>();
//   @OneToMany
//   private List<Note> notes;

}
