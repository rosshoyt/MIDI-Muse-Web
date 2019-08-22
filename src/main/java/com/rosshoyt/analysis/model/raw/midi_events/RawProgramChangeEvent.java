package com.rosshoyt.analysis.model.raw.midi_events;

import com.rosshoyt.analysis.model.raw.abstractions.RawTrackEvent;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class RawProgramChangeEvent extends RawTrackEvent {

   private int program;

}