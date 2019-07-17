package com.rosshoyt.analysis.utils;

import com.rosshoyt.analysis.model.MidiFile;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MidiFileUtils {
   // Reference values
   public static final String[] MIDI_FILE_EXTENSIONS_SUPPORTED =  { "mid", "midi", "smf"};
   private static FileExtensionValidator extensionValidator = new FileExtensionValidator(MIDI_FILE_EXTENSIONS_SUPPORTED);


   // Validation
   public static boolean isValidSMF(File file) {
      return file != null && extensionValidator.fileHasSupportedExtension(file);

   }
   public static boolean isValidSMF(MultipartFile multipartFile) {
      return multipartFile != null && extensionValidator.multipartFileHasSupportedExtension(multipartFile);
   }

   // Utilities
   public static byte[] fileToByteArray(File file) throws IOException {
      InputStream targetStream = new FileInputStream(file);
      return IOUtils.toByteArray(targetStream);
   }

   public static String getExtension(File file) {
      return FilenameUtils.getExtension(file.getName());
   }
   public static String getFileNameWithoutExtension(String fullFileName){
      return FilenameUtils.removeExtension(fullFileName);
   }
}
