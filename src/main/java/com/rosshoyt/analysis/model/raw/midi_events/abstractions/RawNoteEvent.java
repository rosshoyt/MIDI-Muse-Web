package com.rosshoyt.analysis.model.raw.midi_events.abstractions;


import com.rosshoyt.analysis.model.raw.abstractions.RawTrackEvent;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class RawNoteEvent extends RawTrackEvent {

}
