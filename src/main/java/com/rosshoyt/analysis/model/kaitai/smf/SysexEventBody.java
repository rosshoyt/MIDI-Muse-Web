package com.rosshoyt.analysis.model.kaitai.smf;

import com.rosshoyt.analysis.midifile.tools.kaitai.VlqBase128Be;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SysexEventBody {

   private VlqBase128Be len;
   private byte[] data;
   @Id 
   private Long id;
   private Long fkRawAnalysisId;
   private TrackEvent _parent;

}