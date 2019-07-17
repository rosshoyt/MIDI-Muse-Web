package com.rosshoyt.analysis.midi_file_tools;

import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;


public class MidiFIleAnalyzerTest {
   private static File smfType1 = new File("preloaded-midi-files/pianocon.mid");
   @Test
   public void analyze(){
      MidiFileAnalyzer midiFileAnalyzer = new MidiFileAnalyzer();
      try {
         System.out.println(midiFileAnalyzer.analyze(smfType1));
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
}
