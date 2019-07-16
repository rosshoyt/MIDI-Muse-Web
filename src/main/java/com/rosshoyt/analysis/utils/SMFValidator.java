package com.rosshoyt.analysis.utils;

import java.io.File;

/**
 * Validates a Standard Midi File (.mid, .midi, .sfm)
 */
public class SMFValidator {
   // Reference values
   public static final String[] MIDI_FILE_EXTENSIONS_SUPPORTED =  { "mid", "midi", "smf"};
   private FileExtensionValidator extensionValidator = new FileExtensionValidator(MIDI_FILE_EXTENSIONS_SUPPORTED);

   public boolean isValidSMF(File file) {
      return extensionValidator.isValid(file);
   }



}
