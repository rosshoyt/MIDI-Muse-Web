package com.rosshoyt.analysis.midi_file_tools;

import com.rosshoyt.analysis.midi_file_tools.exceptions.UnexpectedMidiDataException;
import io.kaitai.struct.KaitaiStream;

public interface StandardMidiFileParser {
   public ParseResult parse(byte[] data) throws UnexpectedMidiDataException;

}
