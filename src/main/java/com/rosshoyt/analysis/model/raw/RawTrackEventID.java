package com.rosshoyt.analysis.model.raw;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class RawTrackEventID implements Serializable {

   private Long eventNumber;
   private int trackNumber;




   // default constructor

   public RawTrackEventID(Long eventNumber, int trackNumber) {
      this.eventNumber = eventNumber;
      this.trackNumber = trackNumber;
   }


   // getters, equals() and hashCode() methods
}
