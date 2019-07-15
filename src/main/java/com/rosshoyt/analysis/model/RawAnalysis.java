package com.rosshoyt.analysis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Domain class...
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class RawAnalysis {
   @Id
   @GeneratedValue
   private Long id;

   /*
   * MThd header derived fields (in near-order of appearance)
   */
   private int midiFileFormatType; // 0, 1 or 2
   private int numTracks;
   private float divisionType;



}
