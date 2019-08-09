package com.rosshoyt.analysis.services;

import com.rosshoyt.analysis.midifile.tools.musicanalysis.MusicAnalyzer;
import com.rosshoyt.analysis.midifile.tools.musicanalysis.NoteOnWaitingList;
import com.rosshoyt.analysis.midifile.tools.musicanalysis.TrackAnalyzer;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.kaitai.smf.RawAnalysis;


import com.rosshoyt.analysis.model.kaitai.smf._TrackEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOffEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOnEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events.controller_events._SustainPedalEvent;
import com.rosshoyt.analysis.model.musical.MusicalAnalysis;
import com.rosshoyt.analysis.model.musical.Note;
import com.rosshoyt.analysis.model.musical.Track;
import com.rosshoyt.analysis.repositories.music.MusicalAnalysisRepository;
import com.rosshoyt.analysis.repositories.music.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MusicalAnalysisService {
   // CRUD Repos
   private final MusicalAnalysisRepository musicalAnalysisRepository;
   private final NoteRepository noteRepository;

   // Utilities
   //private static RawSMFAnalyzer rawSMFAnalyzer = new RawSMFAnalyzer();
   @Autowired
   public MusicalAnalysisService(MusicalAnalysisRepository musicalAnalysisRepository, NoteRepository noteRepository) {
      this.musicalAnalysisRepository = musicalAnalysisRepository;
      this.noteRepository = noteRepository;
   }

   public Optional<MusicalAnalysis> findMusicalAnalysisByMFA(MidiFileAnalysis mfa){
      return musicalAnalysisRepository.findById(mfa.getId());
   }


   public MusicalAnalysis addMusicalAnalysis(MidiFileAnalysis midiFileAnalysis, RawAnalysis rawAnalysis) {
      //MusicalAnalysis musicalAnalysis = musicalAnalysisRepository.save(new MusicalAnalysis());
      MusicalAnalysis musicalAnalysis = new MusicalAnalysis(midiFileAnalysis);
      List<Note> noteList = new ArrayList<>();
      NoteOnWaitingList noteOnWaitingList = new NoteOnWaitingList();
      for(_TrackEvent _trackEvent : rawAnalysis.getTrackEvents()){
         if(_trackEvent instanceof _NoteOnEvent){
            System.out.println("Adding noteOnEvent to waiting list");
            noteOnWaitingList.addNoteOn((_NoteOnEvent)_trackEvent);
         } else if(_trackEvent instanceof _NoteOffEvent){
            System.out.println("Getting Note from Noteoff");
            Note note = noteOnWaitingList.getNoteFromNoteOffEvent((_NoteOffEvent)_trackEvent, new Note());
            if(note != null){
               System.out.println("Found NoteOn for Note off, saving Note to DB");
               note = noteRepository.save(note);
               noteList.add(note);
            }
         } else if(_trackEvent instanceof _SustainPedalEvent) {
            System.out.println("SustainPedalEvent.. TODO implement");
         }
      }
//      for(_Track rawTrack: rawAnalysis.getTracks()) {
//         Track analyzedTrack = noteRepository.save(new Track());
//         analyzedTrack = TrackAnalyzer.analyzeTrack(rawTrack, analyzedTrack);
//         noteList.add(analyzedTrack);
//      }
      noteRepository.saveAll(noteList);
      musicalAnalysis.setNoteList(noteList);
      return musicalAnalysisRepository.save(musicalAnalysis);
   }

}
