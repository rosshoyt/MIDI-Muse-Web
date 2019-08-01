package com.rosshoyt.analysis.model.kaitai.smf;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Header {


   private long lenHeader;
   private int format;
   private int numTracks;
   private short division;
   @Id
   private Long id;
   private Long fkRawAnalysisId;
   private RawAnalysis _parent;

}
