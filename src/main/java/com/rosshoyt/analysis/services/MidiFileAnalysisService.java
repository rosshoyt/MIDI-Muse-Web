package com.rosshoyt.analysis.services;

import com.rosshoyt.analysis.midifile.tools.musicanalysis.MusicAnalyzer;
import com.rosshoyt.analysis.midifile.tools.SMFAnalyzer;
import com.rosshoyt.analysis.midifile.tools.MidiFileValidatorParser;
import com.rosshoyt.analysis.model.internal.ValidatedParseResult;
import com.rosshoyt.analysis.midifile.tools.exceptions.InvalidMidiFileException;
import com.rosshoyt.analysis.midifile.tools.exceptions.UnexpectedMidiDataException;
import com.rosshoyt.analysis.model.*;
import com.rosshoyt.analysis.model.file.MidiFileDetail;
import com.rosshoyt.analysis.model.kaitai.smf.RawAnalysis;
import com.rosshoyt.analysis.repositories.MidiFileAnalysisRepository;
import com.rosshoyt.analysis.repositories.MidiFileDetailRepository;
import com.rosshoyt.analysis.repositories.music.MusicalAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class MidiFileAnalysisService {

   // CRUD Repos
   private final MidiFileAnalysisRepository midiFileAnalysisRepository;
   private final MidiFileDetailRepository midiFileDetailRepository;
   private final MusicalAnalysisRepository musicalAnalysisRepository;
   //private final RawAnalysisRepository rawAnalysisRepository;
   // Helper services
   private final RawAnalysisService rawAnalysisService;
   private final MusicalAnalysisService musicalAnalysisService;

   // Utilities

   private static MusicAnalyzer musicAnalyzer;
   private static MidiFileValidatorParser midiFileValidatorParser = new MidiFileValidatorParser();

   @Autowired
   public MidiFileAnalysisService(MidiFileAnalysisRepository midiFileAnalysisRepository, MidiFileDetailRepository midiFileDetailRepository,
         /*RawAnalysisRepository rawAnalysisRepository,*/ MusicalAnalysisRepository musicalAnalysisRepository,
                                  RawAnalysisService rawAnalysisService, MusicalAnalysisService musicalAnalysisService) {
      this.midiFileAnalysisRepository = midiFileAnalysisRepository;
      this.midiFileDetailRepository = midiFileDetailRepository;
      //this.rawAnalysisRepository = rawAnalysisRepository;
      this.musicalAnalysisRepository = musicalAnalysisRepository;
      this.rawAnalysisService = rawAnalysisService;
      this.musicalAnalysisService = musicalAnalysisService;
   }

   public List<MidiFileAnalysis> getAllMidiFileAnalyses() {
      List<MidiFileAnalysis> analyses = new ArrayList<>();
      midiFileAnalysisRepository.findAll().forEach(analyses::add);
      return analyses;
   }

   public Optional<MidiFileAnalysis> getMidiFileAnalysis(long id) {
      return midiFileAnalysisRepository.findById(id);
   }

   public List<MidiFileDetail> getMidiFileDetailList(){
      List<MidiFileDetail> midiFileDetailList = new ArrayList<>();
      midiFileDetailRepository.findAll().forEach(midiFileDetailList::add);
      return midiFileDetailList;
   }

   public MidiFileAnalysis addMidiFile(File file) throws IOException, InvalidMidiFileException, UnexpectedMidiDataException {
      return addMidiFile(midiFileValidatorParser.validateAndParse(file));
   }
   public MidiFileAnalysis addMidiFile(MultipartFile multipartFile) throws IOException, InvalidMidiFileException, UnexpectedMidiDataException {
      return addMidiFile(midiFileValidatorParser.validateAndParse(multipartFile));
   }
   private MidiFileAnalysis addMidiFile(ValidatedParseResult parseResult){
      // File Has been validated in parse(), saving record to DB
      MidiFileAnalysis mfa = midiFileAnalysisRepository.save(new MidiFileAnalysis()); // Getting base entry



      // Raw Analysis TODO Methodize
      System.out.println("Analyzing Raw SMF parse");
      RawAnalysis rawAnalysis = rawAnalysisService.addRawAnalysis(mfa.getId(), parseResult);
      mfa.setRawAnalysis(rawAnalysis);
      mfa = midiFileAnalysisRepository.save(mfa);

      /* Create other DB entries with PK of MFA entry */
      System.out.println("Persisting File Byte Data...");
      MidiFileDetail midiFileDetail = SMFAnalyzer.getMidiFileDetail(parseResult.fileName, parseResult.extension,
                                 rawAnalysis, parseResult.data);
      midiFileDetail = midiFileDetailRepository.save(midiFileDetail);
      mfa.setMidiFileDetail(midiFileDetail);
      mfa= midiFileAnalysisRepository.save(mfa);

      // Musical Analysis TODO Methodize
      System.out.println("Analyzing musical data...");

      //System.out.println("...Setting IDs manually... [Refactor for automatic ID gen]");
      //musicalAnalysis.setId(mfa.getId());
      mfa.setMusicalAnalysis(musicalAnalysisService.addMusicalAnalysis(rawAnalysis));
      System.out.println("Updating MFA DB Entry");
      mfa = midiFileAnalysisRepository.save(mfa);

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
