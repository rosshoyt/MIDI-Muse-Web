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
public abstract class _TrackEvent {
   @Id
   @GeneratedValue(strategy=GenerationType.SEQUENCE)
   private Long id;
   @OneToOne
   private _TrackEventContainer container;


}