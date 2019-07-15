package com.rosshoyt.analysis.utils;

import com.google.common.io.Files;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FileExtensionValidator {

   //private static final Set<String> SUPPORTED_FILE_EXTENSIONS = Sets.newHashSet("mid", "midi");
   private static Set<String> SUPPORTED_FILE_EXTENSIONS;
   /**
    * Validates a @java.io.file object for use in domain model
    * Also Checks for null value
    * @param fileExtensions list of file extensions to allow, no period eg "mid"
    */
   public FileExtensionValidator(String[] fileExtensions) {
      {
         SUPPORTED_FILE_EXTENSIONS = new HashSet<String>(Arrays.asList(fileExtensions));
      }
   }

   public boolean isValid(File file) {
      if(file != null && SUPPORTED_FILE_EXTENSIONS.contains(Files.getFileExtension(file.getName())))
         return true;
      return false;
   }
}