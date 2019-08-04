package com.rosshoyt.analysis.services;

import com.rosshoyt.analysis.midifile.tools.RawSMFAnalyzer;
import com.rosshoyt.analysis.midifile.tools.ValidatedParseResult;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.kaitai.smf.RawAnalysis;
import com.rosshoyt.analysis.repositories.RawAnalysisRepository;
import com.rosshoyt.analysis.repositories.TrackAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RawAnalysisService {
   // CRUD Repos
   private final RawAnalysisRepository rawAnalysisRepository;
   private final TrackAnalysisRepository trackAnalysisRepository;

   // Utilities
   //private static RawSMFAnalyzer rawSMFAnalyzer = new RawSMFAnalyzer();
   @Autowired
   public RawAnalysisService(RawAnalysisRepository rawAnalysisRepository, TrackAnalysisRepository trackAnalysisRepository) {
      this.rawAnalysisRepository = rawAnalysisRepository;
      this.trackAnalysisRepository = trackAnalysisRepository;
   }


   public RawAnalysis addRawAnalysis(Long midiFileAnalysisId, ValidatedParseResult parseResult) {
      RawAnalysis rawAnalysis = rawAnalysisRepository.save(new RawAnalysis());
      rawAnalysis = RawSMFAnalyzer.analyzeRaw(parseResult.smf, midiFileAnalysisId, rawAnalysis);
      return rawAnalysisRepository.save(rawAnalysis);
   }
}
