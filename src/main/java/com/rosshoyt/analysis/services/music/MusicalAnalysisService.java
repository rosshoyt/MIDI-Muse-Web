package com.rosshoyt.analysis.services.music;

import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.raw.RawAnalysis;


import com.rosshoyt.analysis.model.musical.MusicalAnalysis;
import com.rosshoyt.analysis.model.musical.Note;
import com.rosshoyt.analysis.repositories.music.MusicalAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MusicalAnalysisService {

   private final MusicalAnalysisRepository musicalAnalysisRepository;
   private final NoteService noteService;
   private final MusicalArrangementService musicalArrangementService;
   // Utilities
   //private static RawSMFAnalyzer rawSMFAnalyzer = new RawSMFAnalyzer();
   @Autowired
   public MusicalAnalysisService(MusicalAnalysisRepository musicalAnalysisRepository, NoteService noteService, MusicalArrangementService musicalArrangementService) {
      this.musicalAnalysisRepository = musicalAnalysisRepository;
      this.noteService = noteService;
      this.musicalArrangementService = musicalArrangementService;
   }

   public Optional<MusicalAnalysis> findMusicalAnalysisByMFA(MidiFileAnalysis mfa){
      return musicalAnalysisRepository.findById(mfa.getId());
   }


   public MusicalAnalysis addMusicalAnalysis(MidiFileAnalysis midiFileAnalysis, RawAnalysis rawAnalysis) {
      //MusicalAnalysis musicalAnalysis = musicalAnalysisRepository.save(new MusicalAnalysis());
      MusicalAnalysis musicalAnalysis = new MusicalAnalysis(midiFileAnalysis);

      musicalAnalysis.setNotes(new TreeSet<>(StreamSupport.stream(noteService.addNoteList(rawAnalysis, midiFileAnalysis)
            .spliterator(), false).collect(Collectors.toList())));

      return musicalAnalysisRepository.save(musicalAnalysis);
   }

}

