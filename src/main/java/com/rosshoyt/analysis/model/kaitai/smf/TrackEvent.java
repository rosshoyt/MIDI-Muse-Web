package com.rosshoyt.analysis.model.kaitai.smf;

import com.rosshoyt.analysis.midifile.tools.kaitai.VlqBase128Be;
import io.kaitai.struct.KaitaiStruct;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TrackEvent {
   @Id
   private Long id;
   private Long fkRawAnalysisId;

   private Integer eventType;
   private Integer channel;

   private VlqBase128Be vTime;



}