package com.rosshoyt.analysis.model.raw.meta_events;


import com.rosshoyt.analysis.model.raw.abstractions.RawTrackEvent;
import lombok.Data;

import javax.persistence.Entity;

/**
 * @SEE http://midi.teragonaudio.com/tech/midifile/seq.htm
 * FF 00 02 ss ss
 *
 * or...
 *
 * FF 00 00
 *
 * This optional event must occur at the beginning of a MTrk (ie, before any non-zero delta-times and before any midi events).
 * It specifies the sequence number. The two data bytes ss ss, are that number which corresponds to the MIDI Cue message.
 * In a format 2 MIDI file, this number identifies each "pattern" (ie, Mtrk) so that a "song" sequence can use the MIDI Cue message to refer to patterns.
 *
 * If the ss ss numbers are omitted (ie, the second form shown above), then the MTrk's location in the file is used.
 * (ie, The first MTrk chunk is sequence number 0. The second MTrk is sequence number 1. Etc).
 *
 * In format 0 or 1, which contain only one "pattern" (even though format 1 contains several MTrks), this event is
 * placed in only the first MTrk. So, a group of format 0 or 1 files with different sequence numbers can comprise a "song collection".
 *
 * There can be only one of these events per MTrk chunk in a Format 2. There can be only one of these events in a
 * Format 0 or 1, and it must be in the first MTrk.
 */
@Data
@Entity
public class RawSequenceNumberEvent extends RawTrackEvent {

}