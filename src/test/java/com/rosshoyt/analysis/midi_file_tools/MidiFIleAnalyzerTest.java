package com.rosshoyt.analysis.midi_file_tools;

import com.rosshoyt.analysis.model.MidiFileAnalysis;
import org.junit.Test;

import java.io.File;


public class MidiFIleAnalyzerTest {

   private static File smfType1 = new File("preloaded-midi-files/pianocon.mid");
   @Test
   public void analyze(){
      MidiFileAnalyzer midiFileAnalyzer = new MidiFileAnalyzer();
      try {
         ParseResult parseResult = midiFileAnalyzer.initialParse(smfType1);
         MidiFileAnalysis mfa = midiFileAnalyzer.analyzeParseResult(new MidiFileAnalysis(), parseResult);
         System.out.println(mfa);

      } catch(Exception e) {
         e.printStackTrace();
      }
   }

}
