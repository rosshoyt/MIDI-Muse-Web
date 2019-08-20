package com.rosshoyt.analysis.model.raw.sysex;

import com.rosshoyt.analysis.model.raw._TrackEvent;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class _SysexEvent extends _TrackEvent {

   private Integer value;
   private byte[] data;





}