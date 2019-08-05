package com.rosshoyt.analysis.model.kaitai.smf;

import com.rosshoyt.analysis.model.MidiFileAnalysis;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;



@Data
@NoArgsConstructor
@Transactional
@Entity
public class RawAnalysis {

   @Id
   //@GeneratedValue(strategy=GenerationType.SEQUENCE)
   private Long id; // probably Same id as MidiFileAnalysis

   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   private _Header header;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
   @ToString.Exclude
   private List<_Track> tracks;

   //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   //private MidiFileAnalysis midiFileAnalysis;

   //private Long fkMidiFileAnalysis;
}
