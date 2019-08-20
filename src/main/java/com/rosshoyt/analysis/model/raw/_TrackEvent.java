package com.rosshoyt.analysis.model.raw;

import com.rosshoyt.analysis.model.abstractions.TickOrderedEvent;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * Single Table inheritance strategy
 * @See https://thoughts-on-java.org/complete-guide-inheritance-strategies-jpa-hibernate/
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@ToString(callSuper = true)
public class _TrackEvent extends TickOrderedEvent{
   @Id
   @GeneratedValue(strategy=GenerationType.SEQUENCE)
   private Long id;

   private int trackNumber;
   private Integer channel;

   @ToString.Exclude
   private Integer vTime; // ticks since previous midi message. This is the field 'tick' is derived from...
   @ToString.Exclude
   private Long fkMidiFileAnalysisId;




}