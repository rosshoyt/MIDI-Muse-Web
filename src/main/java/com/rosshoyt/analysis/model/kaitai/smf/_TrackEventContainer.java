package com.rosshoyt.analysis.model.kaitai.smf;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Data
@Entity
public class _TrackEventContainer {
   @Id
   private Long id;
   // Nullable
   private Integer channel;
   private Integer tick;

   @OneToOne
   private _TrackEvent trackEvent;
   // convenience fields
   private int trackNumber;
   private Long fkMidiFileAnalysisId;
}
