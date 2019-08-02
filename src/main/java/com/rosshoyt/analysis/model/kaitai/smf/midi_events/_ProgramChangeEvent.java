package com.rosshoyt.analysis.model.kaitai.smf.midi_events;

import com.rosshoyt.analysis.model.kaitai.smf._TrackEvent;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class _ProgramChangeEvent extends _TrackEvent {

   private int program;

}