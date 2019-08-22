package com.rosshoyt.analysis.model.raw.midi_events;

import com.rosshoyt.analysis.model.raw.abstractions.RawTrackEvent;
import com.rosshoyt.analysis.model.raw.midi_events.abstractions.RawNoteEvent;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;


@Data
@Entity
@ToString(callSuper = true)
public class RawNoteOffEvent extends RawNoteEvent {

   private int note;
   private int velocity;

}
