package com.rosshoyt.analysis.model.raw.midi_events;

import com.rosshoyt.analysis.model.raw._TrackEvent;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;


@Data
@Entity
@ToString(callSuper = true)
public class _NoteOffEvent extends _TrackEvent {

   private int note;
   private int velocity;

}
