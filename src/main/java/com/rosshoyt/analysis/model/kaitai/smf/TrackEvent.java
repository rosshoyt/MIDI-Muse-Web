package com.rosshoyt.analysis.model.kaitai.smf;

import com.rosshoyt.analysis.midifile.tools.kaitai.VlqBase128Be;
import io.kaitai.struct.KaitaiStruct;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TrackEvent  {


   private Integer eventType;

   private Integer channel;

   private VlqBase128Be vTime;
   private int eventHeader;
   private MetaEventBody metaEventBody;
   private SysexEventBody sysexBody;
   private KaitaiStruct eventBody;
   @Id
   private Long id;
   private Long fkRawAnalysisId;
   private TrackEvents _parent;
}