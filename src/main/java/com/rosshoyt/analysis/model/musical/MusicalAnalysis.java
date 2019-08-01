package com.rosshoyt.analysis.model.musical;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString
@Entity
public class MusicalAnalysis {
   
   @Id
   private long id;



   @OneToMany//(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   private List<Chord> chords;


   @OneToOne
   private MusicalArrangement musicalArrangement;

   @OneToMany
   private Set<Track> tracks;
   @OneToMany
   private List<Note> notes;

}
