package com.rosshoyt.analysis.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {


   // Utilities
   public static byte[] getByteArray(File file) throws IOException {
      InputStream targetStream = new FileInputStream(file);
      return IOUtils.toByteArray(targetStream);
   }
   public static byte[] getByteArray(MultipartFile multipartFile) throws IOException {
      return multipartFile.getBytes();
   }

   public static String getExtension(File file) {
      return FilenameUtils.getExtension(file.getName());
   }
   public static String getFileNameWithoutExtension(String fullFileName){
      return FilenameUtils.removeExtension(fullFileName);
   }
}
