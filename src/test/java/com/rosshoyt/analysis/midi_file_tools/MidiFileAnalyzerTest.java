package com.rosshoyt.analysis.midi_file_tools;

import com.rosshoyt.analysis.midi_file_tools.kaitai.KaitaiSMFParser;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.utils.FileUtils;
import org.junit.Test;

import java.io.File;


public class MidiFileAnalyzerTest {

   private static File smfType1 = new File("preloaded-midi-files/pianocon.mid");
   @Test
   public void analyze(){
//      StandardMidiFileParser parser = new KaitaiSMFParser();
//      MidiFileAnalyzer midiFileAnalyzer = new MidiFileAnalyzer();
//      try {
//         ParseResult parseResult = parser.parse(FileUtils.getByteArray(smfType1));
//         MidiFileAnalysis mfa = midiFileAnalyzer.analyzeParseResult(new MidiFileAnalysis(), parseResult);
//         System.out.println(mfa);
//
//      } catch(Exception e) {
//         e.printStackTrace();
//      }
   }

}
