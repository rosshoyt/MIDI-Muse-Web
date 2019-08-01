package com.rosshoyt.analysis.model.raw;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class MidiEvent {
   //@Id // TODO Implement RawTrackEventID with hashcode related to unique field
   //private RawTrackEventID id;
   @Id
   private Long id;

   private Integer tick;

   private int track;
   private int channel;
}
