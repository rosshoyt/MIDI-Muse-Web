package com.rosshoyt.analysis.services;

import com.rosshoyt.analysis.midifile.tools.SMFAnalyzer;
import com.rosshoyt.analysis.model.internal.ValidatedParseResult;
import com.rosshoyt.analysis.model.kaitai.smf.RawAnalysis;
import com.rosshoyt.analysis.repositories.raw.RawAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RawAnalysisService {
   // CRUD Repos
   private final RawAnalysisRepository rawAnalysisRepository;
   //private final TrackAnalysisRepository trackAnalysisRepository;

   // Utilities
   //private static RawSMFAnalyzer rawSMFAnalyzer = new RawSMFAnalyzer();
   @Autowired
   public RawAnalysisService(RawAnalysisRepository rawAnalysisRepository/*, TrackAnalysisRepository trackAnalysisRepository*/) {
      this.rawAnalysisRepository = rawAnalysisRepository;
      //this.trackAnalysisRepository = trackAnalysisRepository;
   }


   public RawAnalysis addRawAnalysis(Long midiFileAnalysisId, ValidatedParseResult parseResult) {
      return rawAnalysisRepository.save(SMFAnalyzer.analyzeRaw(parseResult.smf, midiFileAnalysisId, new RawAnalysis()));
   }
}
