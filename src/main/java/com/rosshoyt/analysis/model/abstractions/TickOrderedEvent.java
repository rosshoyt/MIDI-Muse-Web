package com.rosshoyt.analysis.model.abstractions;

import com.rosshoyt.analysis.model.raw._TrackEvent;
import lombok.Data;

import java.util.Objects;

@Data
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
