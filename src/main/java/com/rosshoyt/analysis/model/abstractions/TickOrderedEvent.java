package com.rosshoyt.analysis.model.abstractions;

import lombok.Data;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Data
@ToString(callSuper = true)
@MappedSuperclass
public abstract class TickOrderedEvent extends FlaggableEvent implements Comparable<TickOrderedEvent> {

   private Long tick; // ticks since start

   @Override
   public int compareTo(TickOrderedEvent o) {
      return tick.compareTo(o.getTick());
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      TickOrderedEvent tickOrderedEvent = (TickOrderedEvent) o;
      return Objects.equals(getTick(), tickOrderedEvent.getTick());
   }

   @Override
   public int hashCode() {
      return Objects.hash(tick);
   }

}
