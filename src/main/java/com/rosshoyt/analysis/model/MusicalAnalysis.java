package com.rosshoyt.analysis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class MusicalAnalysis {
   @Id
   @GeneratedValue
   private long id;
   @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   private List<Chord> chords;
}
