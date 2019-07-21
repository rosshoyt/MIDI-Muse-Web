package com.rosshoyt.analysis.web;
import com.rosshoyt.analysis.midi_file_tools.MidiFileAnalyzer;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.repositories.MidiFileAnalysisRepository;
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

//   @PostMapping("/uploadmidifile")
//   ResponseEntity<MidiFileAnalysis> createMidiFileAnalysis(@Valid @RequestBody MidiFileAnalysis group) throws URISyntaxException {
//      log.info("Request to create midiFileAnalysis: {}", group);
//      MidiFileAnalysis result = midiFileAnalysisRepository.save(midiFileAnalyzer.analyze());
//      return ResponseEntity.created(new URI("/api/group/" + result.getId()))
//            .body(result);
//   }

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

}