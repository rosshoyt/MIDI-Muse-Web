package com.rosshoyt.analysis.midifile.tools;

import com.rosshoyt.analysis.midifile.tools.exceptions.UnexpectedMidiDataException;
import com.rosshoyt.analysis.model.internal.ValidatedParseResult;

public interface StandardMidiFileParser {
   public ValidatedParseResult parse(byte[] data) throws UnexpectedMidiDataException;

}
