package com.rosshoyt.analysis.model.kaitai.smf;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Data
@Entity
public class _TrackEventContainer {
   @Id
   private Long id;

   // Nullable
   private Integer channel;
   private Long tick; // ticks since start
   private Integer vTime; // ticks since previous midi message

   private int trackNumber;
   private Long fkMidiFileAnalysisId;

   @OneToOne(fetch = FetchType.EAGER)
   private _TrackEvent trackEvent;
}
