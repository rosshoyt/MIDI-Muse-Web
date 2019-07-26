package com.rosshoyt.analysis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Domain class...
 */
@Data
@NoArgsConstructor
@ToString
@Entity
public class RawAnalysis {
   @Id
   private Long id;

//   @OneToOne
//   @JoinColumn(name = "id")
//   @MapsId
//   @ToString.Exclude
//   private MidiFileAnalysis midiFileAnalysis;

   /*
   * MThd header derived fields (in near order of appearance)
   */
   private int midiFileFormatType; // 0, 1 or 2
   private int numTracks;
   private float divisionType;

   /*
   * Basic Raw musical information for display (TODO refactor and add more advanced schema based on each Track)
    */
   private int numMidiMessages;

}
