package com.rosshoyt.analysis.model.raw.midi_events.abstractions;


import com.rosshoyt.analysis.model.raw.abstractions.RawTrackEvent;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@ToString(callSuper = true)
public abstract class RawNoteEvent extends RawTrackEvent {

}
