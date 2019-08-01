package com.rosshoyt.analysis.services;

import com.rosshoyt.analysis.midifile.tools.MidiFileAnalyzer;
import com.rosshoyt.analysis.midifile.tools.MidiFileValidator;
import com.rosshoyt.analysis.midifile.tools.ParseResult;
import com.rosshoyt.analysis.midifile.tools.exceptions.InvalidMidiFileException;
import com.rosshoyt.analysis.midifile.tools.exceptions.UnexpectedMidiDataException;
import com.rosshoyt.analysis.model.*;
import com.rosshoyt.analysis.model.file.FileByteData;
import com.rosshoyt.analysis.model.file.MidiFileDetail;
import com.rosshoyt.analysis.model.musical.MusicalAnalysis;
import com.rosshoyt.analysis.model.raw.OldRawAnalysis;
import com.rosshoyt.analysis.repositories.MidiFileAnalysisRepository;
import com.rosshoyt.analysis.repositories.FileDetailRepository;
import com.rosshoyt.analysis.repositories.RawAnalysisRepository;
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
   // CRUD Repos
   private final MidiFileAnalysisRepository midiFileAnalysisRepository;
   private final FileDetailRepository fileDetailRepository;
   private final RawAnalysisRepository rawAnalysisRepository;

   @Autowired
   public MidiFileAnalysisService(MidiFileAnalysisRepository midiFileAnalysisRepository, FileDetailRepository fileDetailRepository,
                                  RawAnalysisRepository rawAnalysisRepository) {
      this.midiFileAnalysisRepository = midiFileAnalysisRepository;
      this.fileDetailRepository = fileDetailRepository;
      this.rawAnalysisRepository = rawAnalysisRepository;
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
      fileDetailRepository.findAll().forEach(midiFileDetailList::add);
      return midiFileDetailList;
   }

   public MidiFileAnalysis addMidiFile(File file) throws IOException, InvalidMidiFileException, UnexpectedMidiDataException {
      return addMidiFile(midiFileValidator.validate(file));
   }
   public MidiFileAnalysis addMidiFile(MultipartFile multipartFile) throws IOException, InvalidMidiFileException, UnexpectedMidiDataException {
      return addMidiFile(midiFileValidator.validate(multipartFile));
   }
   private MidiFileAnalysis addMidiFile(ParseResult parseResult){
      // File Has been validated in parse(), saving record to DB
      MidiFileAnalysis mfa = midiFileAnalysisRepository.save(new MidiFileAnalysis()); // Getting base entry

      /* Create other DB entries with PK of MFA entry */
      MidiFileDetail midiFileDetail = midiFileAnalyzer.getMidiFileDetail(parseResult.smf, parseResult.fileName, parseResult.extension);
      System.out.println("Persisting File Byte Data...");
      midiFileDetail.setFileByteData(new FileByteData(mfa.getId(), parseResult.data));
      fileDetailRepository.save(midiFileDetail);

      // Create analysis tables
      System.out.println("Analyzing Raw SMF parse");
      OldRawAnalysis oldRawAnalysis = midiFileAnalyzer.analyzeRaw(parseResult.smf);
      System.out.println("Analyzing musical data");
      MusicalAnalysis musicalAnalysis = midiFileAnalyzer.analyzeMusic(oldRawAnalysis);
      System.out.println("...Setting IDs manually... [Refactor for automatic ID gen]");
      oldRawAnalysis.setId(mfa.getId());
      rawAnalysisRepository.save(oldRawAnalysis);
      musicalAnalysis.setId(mfa.getId());
      mfa.setOldRawAnalysis(oldRawAnalysis);
      mfa.setMusicalAnalysis(musicalAnalysis);
      mfa.setMidiFileDetail(midiFileDetail);

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



   // Utilities
   private static MidiFileAnalyzer midiFileAnalyzer = new MidiFileAnalyzer();
   private static MidiFileValidator midiFileValidator = new MidiFileValidator();
}
