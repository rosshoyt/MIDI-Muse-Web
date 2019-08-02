package com.rosshoyt.analysis.web;
import com.rosshoyt.analysis.midifile.tools.exceptions.InvalidMidiFileException;
import com.rosshoyt.analysis.model.file.FileByteData;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.file.MidiFileDetail;
import com.rosshoyt.analysis.services.MidiFileAnalysisService;

import io.kaitai.struct.KaitaiStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class MidiFileAnalysisController {

   private final Logger log = LoggerFactory.getLogger(MidiFileAnalysisController.class);
   //private MidiFileAnalysisRepository midiFileAnalysisRepository;


   @Autowired
   private MidiFileAnalysisService midiFileAnalysisService;


   @GetMapping("/midifiles")
   List<MidiFileDetail> getAllMidiFiles() {
      return midiFileAnalysisService.getMidiFileDetailList();
   }

   @GetMapping("/midifileanalysis/{id}")
   ResponseEntity<?> getMidiFileAnalysis(@PathVariable String id) {
      Optional<MidiFileAnalysis> group = midiFileAnalysisService.getMidiFileAnalysis(Long.parseLong(id));
      return group.map(response -> ResponseEntity.ok().body(response))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
   }


   @CrossOrigin
   @PostMapping(path = "/uploadmidifile")//, produces = { MediaType.APPLICATION_JSON_VALUE})
   ResponseEntity uploadMidiFile(@RequestParam("file") MultipartFile file) {
      //log.info("Request to initialParse file", file.getOriginalFilename());
      try {
         System.out.println("Request to upload file " +  file.getOriginalFilename());
         //result =
         MidiFileAnalysis midiFileAnalysis = midiFileAnalysisService.addMidiFile(file);
         return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(midiFileAnalysis);
      } catch (Exception e){
         e.printStackTrace();
         if(e instanceof InvalidMidiFileException) {
            return ResponseEntity
                  .status(HttpStatus.FORBIDDEN)
                  .body("File type not supported, must be .mid, .midi, or .smf");
         } else if(e instanceof KaitaiStream.UnexpectedDataError){
            return ResponseEntity
                  .status(HttpStatus.FORBIDDEN)
                  .body("File is not valid " + file.getContentType() + " file, contains unexpected data");
         }
         //else if(e instanceof NullPointerException || e instanceof IOException){}
         else return ResponseEntity
                     .status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body("Internal server error");

      }

   }









//   // Todo implement in above method
//   @RequestMapping(method = RequestMethod.GET)
//   public ResponseEntity getUser(@RequestHeader(value="Access-key") String accessKey,
//                                 @RequestHeader(value="Secret-key") String secretKey) {
//      try {
//         // see note 1
//         return ResponseEntity
//               .status(HttpStatus.CREATED)
//               .body(this.userService.chkCredentials(accessKey, secretKey, timestamp));
//      }
//      catch(ChekingCredentialsFailedException e) {
//         e.printStackTrace(); // see note 2
//         return ResponseEntity
//               .status(HttpStatus.FORBIDDEN)
//               .body("Error Message");
//      }
//   }

//   @PutMapping("/group/{id}")
//   ResponseEntity<MidiFileAnalysis> updateMidiFileAnalysis(@Valid @RequestBody MidiFileAnalysis group) {
//      log.info("Request to update group: {}", group);
//      MidiFileAnalysis result = midiFileAnalysisRepository.save(group);
//      return ResponseEntity.ok().body(result);
//   }

   @DeleteMapping("/group/{id}")
   public ResponseEntity<?> deleteMidiFileAnalysis(@PathVariable Long id) {
      log.info("Request to delete group: {}", id);
      midiFileAnalysisService.deleteMidiFileAnalysis(id);
      return ResponseEntity.ok().build();
   }

}