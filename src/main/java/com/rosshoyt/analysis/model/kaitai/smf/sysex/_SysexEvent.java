package com.rosshoyt.analysis.model.kaitai.smf.sysex;

import com.rosshoyt.analysis.midifile.tools.kaitai.VlqBase128Be;
import com.rosshoyt.analysis.model.kaitai.smf._TrackEvent;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class _SysexEvent extends _TrackEvent {

   private Integer value;
   private byte[] data;





}