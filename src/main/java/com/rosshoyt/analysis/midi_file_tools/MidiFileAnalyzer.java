package com.rosshoyt.analysis.midi_file_tools;



import com.rosshoyt.analysis.midi_file_tools.exceptions.BrokenFileException;
import com.rosshoyt.analysis.midi_file_tools.exceptions.InvalidMidiFileException;
import com.rosshoyt.analysis.model.MidiFile;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.MidiHeaderProperties;
import com.rosshoyt.analysis.utils.FileExtensionValidator;
import com.rosshoyt.analysis.web.FileUploadController;
import org.omg.CORBA.DynAnyPackage.Invalid;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
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
   private static FileExtensionValidator midiFileValidator = new FileExtensionValidator(FileUploadController.MIDI_FILE_EXTENSIONS_SUPPORTED);

   public static boolean validateMidiFile(File file){
      return midiFileValidator.isValid(file);
   }


   /**
    *
    */
   public MidiFileAnalyzer() { }

   public MidiFileAnalysis analyze(File midiFile) throws InvalidMidiFileException, InvalidMidiDataException, IOException {
      com.rosshoyt.analysis.midi_midiFile_tools.MidiFileParser parser = new com.rosshoyt.analysis.midi_midiFile_tools.MidiFileParser(); //TODO make static/global?
      MidiFileAnalysis analysis = parser.parseMidi(midiFile);

   }
   private Properties parseHeader() {
      return null;
   }
}
