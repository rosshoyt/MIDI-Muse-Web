package com.rosshoyt.analysis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "midifile_analysis")
public class MidiFileAnalysis {

   @Id
   @GeneratedValue
   private Long id;
   @NonNull
   private String fileName;
   private int midiFileType;
   private int numTracks;
   @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   private Set<Chord> chords;

   // Extra example
   //@ManyToOne(cascade=CascadeType.PERSIST)
   //private User user;
}
