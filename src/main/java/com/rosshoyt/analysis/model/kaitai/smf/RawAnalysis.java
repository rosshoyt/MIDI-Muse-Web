package com.rosshoyt.analysis.model.kaitai.smf;

import com.rosshoyt.analysis.model.MidiFileAnalysis;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Data
@NoArgsConstructor
@Entity
public class RawAnalysis {

   @Id
   @GeneratedValue(strategy=GenerationType.SEQUENCE)
   private Long id; // probably Same id as MidiFileAnalysis

   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private _Header header;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<_Track> tracks;

//   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//   private MidiFileAnalysis midiFileAnalysis;
   private Long fkMidiFileAnalysis;
}
