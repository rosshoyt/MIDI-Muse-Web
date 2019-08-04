package com.rosshoyt.analysis.web;



import com.rosshoyt.analysis.midifile.tools.RawSMFAnalyzer;
import com.rosshoyt.analysis.midifile.tools.exceptions.InvalidMidiFileException;
import com.rosshoyt.analysis.repositories.MidiFileAnalysisRepository;
import com.rosshoyt.analysis.services.MidiFileAnalysisService;
import io.kaitai.struct.KaitaiStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rosshoyt.analysis.storage.StorageFileNotFoundException;

@Controller
@EntityScan
public class FileUploadController {

   private MidiFileAnalysisRepository midiFileAnalysisRepository;
   public static RawSMFAnalyzer rawSMFAnalyzer;

   MidiFileAnalysisService midiFileAnalysisService;



   @Autowired
   public FileUploadController(MidiFileAnalysisService midiFileAnalysisService) {
      this.midiFileAnalysisService = midiFileAnalysisService;
      this.rawSMFAnalyzer = new RawSMFAnalyzer();

   }
/*
   @GetMapping("/")
   public String listUploadedFiles(Model model) throws IOException {

      model.addAttribute("files", storageService.loadAll().map(
            path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                  "serveFile", path.getFileName().toString()).build().toString())
            .collect(Collectors.toList()));

      return "uploadForm";
   }

   @GetMapping("/files/{filename:.+}")
   @ResponseBody
   public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

      Resource file = storageService.loadAsResource(filename);
      return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + file.getFilename() + "\"").body(file);
   }*/
   @CrossOrigin
   @PostMapping("/upload")
   public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                  RedirectAttributes redirectAttributes) {

      System.out.println("In PostMapping /upload with fileName = " + file.getOriginalFilename());

      boolean successfulUpload = true;
      try{
         midiFileAnalysisService.addMidiFile(file);
      } catch(Exception e){
         e.printStackTrace();
         successfulUpload = false;
         if(e instanceof InvalidMidiFileException) {

            redirectAttributes.addFlashAttribute("message", "File format/type not supported");
         } else if(e instanceof KaitaiStream.UnexpectedDataError){

            redirectAttributes.addFlashAttribute("message", "Midi File contained unexpected data");
         }
      }

      //storageService.store(file);


      if(successfulUpload) {
         redirectAttributes.addFlashAttribute("message",
               "You successfully uploaded " + file.getOriginalFilename() + "!");
         System.out.println("Midi File Successfully uploaded and analyzed.");
      }

      return "redirect:/";
   }

   @ExceptionHandler(StorageFileNotFoundException.class)
   public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
      return ResponseEntity.notFound().build();
   }

}