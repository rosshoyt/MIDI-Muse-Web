package com.rosshoyt.analysis.model.kaitai.smf.midi_events;

import com.rosshoyt.analysis.model.kaitai.smf._TrackEvent;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class _ChannelPressureEvent extends _TrackEvent {


   private int pressure;

}