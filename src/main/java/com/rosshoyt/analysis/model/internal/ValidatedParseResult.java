package com.rosshoyt.analysis.model.internal;

import com.rosshoyt.analysis.midifile.tools.kaitai.StandardMidiFile;

public class ValidatedParseResult {
   public StandardMidiFile smf;
   public byte[] data;
   public String fileName;
   public String extension;
}
