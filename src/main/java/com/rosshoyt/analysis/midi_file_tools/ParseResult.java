package com.rosshoyt.analysis.midi_file_tools;

import com.rosshoyt.analysis.midi_file_tools.kaitai.StandardMidiFile;

public class ParseResult {
   public StandardMidiFile smf;
   public byte[] data;
   public String fileName;
   public String extension;
}
