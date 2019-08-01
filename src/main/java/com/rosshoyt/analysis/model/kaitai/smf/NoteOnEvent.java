package com.rosshoyt.analysis.model.kaitai.smf;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NoteOnEvent {

   private int note;
   private int velocity;
   @Id
   private Long id;
   private Long fkRawAnalysisId;
   private TrackEvent _parent;
}