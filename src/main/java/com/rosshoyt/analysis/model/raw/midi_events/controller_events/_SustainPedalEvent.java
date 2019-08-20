package com.rosshoyt.analysis.model.raw.midi_events.controller_events;


import com.rosshoyt.analysis.model.raw.midi_events._ControllerEvent;
import lombok.Data;

import javax.persistence.Entity;
@Data
@Entity
public class _SustainPedalEvent extends _ControllerEvent {
   int value;
}