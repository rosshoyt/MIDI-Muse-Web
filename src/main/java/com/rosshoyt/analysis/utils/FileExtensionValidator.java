package com.rosshoyt.analysis.utils;

import com.google.common.io.Files;
import org.springframework.web.multipart.MultipartFile;

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

   public boolean fileHasSupportedExtension(File file) {
      return SUPPORTED_FILE_EXTENSIONS.contains(Files.getFileExtension(file.getName()));
   }
   public boolean multipartFileHasSupportedExtension(MultipartFile multipartFile){
      return SUPPORTED_FILE_EXTENSIONS.contains(multipartFile.getContentType());
   }
   public boolean isSupportedExtension(String extension) {
      return SUPPORTED_FILE_EXTENSIONS.contains(extension);
   }
}