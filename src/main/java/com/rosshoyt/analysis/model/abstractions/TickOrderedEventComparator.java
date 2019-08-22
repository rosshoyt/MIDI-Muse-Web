package com.rosshoyt.analysis.model.abstractions;

import java.util.Comparator;


public class TickOrderedEventComparator implements Comparator<TickOrderedEvent> {
   @Override
   public int compare(TickOrderedEvent o1, TickOrderedEvent o2) {
      return o1.compareTo(o2);
   }
}