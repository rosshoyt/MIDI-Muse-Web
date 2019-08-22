package com.rosshoyt.analysis.model.raw.sysex;

import com.rosshoyt.analysis.model.raw.abstractions.RawTrackEvent;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class RawSysexEvent extends RawTrackEvent {

   private Integer value;
   private byte[] data;





}