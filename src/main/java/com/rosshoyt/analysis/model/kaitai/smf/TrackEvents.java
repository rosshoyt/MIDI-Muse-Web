package com.rosshoyt.analysis.model.kaitai.smf;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class TrackEvents {

   private ArrayList<TrackEvent> event;
   @Id
   private Long id;
   private Long fkRawAnalysisId;
   private Long fk;
}