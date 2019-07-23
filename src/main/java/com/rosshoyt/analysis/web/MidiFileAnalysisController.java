package com.rosshoyt.analysis.web;
import com.rosshoyt.analysis.midi_file_tools.MidiFileAnalyzer;
import com.rosshoyt.analysis.midi_file_tools.ParseResult;
import com.rosshoyt.analysis.midi_file_tools.exceptions.InvalidMidiFileException;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.repositories.MidiFileAnalysisRepository;
import io.kaitai.struct.KaitaiStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class MidiFileAnalysisController {

   private final Logger log = LoggerFactory.getLogger(MidiFileAnalysisController.class);
   private MidiFileAnalysisRepository midiFileAnalysisRepository;
   private MidiFileAnalyzer midiFileAnalyzer;

   public MidiFileAnalysisController(MidiFileAnalysisRepository midiFileAnalysisRepository) {
      this.midiFileAnalysisRepository = midiFileAnalysisRepository;
      this.midiFileAnalyzer = new MidiFileAnalyzer();
   }

   @GetMapping("/midifiles")
   Collection<MidiFileAnalysis> groups() {
      return midiFileAnalysisRepository.findAll();
   }

   @GetMapping("/midifile/{id}")
   ResponseEntity<?> getMidiFileAnalysis(@PathVariable String id) {
      Optional<MidiFileAnalysis> group = midiFileAnalysisRepository.findById(Long.parseLong(id));
      return group.map(response -> ResponseEntity.ok().body(response))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
   }
// TODO FIX
//   @CrossOrigin
//   @PostMapping(path = "/uploadmidifile")//, produces = { MediaType.APPLICATION_JSON_VALUE})
//   ResponseEntity uploadMidiFile(@RequestParam("file") MultipartFile file) {
//      log.info("Request to initialParse file", file.getOriginalFilename());
//
//      try {
//         //result =
//         ParseResult parseResult = midiFileAnalyzer.initialParse(file);
//         return ResponseEntity
//               .status(HttpStatus.CREATED)
//               .body(this.midiFileAnalysisRepository.save(midiFileAnalyzer.analyzeParseResult(parseResult)));
//      } catch (Exception e){
//         e.printStackTrace();
//         if(e instanceof InvalidMidiFileException) {
//            return ResponseEntity
//                  .status(HttpStatus.FORBIDDEN)
//                  .body("File type not supported, must be .mid, .midi, or .smf");
//         } else if(e instanceof KaitaiStream.UnexpectedDataError){
//            return ResponseEntity
//                  .status(HttpStatus.FORBIDDEN)
//                  .body("File is not valid " + file.getContentType() + " file, contains unexpected data");
//         }
//         //else if(e instanceof NullPointerException || e instanceof IOException){}
//         else return ResponseEntity
//                     .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                     .body("Internal server error occurred");
//
//      }
//
//   }









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
      midiFileAnalysisRepository.deleteById(id);
      return ResponseEntity.ok().build();
   }

}