package com.rosshoyt.analysis.midifile.tools;

import com.rosshoyt.analysis.midifile.tools.exceptions.UnexpectedMidiDataException;
import com.rosshoyt.analysis.model.internal.ValidatedParseResult;
import com.rosshoyt.analysis.midifile.tools.kaitai.StandardMidiFile;
import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStream;


public class KaitaiSMFParser implements StandardMidiFileParser {
   public ValidatedParseResult parse(byte[] data) throws UnexpectedMidiDataException {

      System.out.print("...Parsing SMF with Kaitai Struct...\n");
      ValidatedParseResult parseResult = new ValidatedParseResult();
      try {
         // Kaitai Struct SMF parse
         parseResult.smf = new StandardMidiFile(new ByteBufferKaitaiStream(data));
      } catch(Exception e) {
         if(e instanceof KaitaiStream.UnexpectedDataError || e instanceof KaitaiStream.UndecidedEndiannessError)
         throw new UnexpectedMidiDataException();
      }
      // Retain data for later use
      parseResult.data = data;

      return parseResult;
   }
}


