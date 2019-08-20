package com.rosshoyt.analysis.model.raw.midi_events;

import com.rosshoyt.analysis.model.raw._TrackEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class _NoteOnEvent extends _TrackEvent {

   private int note;
   private int velocity;

}