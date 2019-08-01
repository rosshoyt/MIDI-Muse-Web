package com.rosshoyt.analysis.model.raw;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Domain class...
 */
@Data
@NoArgsConstructor
@ToString
@Entity
public class OldRawAnalysis {
   @Id
   private Long id;

//   @OneToOne
//   @JoinColumn(name = "id")
//   @MapsId
//   @ToString.Exclude
//   private MidiFileAnalysis midiFileAnalysis;



   /*
   * Basic Raw musical information for display (TODO refactor and add more advanced schema based on each Track)
    */
   private int numMidiMessages;

   @OneToMany
   private List<RawTrack> rawTracks;
}




