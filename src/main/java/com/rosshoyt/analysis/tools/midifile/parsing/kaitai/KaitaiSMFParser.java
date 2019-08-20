package com.rosshoyt.analysis.tools.midifile.parsing.kaitai;

import com.rosshoyt.analysis.exceptions.UnexpectedMidiDataException;
import com.rosshoyt.analysis.model.internal.ValidParseResultContainer;
import com.rosshoyt.analysis.tools.midifile.parsing.StandardMidiFileParser;
import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStream;


public class KaitaiSMFParser implements StandardMidiFileParser {
   public ValidParseResultContainer parse(byte[] data) throws UnexpectedMidiDataException {

      System.out.print("...Parsing SMF with Kaitai Struct...\n");
      ValidParseResultContainer parseResult = new ValidParseResultContainer();
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


