package com.rosshoyt.analysis.web;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.MidiFileAnalysisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class MidiFileAnalysisController {

   private final Logger log = LoggerFactory.getLogger(MidiFileAnalysisController.class);
   private MidiFileAnalysisRepository midiFileAnalysisRepository;

   public MidiFileAnalysisController(MidiFileAnalysisRepository midiFileAnalysisRepository) {
      this.midiFileAnalysisRepository = midiFileAnalysisRepository;
   }

   @GetMapping("/groups")
   Collection<MidiFileAnalysis> groups() {
      return midiFileAnalysisRepository.findAll();
   }

   @GetMapping("/group/{id}")
   ResponseEntity<?> getMidiFileAnalysis(@PathVariable Long id) {
      Optional<MidiFileAnalysis> group = midiFileAnalysisRepository.findById(id);
      return group.map(response -> ResponseEntity.ok().body(response))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
   }

   @PostMapping("/group")
   ResponseEntity<MidiFileAnalysis> createMidiFileAnalysis(@Valid @RequestBody MidiFileAnalysis group) throws URISyntaxException {
      log.info("Request to create group: {}", group);
      MidiFileAnalysis result = midiFileAnalysisRepository.save(group);
      return ResponseEntity.created(new URI("/api/group/" + result.getId()))
            .body(result);
   }

   @PutMapping("/group/{id}")
   ResponseEntity<MidiFileAnalysis> updateMidiFileAnalysis(@Valid @RequestBody MidiFileAnalysis group) {
      log.info("Request to update group: {}", group);
      MidiFileAnalysis result = midiFileAnalysisRepository.save(group);
      return ResponseEntity.ok().body(result);
   }

   @DeleteMapping("/group/{id}")
   public ResponseEntity<?> deleteMidiFileAnalysis(@PathVariable Long id) {
      log.info("Request to delete group: {}", id);
      midiFileAnalysisRepository.deleteById(id);
      return ResponseEntity.ok().build();
   }

//   @RequestMapping(method={RequestMethod.GET})
//   public String index() {
//      return "index";
//   }

}