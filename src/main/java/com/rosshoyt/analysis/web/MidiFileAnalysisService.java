//package com.rosshoyt.analysis.web;
//
//import com.rosshoyt.analysis.model.MidiFileAnalysis;
//import com.rosshoyt.analysis.repositories.MidiFileAnalysisRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MidiFileAnalysisService {
//   @Autowired
//   private MidiFileAnalysisRepository midiFileAnalysisRepository;
//
//   private List<MidiFileAnalysis> getAllMidiFileAnalyses(){
//      List<MidiFileAnalysis> analyses = new ArrayList<>();
//      midiFileAnalysisRepository.findAll().forEach(analyses::add);
//      return analyses;
//   }
//
//   private MidiFileAnalysis getMidiFileAnalysis(long id) {
//      return midiFileAnalysisRepository.findById(id).orElseGet(MidiFileAnalysis::new);
//   }
//
//   private void addMidiFile()
//}
