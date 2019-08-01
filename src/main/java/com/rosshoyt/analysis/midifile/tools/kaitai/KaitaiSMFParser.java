package com.rosshoyt.analysis.midifile.tools.kaitai;

import com.rosshoyt.analysis.midifile.tools.exceptions.UnexpectedMidiDataException;
import com.rosshoyt.analysis.midifile.tools.ParseResult;
import com.rosshoyt.analysis.midifile.tools.StandardMidiFileParser;
import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStream;


public class KaitaiSMFParser implements StandardMidiFileParser {
   public ParseResult parse(byte[] data) throws UnexpectedMidiDataException {
      System.out.print("...Parsing SMF with Kaitai Struct...\n");
      ParseResult parseResult = new ParseResult();
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
