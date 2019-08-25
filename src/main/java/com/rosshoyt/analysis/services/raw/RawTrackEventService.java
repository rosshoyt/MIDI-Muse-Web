package com.rosshoyt.analysis.services.raw;

import com.google.common.collect.Lists;
import com.rosshoyt.analysis.model.raw.meta_events.RawTempoEvent;
import com.rosshoyt.analysis.model.raw.meta_events.RawTimeSignatureEvent;
import com.rosshoyt.analysis.model.raw.midi_events.abstractions.RawNoteEvent;
import com.rosshoyt.analysis.model.raw.midi_events.controller_events.RawSustainPedalEvent;
import com.rosshoyt.analysis.repositories.raw.trackevents.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RawTrackEventService {


   public List<RawNoteEvent> addRawNoteEvents(Iterable<RawNoteEvent> rawNoteEvents){
      return Lists.newArrayList(rawNoteEventRepository.saveAll(rawNoteEvents));
   }

   public List<RawSustainPedalEvent> addRawSustainPedalEvents(Iterable<RawSustainPedalEvent> raw){
      return Lists.newArrayList(rawSustainPedalEventRepository.saveAll(raw));
   }
   public List<RawTempoEvent> addRawTempoEvents(Iterable<RawTempoEvent> rawTempoEvents){
      return Lists.newArrayList(rawTempoEventRepository.saveAll(rawTempoEvents));
   }
   public List<RawTimeSignatureEvent> addRawTimeSignatureEvents(Iterable<RawTimeSignatureEvent> rawTimeSignatureEvents) {
      return Lists.newArrayList(rawTimeSignatureEventRepository.saveAll(rawTimeSignatureEvents));
   }
   //   public Iterable<> addRaw(Iterable<> raw){
//      return raw.saveAll();
//   }
   public List<RawNoteEvent> getRawNoteEvents(Long fkMidiFileAnalysisId){
      //Iterable<RawNoteEvent> noteEvents = rawNoteEventRepository.findByFkMidiFileAnalysisId(fkMidiFileAnalysisId);
      System.out.println("In RawTrackEventService.getRawNoteEvents()");
      List<RawNoteEvent> rawNoteEvents = Lists.newArrayList(rawNoteEventRepository.findByFkMidiFileAnalysisId(fkMidiFileAnalysisId));
      System.out.println("Returning list: " + rawNoteEvents);
      return rawNoteEvents;
   }
   public List<RawSustainPedalEvent> getRawSustainPedalEvents(Long fkMidiFileAnalysisId){
      return Lists.newArrayList(rawSustainPedalEventRepository.findByFkMidiFileAnalysisId(fkMidiFileAnalysisId));
   }

   public List<RawTempoEvent> getRawTempoEvents(Long fkMidiFileAnalysisId){
      return Lists.newArrayList(rawTempoEventRepository.findByFkMidiFileAnalysisId(fkMidiFileAnalysisId));
   }
   public List<RawTimeSignatureEvent> getRawTimeSignatureEvents(Long fkMidiFileAnalysisId){
      return Lists.newArrayList(rawTimeSignatureEventRepository.findByFkMidiFileAnalysisId(fkMidiFileAnalysisId));
   }
//   public List<> getRawEvents(Long fkMidiFileAnalysisId){
//      return Lists.newArrayList(.findAllByFkMidiFileAnalysisId(fkMidiFileAnalysisId));
//   }




   public RawTrackEventService(RawCopyrightEventRepository rawCopyrightEventRepository,
                               RawCuePointEventRepository rawCuePointEventRepository,
                               RawInstrumentNameEventRepository rawInstrumentNameEventRepository,
                               RawKeySignatureEventRepository rawKeySignatureEventRepository,
                               RawLyricTextEventRepository rawLyricTextEventRepository,
                               RawMarkerTextEventRepository rawMarkerTextEventRepository,
                               RawMidiChannelPrefixAssignmentEventRepository rawMidiChannelPrefixAssignmentEventRepository,
                               RawSequenceTrackNameEventRepository rawSequenceTrackNameEventRepository,
                               RawSMPTEOffsetEventRepository rawSMPTEOffsetEventRepository,
                               RawTempoEventRepository rawTempoEventRepository,
                               RawTextEventRepository rawTextEventRepository,
                               RawTimeSignatureEventRepository rawTimeSignatureEventRepository,
                               RawNoteEventRepository rawNoteEventRepository,
                               RawSustainPedalEventRepository rawSustainPedalEventRepository) {
      this.rawCopyrightEventRepository = rawCopyrightEventRepository;
      this.rawCuePointEventRepository = rawCuePointEventRepository;
      this.rawInstrumentNameEventRepository = rawInstrumentNameEventRepository;
      this.rawKeySignatureEventRepository = rawKeySignatureEventRepository;
      this.rawLyricTextEventRepository = rawLyricTextEventRepository;
      this.rawMarkerTextEventRepository = rawMarkerTextEventRepository;
      this.rawMidiChannelPrefixAssignmentEventRepository = rawMidiChannelPrefixAssignmentEventRepository;
      this.rawSequenceTrackNameEventRepository = rawSequenceTrackNameEventRepository;
      this.rawSMPTEOffsetEventRepository = rawSMPTEOffsetEventRepository;
      this.rawTempoEventRepository = rawTempoEventRepository;
      this.rawTextEventRepository = rawTextEventRepository;
      this.rawTimeSignatureEventRepository = rawTimeSignatureEventRepository;
      this.rawNoteEventRepository = rawNoteEventRepository;
      this.rawSustainPedalEventRepository = rawSustainPedalEventRepository;
   }
   // Meta Event Repositories
   private final RawCopyrightEventRepository rawCopyrightEventRepository;
   private final RawCuePointEventRepository rawCuePointEventRepository;
   private final RawInstrumentNameEventRepository rawInstrumentNameEventRepository ;
   private final RawKeySignatureEventRepository rawKeySignatureEventRepository;
   private final RawLyricTextEventRepository rawLyricTextEventRepository;
   private final RawMarkerTextEventRepository rawMarkerTextEventRepository;
   private final RawMidiChannelPrefixAssignmentEventRepository rawMidiChannelPrefixAssignmentEventRepository;
   private final RawSequenceTrackNameEventRepository rawSequenceTrackNameEventRepository;
   private final RawSMPTEOffsetEventRepository rawSMPTEOffsetEventRepository;
   private final RawTempoEventRepository rawTempoEventRepository;
   private final RawTextEventRepository rawTextEventRepository;
   private final RawTimeSignatureEventRepository rawTimeSignatureEventRepository;


   // Midi Event Repositories
   private final RawNoteEventRepository rawNoteEventRepository;
   private final RawSustainPedalEventRepository rawSustainPedalEventRepository;
}

