package com.rosshoyt.analysis.midi_file_tools;




import com.rosshoyt.analysis.midi_file_tools.exceptions.InvalidMidiFileException;

import com.rosshoyt.analysis.model.MidiFileAnalysis;

import com.rosshoyt.analysis.utils.SMFValidator;



import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;
import java.util.Properties;


/**
 * High level file analyzer.
 * Works with one uploaded .mid/.midi file to extract useful information and return
 *
 * SQL database-persistable analysis data
 */

public class MidiFileAnalyzer {
   // Static MIDI File Validation Utils
   private SMFValidator smfValidator = new SMFValidator();

   /**
    *
    */
   public MidiFileAnalyzer() { }

   public MidiFileAnalysis analyze(File midiFile) throws InvalidMidiFileException, InvalidMidiDataException, IOException {
      if(smfValidator.isValidSMF(midiFile)){

      } else throw new InvalidMidiFileException();
      return null;

   }
   private Properties parseHeader() {
      return null;
   }
}
