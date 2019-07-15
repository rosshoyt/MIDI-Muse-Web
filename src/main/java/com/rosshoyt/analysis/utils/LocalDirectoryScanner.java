package com.rosshoyt.analysis.utils;

import com.rosshoyt.analysis.web.FileUploadController;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;
//TODO refactor file validation to use FileExtensionValidator
public class LocalDirectoryScanner {
   private Path root;

   private static Set<String> extensionsToFind;
   private static ArrayList<File> targetFiles;
   private static Set<String> MIDI_FILE_EXTENSION;
   public LocalDirectoryScanner(String root, String extension){
      this.root = Paths.get(root);
      extensionsToFind = new HashSet<>();
      extensionsToFind.add(extension);
      targetFiles = new ArrayList<>();
   }
   public LocalDirectoryScanner(String root, String[] extensions){
      this.root = Paths.get(root);
      extensionsToFind = new HashSet<>(Arrays.asList(extensions));

      targetFiles = new ArrayList<>();
   }
   public LocalDirectoryScanner(String root, Set<String> extensionsToFind){
      this.root = Paths.get(root);
      this.extensionsToFind = extensionsToFind;
      targetFiles = new ArrayList<>();
   }

   public List<File> getFiles(){
      return getMidiFileResources();
   }

   private List<File> getMidiFileResources(){
      System.out.println("\nScanning for resource folder for midi files:");
      List<File> all = new ArrayList<>();
      try {
         addTree(root, all);
      } catch(IOException e) {
         e.printStackTrace();
      }

      return all;
   }
   private static void addTree(Path directory, Collection<File> all)
         throws IOException {

      try (DirectoryStream<Path> ds = Files.newDirectoryStream(directory)) {
         for (Path child : ds) {
            boolean isDirectory = false;

            if (Files.isDirectory(child)) {
               //System.out.println("In directory " + child);
               isDirectory = true;
               addTree(child, all); //recursive call when not at leaf node
            } else {
               isDirectory = false;
               if(extensionsToFind.contains(FilenameUtils.getExtension(child.toString()))){
                  all.add(child.toFile());
                  System.out.println("Found target file type, adding to target file list:\n " + child);
               } else{
                  //System.out.println("Other file found: " + child);
               }
            }
         }
      }
   }

}
