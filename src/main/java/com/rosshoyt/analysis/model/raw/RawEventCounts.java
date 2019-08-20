package com.rosshoyt.analysis.model.raw;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rosshoyt.analysis.model.internal.RawEventCountContainer;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class RawEventCounts {
   public RawEventCounts(RawEventCountContainer eventCountContainer) {
      this.numTotalEvents = eventCountContainer.numTotalEvents;
      this.numMetaEvents = eventCountContainer.numMetaEvents;
      this.numSysexEvents = eventCountContainer.numSysexEvents;
      this.numMidiEvents = eventCountContainer.numMidiEvents;
      this.numControllerEvents = eventCountContainer.numControllerEvents;
      this.numNoteOnEvents = eventCountContainer.numNoteOnEvents;
      this.numNoteOffEvents = eventCountContainer.numNoteOffEvents;
      this.numSustainPedalEvents = eventCountContainer.numSustainPedalEvents;
      this.numTempoEvents = eventCountContainer.numTempoEvents;
      this.numTimeSignatureEvents = eventCountContainer.numTimeSignatureEvents;
      this.numUnsupportedEvents = eventCountContainer.numUnsupportedEvents;
   }

   @Id
   private Long id;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id")
   @MapsId
   @ToString.Exclude
   @JsonBackReference
   private RawAnalysis rawAnalysis;


   // global
   private int numTotalEvents;

   private int numMetaEvents;
   private int numSysexEvents;
   private int numMidiEvents;
   private int numControllerEvents;

   private int numNoteOnEvents;
   private int numNoteOffEvents;
   private int numSustainPedalEvents;

   private int numTempoEvents;
   private int numTimeSignatureEvents;

   private int numUnsupportedEvents;
}
