package com.rosshoyt.analysis.model.raw;

/**
 *
 * Midi Time Code (MTC)
 * For many (non-musical) cues, it's easier for humans to reference time in some absolute way
 * rather than based upon musical beats at a certain tempo...
 * MTC messages are an alternative to using MIDI Clocks and Song Position Pointer messages.
 * MTC is essentially SMPTE mutated for transmission over MIDI.
 * SMPTE timing is referenced from an absolute "time of day". On the other hand,
 * MIDI Clocks and Song Position Pointer are based upon musical beats from the start of a song, played at a specific Tempo.
 * There are several MIDI messages which make up the MTC protocol.
 * All but one are specially defined SysEx messages.
 */
public class MTC {

}
