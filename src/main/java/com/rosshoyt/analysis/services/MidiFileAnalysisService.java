package com.rosshoyt.analysis.services;

import com.rosshoyt.analysis.exceptions.InvalidMidiFileDataException;
import com.rosshoyt.analysis.model.internal.ValidParseResultContainer;
import com.rosshoyt.analysis.exceptions.UnexpectedMidiDataException;
import com.rosshoyt.analysis.model.*;
import com.rosshoyt.analysis.model.file.MidiFileDetail;
import com.rosshoyt.analysis.model.raw.RawAnalysis;
import com.rosshoyt.analysis.repositories.MidiFileAnalysisRepository;
import com.rosshoyt.analysis.services.file.MidiFileDetailService;
import com.rosshoyt.analysis.services.file.MidiFileValidatingParserService;
import com.rosshoyt.analysis.services.music.MusicalAnalysisService;
import com.rosshoyt.analysis.services.raw.RawAnalysisService;
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

   // Helper services
   private final RawAnalysisService rawAnalysisService;
   private final MusicalAnalysisService musicalAnalysisService;
   private final MidiFileDetailService midiFileDetailService;

   // Utilities
   private static MidiFileValidatingParserService midiFileValidatingParserService = new MidiFileValidatingParserService();

   @Autowired
   public MidiFileAnalysisService(MidiFileAnalysisRepository midiFileAnalysisRepository, RawAnalysisService rawAnalysisService,
                                  MusicalAnalysisService musicalAnalysisService, MidiFileDetailService midiFileDetailService) {
      this.midiFileAnalysisRepository = midiFileAnalysisRepository;
      //this.midiFileDetailRepository = midiFileDetailRepository;
      //this.rawAnalysisRepository = rawAnalysisRepository;
      //this.musicalAnalysisRepository = musicalAnalysisRepository;
      this.rawAnalysisService = rawAnalysisService;
      this.musicalAnalysisService = musicalAnalysisService;
      this.midiFileDetailService = midiFileDetailService;
   }

   public List<MidiFileAnalysis> getAllMidiFileAnalyses() {
      List<MidiFileAnalysis> analyses = new ArrayList<>();
      midiFileAnalysisRepository.findAll().forEach(mfa ->
      {
         mfa.setMusicalAnalysis(musicalAnalysisService.findMusicalAnalysisByMFA(mfa).orElse(null));
         mfa.setRawAnalysis(rawAnalysisService.getRawAnalysisByFkMidiFileAnalysisId(mfa.getId()).orElse(null));
         analyses.add(mfa);
      });
      return analyses;
   }

   public Optional<MidiFileAnalysis> getMidiFileAnalysis(long id) {
      Optional<MidiFileAnalysis> optionalMFA = midiFileAnalysisRepository.findById(id);
      System.out.println("User requested MidiFileAnalysis w/ id " + id);
      if(optionalMFA.isPresent()){
         //MidiFileAnalysis mfa = optionalMFA.get();
         //Optional<RawAnalysis> optionalRA = rawAnalysisService.findRawAnalysisByMFA(mfa);
         //RawAnalysis rawAnalysis = optionalRA.get();
         optionalMFA.get().setRawAnalysis(rawAnalysisService.getRawAnalysisByFkMidiFileAnalysisId(optionalMFA.get().getId()).get());
         optionalMFA.get().setMusicalAnalysis(musicalAnalysisService.findMusicalAnalysisByMFA(optionalMFA.get()).get());
         optionalMFA.get().setMidiFileDetail(midiFileDetailService.findMidiFileDetailByMFA(optionalMFA.get()).get());
         System.out.println("Returning Midi File analysis: " + optionalMFA.get());
      } else System.out.println("Midi File Analysis not found");

      return optionalMFA;
   }

   public List<MidiFileDetail> getMidiFileDetailList(){
      return midiFileDetailService.getMidiFileDetailList();
   }

   public MidiFileAnalysis addMidiFile(File file) throws IOException, InvalidMidiFileDataException, UnexpectedMidiDataException {
      return addMidiFile(MidiFileValidatingParserService.validateAndParse(file));
   }
   public MidiFileAnalysis addMidiFile(MultipartFile multipartFile) throws IOException, InvalidMidiFileDataException, UnexpectedMidiDataException {
      return addMidiFile(MidiFileValidatingParserService.validateAndParse(multipartFile));
   }

   public MidiFileAnalysis addMidiFile(ValidParseResultContainer parseResult){
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


      System.out.println("Analyzing musical data...");
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
