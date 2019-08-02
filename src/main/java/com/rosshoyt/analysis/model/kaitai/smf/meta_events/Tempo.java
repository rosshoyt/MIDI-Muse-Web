package com.rosshoyt.analysis.model.kaitai.smf.meta_events;


import com.rosshoyt.analysis.model.kaitai.smf._TrackEvent;
import lombok.Data;

import javax.persistence.Entity;

/**
 * FF 51 03 tt tt tt
 *
 * The tempo is specified as the Number of microseconds per quarter note, between 1 and 16777215.
 * A value of 500000 (07 A1 20) corresponds to 120 quarter notes ("beats") per minute. To convert beats per minute
 * to a Tempo value, take the quotient from dividing 60,000,000 by the beats per minute.
 *
 * NOTE: If there are no tempo events in a MIDI file, then the tempo is assumed to be 120 BPM.
 *
 * In a format 0 file, the tempo changes are scattered throughout the one track. In format 1, the very first track
      * should consist of only the tempo (and time signature) events so that it could be read by some device capable of generating a "tempo map".
      * It is best not to place MIDI events in this track. In format 2, each track should begin with at least one initial tempo (and time signature) event.
      * @See https://www.mixagesoftware.com/en/midikit/help/HTML/meta_events.html
      */
@Data
@Entity
public class Tempo extends _TrackEvent {
   private long numMicrosecondsPerQuarterNote;
}