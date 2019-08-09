package com.rosshoyt.analysis.model.kaitai.smf;

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
public class RawAnalysis {

   public RawAnalysis(MidiFileAnalysis midiFileAnalysis) {
      this.midiFileAnalysis = midiFileAnalysis;
   }

   @Id
   //@GeneratedValue(strategy=GenerationType.SEQUENCE)
   private Long id; // probably Same id as MidiFileAnalysis

   @OneToOne
   @JoinColumn(name = "id")
   @MapsId
   @ToString.Exclude
   private MidiFileAnalysis midiFileAnalysis;

   @OneToOne(fetch = FetchType.EAGER
         , cascade = CascadeType.PERSIST
         , mappedBy = "rawAnalysis"
   )
   private _Header header;

//   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//   //@ToString.Exclude
//   private List<_Track> tracks;
   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   private List<_TrackEvent> trackEvents;


//   @OneToMany
//   private Set<_TrackEvent> events;

//   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//   private MidiFileAnalysis midiFileAnalysis;

   //private Long fkMidiFileAnalysis;
}
