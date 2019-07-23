package com.rosshoyt.analysis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
@Entity
public class MusicalAnalysis {
   
   @Id
   private long id;

//   @OneToOne
//   @JoinColumn(name = "id")
//   @MapsId
//   @ToString.Exclude
//   private MidiFileAnalysis midiFileAnalysis;


   @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   private List<Chord> chords;
}
