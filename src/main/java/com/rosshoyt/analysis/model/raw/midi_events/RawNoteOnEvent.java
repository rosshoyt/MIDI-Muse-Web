package com.rosshoyt.analysis.model.raw.midi_events;

import com.rosshoyt.analysis.model.raw.midi_events.abstractions.RawNoteEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RawNoteOnEvent extends RawNoteEvent {

   private int note;
   private int velocity;

}