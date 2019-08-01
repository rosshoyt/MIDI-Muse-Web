package com.rosshoyt.analysis.model.kaitai.smf;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ChannelPressureEvent {


   private int pressure;
   @Id
   private Long id;
   private Long fkRawAnalysisId;
   private TrackEvent _parent;

}