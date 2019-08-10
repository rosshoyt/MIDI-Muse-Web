package com.rosshoyt.analysis.services;

import com.rosshoyt.analysis.midifile.tools.musicanalysis.MusicAnalyzer;
import com.rosshoyt.analysis.midifile.tools.rawanalysis.SMFAnalyzer;
import com.rosshoyt.analysis.midifile.tools.MidiFileValidatorParser;
import com.rosshoyt.analysis.model.internal.ValidatedParseResult;
import com.rosshoyt.analysis.midifile.tools.exceptions.InvalidMidiFileException;
import com.rosshoyt.analysis.midifile.tools.exceptions.UnexpectedMidiDataException;
import com.rosshoyt.analysis.model.*;
import com.rosshoyt.analysis.model.file.MidiFileDetail;
import com.rosshoyt.analysis.model.kaitai.smf.RawAnalysis;
import com.rosshoyt.analysis.repositories.MidiFileAnalysisRepository;
import com.rosshoyt.analysis.repositories.file.MidiFileDetailRepository;
import com.rosshoyt.analysis.repositories.music.MusicalAnalysisRepository;
import com.rosshoyt.analysis.repositories.raw.RawAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Main aggregating web service for MidiAnalyzer.com
 * TODO based on https://technology.amis.nl/2019/02/26/building-a-restful-web-service-with-spring-boot-using-an-h2-in-memory-database-and-also-an-external-mysql-database/
 */

@Service
@Transactional
public class MidiFileAnalysisService {

   // CRUD Repos
   private final MidiFileAnalysisRepository midiFileAnalysisRepository;
   private final MusicalAnalysisRepository musicalAnalysisRepository;
   //private final MidiFileDetailRepository midiFileDetailRepository;
   //private final RawAnalysisRepository rawAnalysisRepository;
   // Helper services
   private final RawAnalysisService rawAnalysisService;
   private final MusicalAnalysisService musicalAnalysisService;
   private final MidiFileDetailService midiFileDetailService;

   // Utilities
   private static MidiFileValidatorParser midiFileValidatorParser = new MidiFileValidatorParser();

   @Autowired
   public MidiFileAnalysisService(MidiFileAnalysisRepository midiFileAnalysisRepository, /*MidiFileDetailRepository midiFileDetailRepository,
                                  RawAnalysisRepository rawAnalysisRepository,*/ MusicalAnalysisRepository musicalAnalysisRepository,
                                  RawAnalysisService rawAnalysisService, MusicalAnalysisService musicalAnalysisService, MidiFileDetailService midiFileDetailService) {
      this.midiFileAnalysisRepository = midiFileAnalysisRepository;
      //this.midiFileDetailRepository = midiFileDetailRepository;
      //this.rawAnalysisRepository = rawAnalysisRepository;
      this.musicalAnalysisRepository = musicalAnalysisRepository;
      this.rawAnalysisService = rawAnalysisService;
      this.musicalAnalysisService = musicalAnalysisService;
      this.midiFileDetailService = midiFileDetailService;
   }
   //@Transactional
   public List<MidiFileAnalysis> getAllMidiFileAnalyses() {
      List<MidiFileAnalysis> analyses = new ArrayList<>();
      midiFileAnalysisRepository.findAll().forEach(mfa ->
      {
         mfa.setMusicalAnalysis(musicalAnalysisService.findMusicalAnalysisByMFA(mfa).get());
         mfa.setRawAnalysis(rawAnalysisService.findRawAnalysisByMFA(mfa).get());
         analyses.add(mfa);
      });

      return analyses;
   }

   public Optional<MidiFileAnalysis> getMidiFileAnalysis(long id) {
      return midiFileAnalysisRepository.findById(id);
   }

   public List<MidiFileDetail> getMidiFileDetailList(){
      return midiFileDetailService.getMidiFileDetailList();
   }

   public MidiFileAnalysis addMidiFile(File file) throws IOException, InvalidMidiFileException, UnexpectedMidiDataException {
      return addMidiFile(midiFileValidatorParser.validateAndParse(file));
   }
   public MidiFileAnalysis addMidiFile(MultipartFile multipartFile) throws IOException, InvalidMidiFileException, UnexpectedMidiDataException {
      return addMidiFile(midiFileValidatorParser.validateAndParse(multipartFile));
   }

   public MidiFileAnalysis addMidiFile(ValidatedParseResult parseResult){
      // File Has been validated in parse(), saving record to DB
      MidiFileAnalysis mfa = midiFileAnalysisRepository.save(new MidiFileAnalysis()); // Getting base entry



      // Raw Analysis TODO Methodize
      System.out.println("Analyzing Raw SMF parse");
      RawAnalysis rawAnalysis = rawAnalysisService.addRawAnalysis(mfa, parseResult);
      mfa.setRawAnalysis(rawAnalysis);
      mfa = midiFileAnalysisRepository.save(mfa);

      /* Create other DB entries with PK of MFA entry */
      System.out.println("Persisting File Byte Data...");
      MidiFileDetail midiFileDetail = midiFileDetailService.addMidiFileDetail(mfa, parseResult);
      mfa.setMidiFileDetail(midiFileDetail);
      mfa = midiFileAnalysisRepository.save(mfa);

      // Musical Analysis TODO Methodize
      System.out.println("Analyzing musical data...");

      //System.out.println("...Setting IDs manually... [Refactor for automatic ID gen]");
      //musicalAnalysis.setId(mfa.getId());
      mfa.setMusicalAnalysis(musicalAnalysisService.addMusicalAnalysis(mfa, rawAnalysis));
      System.out.println("Updating MFA DB Entry");
      mfa = midiFileAnalysisRepository.save(mfa);


      //mfa.getMusicalAnalysis();
      System.out.println("Midi File Analysis results: " + mfa);
      return mfa;
   }
   public void updateMidiFileAnalysis(Long id, MidiFileAnalysis midiFileAnalysis) {
      midiFileAnalysisRepository.save(midiFileAnalysis);
   }

   public void deleteMidiFileAnalysis(Long id) {
      midiFileAnalysisRepository.deleteById(id);
   }



}
