package com.rosshoyt.analysis.model.kaitai.smf;

import com.rosshoyt.analysis.model.MidiFileAnalysis;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class RawAnalysis {
   @OneToOne
   private _Header header;
   @OneToMany(fetch = FetchType.LAZY)
   private List<_Track> tracks;

   @Id
   private Long id; // Id of MidiFileAnalysis
//   @OneToOne(fetch = FetchType.LAZY)
//   private MidiFileAnalysis midiFileAnalysis;
}
