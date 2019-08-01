package com.rosshoyt.analysis.model.kaitai.smf;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Track {
   private long lenEvents;
   private TrackEvents events;
   @Id
   private Long id;
   private Long fkRawAnalysisId;


}