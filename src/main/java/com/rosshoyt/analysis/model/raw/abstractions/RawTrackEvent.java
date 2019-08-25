package com.rosshoyt.analysis.model.raw.abstractions;

import com.rosshoyt.analysis.model.abstractions.TickOrderedEvent;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Single Table inheritance strategy
 * @See https://thoughts-on-java.org/complete-guide-inheritance-strategies-jpa-hibernate/
 */
@Data
@MappedSuperclass
@ToString(callSuper = true)
public abstract class RawTrackEvent extends TickOrderedEvent{

   private int trackNumber;
   private Integer channel;

   @ToString.Exclude
   private Integer vTime; // ticks since previous midi message. This is the field 'tick' is derived from...




}