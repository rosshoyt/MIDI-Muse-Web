package com.rosshoyt.analysis.model.kaitai.smf;

import com.rosshoyt.analysis.midifile.tools.kaitai.VlqBase128Be;

import javax.persistence.Entity;


@Entity
public abstract class MetaEvent extends TrackEvent {



   private MetaTypeEnum metaType;
   private VlqBase128Be len;
   private byte[] body;




}