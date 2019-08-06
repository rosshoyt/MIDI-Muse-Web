package com.rosshoyt.analysis.model.kaitai.smf;

import lombok.Data;

import javax.persistence.*;

/**
 * Single Table inheritance strategy
 * @See https://thoughts-on-java.org/complete-guide-inheritance-strategies-jpa-hibernate/
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class _TrackEvent {
   @Id
   @GeneratedValue(strategy=GenerationType.SEQUENCE)
   private Long id;

   // Nullable
   private Integer channel;
   private Long tick; // ticks since start
   private Integer vTime; // ticks since previous midi message

   private int trackNumber;
   private Long fkMidiFileAnalysisId;



}