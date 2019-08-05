package com.rosshoyt.analysis.model.musical;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Transactional
@Entity
public class MusicalAnalysis {
   
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private long id;

//   @OneToMany//(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//   private List<Chord> chords;


   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
   private MusicalArrangement musicalArrangement;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   private List<Track> tracks;
//   @OneToMany
//   private List<Note> notes;

}
