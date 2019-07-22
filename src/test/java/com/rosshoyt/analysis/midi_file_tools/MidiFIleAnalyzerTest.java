package com.rosshoyt.analysis.midi_file_tools;

import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.repositories.MidiFileAnalysisRepository;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;


public class MidiFIleAnalyzerTest {

   private static File smfType1 = new File("preloaded-midi-files/pianocon.mid");
   @Test
   public void analyze(){
      MidiFileAnalyzer midiFileAnalyzer = new MidiFileAnalyzer();
      try {
         MidiFileAnalysis mfa = midiFileAnalyzer.analyze(smfType1);
         System.out.println(mfa);

      } catch(Exception e) {
         e.printStackTrace();
      }
   }

}
