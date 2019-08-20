package com.rosshoyt.analysis.model.internal;

import lombok.Data;

@Data
public class RawEventCountContainer {

   public int numTotalEvents;

   public int numMetaEvents;
   public int numSysexEvents;
   public int numMidiEvents;
   public int numControllerEvents;

   public int numNoteOnEvents;
   public int numNoteOffEvents;
   public int numSustainPedalEvents;

   public int numTempoEvents;
   public int numTimeSignatureEvents;

   public int numUnsupportedEvents;
}
