package com.rosshoyt.analysis.services;

import com.rosshoyt.analysis.midi_file_tools.MidiFileAnalyzer;
import com.rosshoyt.analysis.midi_file_tools.ParseResult;
import com.rosshoyt.analysis.midi_file_tools.exceptions.InvalidMidiFileException;
import com.rosshoyt.analysis.model.MidiFile;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.repositories.MidiFileAnalysisRepository;
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

   private final MidiFileAnalysisRepository midiFileAnalysisRepository;

   @Autowired
   public MidiFileAnalysisService(MidiFileAnalysisRepository midiFileAnalysisRepository) {
      this.midiFileAnalysisRepository = midiFileAnalysisRepository;
   }





   public List<MidiFileAnalysis> getAllMidiFileAnalyses(){
      List<MidiFileAnalysis> analyses = new ArrayList<>();
      midiFileAnalysisRepository.findAll().forEach(analyses::add);
      return analyses;
   }
   public Optional<MidiFileAnalysis> getMidiFileAnalysis(long id) {
      return midiFileAnalysisRepository.findById(id);
   }

   public List<MidiFile> getAllMidiFiles(){
      List<MidiFile> midiFileList = new ArrayList<>();
      for (MidiFileAnalysis mfa : midiFileAnalysisRepository.findAll()) {
         midiFileList.add(mfa.getMidiFile());
      }
      return midiFileList;
   }


   public MidiFileAnalysis addMidiFile(File file) throws IOException, InvalidMidiFileException {

      ParseResult parseResult= midiFileAnalyzer.initialParse(file);

      MidiFileAnalysis mfa = midiFileAnalysisRepository.save(new MidiFileAnalysis());
      mfa  = midiFileAnalyzer.analyzeParseResult(mfa, parseResult);
      System.out.println(mfa);
      return midiFileAnalysisRepository.save(mfa);

   }
   public MidiFileAnalysis addMidiFile(MultipartFile multipartFile) throws IOException, InvalidMidiFileException {
      ParseResult parseResult= midiFileAnalyzer.initialParse(multipartFile);

      MidiFileAnalysis mfa = midiFileAnalysisRepository.save(new MidiFileAnalysis());
      mfa  = midiFileAnalyzer.analyzeParseResult(mfa, parseResult);
      System.out.println(mfa);
      return midiFileAnalysisRepository.save(mfa);
   }
   public void updateBook(Long id, MidiFileAnalysis midiFileAnalysis) {
      midiFileAnalysisRepository.save(midiFileAnalysis);
   }

   public void deleteMidiFileAnalysis(Long id) {
      midiFileAnalysisRepository.deleteById(id);
   }

   private static MidiFileAnalyzer midiFileAnalyzer = new MidiFileAnalyzer();
}
