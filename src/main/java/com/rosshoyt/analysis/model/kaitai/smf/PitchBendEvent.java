package com.rosshoyt.analysis.model.kaitai.smf;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PitchBendEvent {


   private Integer bendValue;

   private Integer adjBendValue;

   private int b1;
   private int b2;
   @Id
   private Long id;
   private Long fkRawAnalysisId;
   private TrackEvent _parent;

}
