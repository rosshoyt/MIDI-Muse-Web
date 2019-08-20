package com.rosshoyt.analysis.tools.midifile.parsing;

import com.rosshoyt.analysis.exceptions.UnexpectedMidiDataException;
import com.rosshoyt.analysis.model.internal.ValidParseResultContainer;

public interface StandardMidiFileParser {
   public ValidParseResultContainer parse(byte[] data) throws UnexpectedMidiDataException;

}
