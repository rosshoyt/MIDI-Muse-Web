package com.rosshoyt.analysis.services;

import com.rosshoyt.analysis.midifile.tools.musicanalysis.MusicAnalyzer;
import com.rosshoyt.analysis.midifile.tools.musicanalysis.TrackAnalyzer;
import com.rosshoyt.analysis.model.kaitai.smf.RawAnalysis;

import com.rosshoyt.analysis.model.kaitai.smf._Track;
import com.rosshoyt.analysis.model.musical.MusicalAnalysis;
import com.rosshoyt.analysis.model.musical.Track;
import com.rosshoyt.analysis.repositories.music.MusicalAnalysisRepository;
import com.rosshoyt.analysis.repositories.music.TrackAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicalAnalysisService {
   // CRUD Repos
   private final MusicalAnalysisRepository musicalAnalysisRepository;
   private final TrackAnalysisRepository trackAnalysisRepository;

   // Utilities
   //private static RawSMFAnalyzer rawSMFAnalyzer = new RawSMFAnalyzer();
   @Autowired
   public MusicalAnalysisService(MusicalAnalysisRepository musicalAnalysisRepository, TrackAnalysisRepository trackAnalysisRepository) {
      this.musicalAnalysisRepository = musicalAnalysisRepository;
      this.trackAnalysisRepository = trackAnalysisRepository;
   }


   public MusicalAnalysis addMusicalAnalysis(RawAnalysis rawAnalysis) {
      MusicalAnalysis musicalAnalysis = musicalAnalysisRepository.save(new MusicalAnalysis());
      List<Track> trackList = new ArrayList<>();
      for(_Track rawTrack: rawAnalysis.getTracks()) {
         Track analyzedTrack = trackAnalysisRepository.save(new Track());
         analyzedTrack = TrackAnalyzer.analyzeTrack(rawTrack, analyzedTrack);
         trackList.add(analyzedTrack);
      }
      trackAnalysisRepository.saveAll(trackList);
      musicalAnalysis.setTracks(trackList);
      return musicalAnalysisRepository.save(musicalAnalysis);
   }

}
