package com.rosshoyt.analysis.midifile.tools;

import com.rosshoyt.analysis.midifile.tools.exceptions.UnexpectedMidiDataException;

public interface StandardMidiFileParser {
   public ParseResult parse(byte[] data) throws UnexpectedMidiDataException;

}
