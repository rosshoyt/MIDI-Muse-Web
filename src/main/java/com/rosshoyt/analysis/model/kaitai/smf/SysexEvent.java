package com.rosshoyt.analysis.model.kaitai.smf;

import com.rosshoyt.analysis.midifile.tools.kaitai.VlqBase128Be;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SysexEvent extends TrackEvent {

   private VlqBase128Be len;
   private byte[] data;




}