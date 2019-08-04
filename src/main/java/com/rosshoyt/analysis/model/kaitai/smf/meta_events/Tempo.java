package com.rosshoyt.analysis.model.kaitai.smf.meta_events;


import com.rosshoyt.analysis.model.kaitai.smf._TrackEvent;
import lombok.Data;

import javax.persistence.Entity;

/**
 * This message consists of six bytes of data. The first byte is the status byte and has a hexadecimal value of 0xFF,
 * which means that this is a meta message. The second byte is the meta message type 0x51 and signifies that this is a
 * set tempo message. The third byte has the value 0x03, which means that there are three bytes remaining.
 * The remaining three bytes carry the number of microseconds per quarter note.
 * The following is an example of a MIDI set tempo meta message.
 * 0xFF 0x51 0x03 0x07 0xA1 0x20
 * The status byte 0xFF shows that this is a meta message. The second byte is the meta type 0x51 and signifies that
 * this is a set tempo meta message. The third byte is 3, which means that there are three remaining bytes.
 * These three bytes form the hexadecimal value 0x07A120 (500000 decimal), which means that there are 500,000
 * microseconds per quarter note.
 * Since there are 60,000,000 microseconds per minute, the message above translates to:
 * set the tempo to 60,000,000 / 500,000 = 120 quarter notes per minute (120 beats per minute).
 * @See https://www.recordingblogs.com/wiki/midi-time-signature-meta-message
 *
 * Web Excerpt 2:
 * FF 51 03 tt tt tt
 * The tempo is specified as the Number of microseconds per quarter note, between 1 and 16777215.
 * A value of 500000 (07 A1 20) corresponds to 120 quarter notes ("beats") per minute. To convert beats per minute
 * to a Tempo value, take the quotient from dividing 60,000,000 by the beats per minute.
 * NOTE: If there are no tempo events in a MIDI file, then the tempo is assumed to be 120 BPM.
 * In a format 0 file, the tempo changes are scattered throughout the one track. In format 1, the very first track
 * should consist of only the tempo (and time signature) events so that it could be read by some device capable of generating a "tempo map".
 * It is best not to place MIDI events in this track. In format 2, each track should begin with at least one initial tempo (and time signature) event.
 * @See https://www.mixagesoftware.com/en/midikit/help/HTML/meta_events.html
 */
@Data
@Entity
public class Tempo extends _TrackEvent {
   private int numMicrosecondsPerQuarterNote;
}