package com.rosshoyt.analysis.model.raw.meta_events;


import com.rosshoyt.analysis.model.raw.abstractions.RawTrackEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * @See https://www.recordingblogs.com/wiki/midi-time-signature-meta-message
 * The following is an example of a MIDI time signature meta message.
 *
 * 0xFF 0x58 0x04 0x04 0x02 0x18 0x08
 *
 * The status byte 0xFF shows that this is a meta message. The second byte is the meta type 0x58 and signifies that
 * this is a time signature meta message. The third byte is 4, which means that there are four remaining bytes.
 * The fourth byte is 0x04, which means that the time signature numerator is 4. The fifth byte is 0x02, which means
 * that the time signature denominator is 2^2 = 4. The sixth byte is 0x18, which is 24 decimal, and means that
 * the metronome will click once every 24 MIDI clocks. The seventh byte is 8,
 * which means that there are eight 32nd notes per beat.
 *
 * If a time signature message is not present in a MIDI sequence, 4/4 signature is assumed.
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class RawTimeSignatureEvent extends RawTrackEvent {
   private int numerator;
   private int denominator;
   private int midiClocksPerMetronomeClick;
   private int num32ndNotesPerBeat;
}