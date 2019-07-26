package com.rosshoyt.analysis.services;

import com.rosshoyt.analysis.midi_file_tools.MidiFileAnalyzer;
import com.rosshoyt.analysis.midi_file_tools.MidiFileValidator;
import com.rosshoyt.analysis.midi_file_tools.ParseResult;
import com.rosshoyt.analysis.midi_file_tools.exceptions.InvalidMidiFileException;
import com.rosshoyt.analysis.midi_file_tools.exceptions.UnexpectedMidiDataException;
import com.rosshoyt.analysis.model.MidiFile;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.MusicalAnalysis;
import com.rosshoyt.analysis.model.RawAnalysis;
import com.rosshoyt.analysis.repositories.MidiFileAnalysisRepository;
import com.rosshoyt.analysis.repositories.MidiFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO based on https://technology.amis.nl/2019/02/26/building-a-restful-web-service-with-spring-boot-using-an-h2-in-memory-database-and-also-an-external-mysql-database/
@Service
public class MidiFileAnalysisService {



   @Autowired
   public MidiFileAnalysisService(MidiFileAnalysisRepository midiFileAnalysisRepository, MidiFileRepository midiFileRepository) {
      this.midiFileAnalysisRepository = midiFileAnalysisRepository;
      this.midiFileRepository = midiFileRepository;
   }





   public List<MidiFileAnalysis> getAllMidiFileAnalyses() {
      List<MidiFileAnalysis> analyses = new ArrayList<>();
      midiFileAnalysisRepository.findAll().forEach(analyses::add);
      return analyses;
   }
   public Optional<MidiFileAnalysis> getMidiFileAnalysis(long id) {
      return midiFileAnalysisRepository.findById(id);
   }

   public List<MidiFile> getAllMidiFiles(){
      List<MidiFile> midiFileList= new ArrayList<>();

      midiFileRepository.findAll().forEach(midiFileList::add);

      return midiFileList;
   }


   public MidiFileAnalysis addMidiFile(File file) throws IOException, InvalidMidiFileException, UnexpectedMidiDataException {
      return addMidiFile(midiFileValidator.validate(file));
   }
   public MidiFileAnalysis addMidiFile(MultipartFile multipartFile) throws IOException, InvalidMidiFileException, UnexpectedMidiDataException {
      return addMidiFile(midiFileValidator.validate(multipartFile));
   }
   private MidiFileAnalysis addMidiFile(ParseResult parseResult){
      // File Has been validated,  saving a record to DB
      MidiFileAnalysis mfa = midiFileAnalysisRepository.save(new MidiFileAnalysis());

      // Create other DB entries with PK of MFA entry
      System.out.println("Adding MidiFile to DB");
      MidiFile midiFile = midiFileRepository.save(new MidiFile(mfa.getId(), parseResult.data, parseResult.fileName, parseResult.extension));
      // Create analysis tables
      System.out.println("Analyzing Raw SMF parse");
      RawAnalysis rawAnalysis = midiFileAnalyzer.analyzeRaw(parseResult.smf);
      System.out.println("Analyzing musical data");
      MusicalAnalysis musicalAnalysis = midiFileAnalyzer.analyzeMusic(parseResult.smf);
      System.out.println("...Setting IDs manually... [Refactor for automatic ID gen]");
      rawAnalysis.setId(mfa.getId());
      musicalAnalysis.setId(mfa.getId());
      mfa.setRawAnalysis(rawAnalysis);
      mfa.setMusicalAnalysis(musicalAnalysis);
      mfa.setMidiFile(midiFile);

      System.out.println("Midi File Analysis results: "+ mfa);
      System.out.println("Updating MFA DB Entry");
      return midiFileAnalysisRepository.save(mfa);
   }
   public void updateMidiFileAnalysis(Long id, MidiFileAnalysis midiFileAnalysis) {
      midiFileAnalysisRepository.save(midiFileAnalysis);
   }

   public void deleteMidiFileAnalysis(Long id) {
      midiFileAnalysisRepository.deleteById(id);
   }

   // Repos
   private final MidiFileAnalysisRepository midiFileAnalysisRepository;
   private final MidiFileRepository midiFileRepository;

   // Utilities
   private static MidiFileAnalyzer midiFileAnalyzer = new MidiFileAnalyzer();
   private static MidiFileValidator midiFileValidator = new MidiFileValidator();
}
