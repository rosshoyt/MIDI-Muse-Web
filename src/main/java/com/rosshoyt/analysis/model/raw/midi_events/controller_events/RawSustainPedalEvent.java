package com.rosshoyt.analysis.model.raw.midi_events.controller_events;


import com.rosshoyt.analysis.model.raw.midi_events.controller_events.abstractions.RawControllerEvent;
import lombok.Data;

import javax.persistence.Entity;
@Data
@Entity
public class RawSustainPedalEvent extends RawControllerEvent {
   int value;
}