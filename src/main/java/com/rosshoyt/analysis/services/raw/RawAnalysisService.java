package com.rosshoyt.analysis.services.raw;

import com.rosshoyt.analysis.model.internal.RawAnalysisStatisticsContainer;
import com.rosshoyt.analysis.model.raw.RawAnalysisStatistics;
import com.rosshoyt.analysis.model.raw.meta_events.RawTempoEvent;
import com.rosshoyt.analysis.model.raw.meta_events.RawTimeSignatureEvent;
import com.rosshoyt.analysis.model.raw.midi_events.RawNoteOffEvent;
import com.rosshoyt.analysis.model.raw.midi_events.RawNoteOnEvent;
import com.rosshoyt.analysis.model.raw.midi_events.abstractions.RawNoteEvent;
import com.rosshoyt.analysis.model.raw.midi_events.controller_events.RawSustainPedalEvent;
import com.rosshoyt.analysis.tools.midifile.handlers.MetaEventHandler;
import com.rosshoyt.analysis.tools.midifile.handlers.MidiEventHandler;
import com.rosshoyt.analysis.tools.midifile.parsing.kaitai.StandardMidiFile;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.internal.ValidParseResultContainer;
import com.rosshoyt.analysis.model.raw.RawAnalysis;

import com.rosshoyt.analysis.model.raw.RawHeader;
import com.rosshoyt.analysis.model.raw.abstractions.RawTrackEvent;
import com.rosshoyt.analysis.repositories.raw.RawAnalysisRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class RawAnalysisService {
   // CRUD Repositories
   private final RawAnalysisRepository rawAnalysisRepository;
   // Helper services
   private final RawTrackEventService rawTrackEventService;

   @Autowired
   public RawAnalysisService(RawAnalysisRepository rawAnalysisRepository, RawTrackEventService rawTrackEventService) {
      this.rawAnalysisRepository = rawAnalysisRepository;
      this.rawTrackEventService = rawTrackEventService;
   }

   public Optional<RawAnalysis> getRawAnalysisByFkMidiFileAnalysisId(Long fkMidiFileAnalysisId){
      System.out.println("In RawAnalysisService.getRawAnalysisByFkMidiFileAnalysisId()");
      Optional<RawAnalysis> optionalRawAnalysis = rawAnalysisRepository.findByFkMidiFileAnalysisId(fkMidiFileAnalysisId);
      if(optionalRawAnalysis.isPresent()){
         System.out.println("RawAnalysis was present, getting raw track events from RawTrackEventService");
         RawAnalysis rawAnalysis = optionalRawAnalysis.get();
         rawAnalysis.setRawNoteEvents(rawTrackEventService.getRawNoteEvents(fkMidiFileAnalysisId));
         rawAnalysis.setRawSustainPedalEvents(rawTrackEventService.getRawSustainPedalEvents(fkMidiFileAnalysisId));
         rawAnalysis.setRawTempoEvents(rawTrackEventService.getRawTempoEvents(fkMidiFileAnalysisId));
         rawAnalysis.setRawTimeSignatureEvents(rawTrackEventService.getRawTimeSignatureEvents(fkMidiFileAnalysisId));
         System.out.println("RawAnalysis after fetching the rawTrackEvents lists" + rawAnalysis);
         return Optional.of(rawAnalysis);
      }
      System.out.println("RawAnalysis was null");
      return optionalRawAnalysis;
   }


   public RawAnalysis addRawAnalysis(MidiFileAnalysis midiFileAnalysis, ValidParseResultContainer parseResult) {
      return rawAnalysisRepository.save(analyzeSMF(parseResult.smf, midiFileAnalysis.getId()));
   }

   /**
    * Method that creates a persistent RawAnalysis from a KaitaiStruct SMF parse result
    * TODO Support Type2 SMF parsing
    * @param smf KaitaiStruct SMF parse result to be analyzed
    * @param fkMidiFileAnalysisId foreign key to base DB entry
    * @return raw RawAnalysis object containing extracted analysis
    */
   private RawAnalysis analyzeSMF(StandardMidiFile smf, Long fkMidiFileAnalysisId) {
      RawAnalysis raw = new RawAnalysis();
      raw.setFkMidiFileAnalysisId(fkMidiFileAnalysisId);

      // Container object that tracks data/stats of midi file with easily incremented fields - TODO create better solution
      RawAnalysisStatisticsContainer rawAnalysisStatisticsContainer = new RawAnalysisStatisticsContainer();

      // Analyze midi file header -
      raw.setRawHeader(analyzeSMFHeader(smf.hdr(), raw.getFkMidiFileAnalysisId()));

      // Containers for RawTrackEvent types to be persisted with RawAnalysis
      List<RawNoteEvent> rawNoteEvents = new ArrayList<>();
      List<RawSustainPedalEvent> rawSustainPedalEvents = new ArrayList<>();
      List<RawTempoEvent> rawTempoEvents = new ArrayList<>();
      List<RawTimeSignatureEvent> rawTimeSignatureEvents = new ArrayList<>();

      // Parse each track's events in order and handle based on type
      int trackNumber = 0;
      for (StandardMidiFile.Track smfTrack : smf.tracks()) {
         System.out.println("...Analyzing midi track #" + trackNumber + "... ");

         // Statistic tracking
         rawAnalysisStatisticsContainer.numTotalEvents += smfTrack.events().event().size();

         // Loop through the track's TrackEvents and handle each
         Long currentTick = 0L;
         for (StandardMidiFile.TrackEvent event : smfTrack.events().event()) {

            // Increment midi tick values;
            Integer vTime = event.vTime().value();
            currentTick += vTime;

            Integer channel = event.channel(); // TODO move this line, not all TrackEvents have a channel

            System.out.print("Track #" + trackNumber + " channel #" + channel + " event @" + currentTick + ": ");

            // Create trackEvent reference and set to null
            RawTrackEvent rawTrackEvent = analyzeSMFTrackEvent(event, rawAnalysisStatisticsContainer);// = new _TrackEvent();

            if(rawTrackEvent != null) {
               rawTrackEvent.setFkMidiFileAnalysisId(fkMidiFileAnalysisId);
               rawTrackEvent.setVTime(vTime);
               rawTrackEvent.setTick(currentTick);
               rawTrackEvent.setChannel(channel);
               rawTrackEvent.setTrackNumber(trackNumber);
               rawTrackEvent.setFkMidiFileAnalysisId(raw.getId());
               // Persist event in corresponding collection
               if     (rawTrackEvent instanceof RawNoteOnEvent)         rawNoteEvents.add((RawNoteOnEvent)rawTrackEvent);
               else if(rawTrackEvent instanceof RawNoteOffEvent)         rawNoteEvents.add((RawNoteOffEvent)rawTrackEvent);
               else if(rawTrackEvent instanceof RawSustainPedalEvent)   rawSustainPedalEvents.add((RawSustainPedalEvent)rawTrackEvent);
               else if(rawTrackEvent instanceof RawTempoEvent)          rawTempoEvents.add((RawTempoEvent)rawTrackEvent);
               else if(rawTrackEvent instanceof RawTimeSignatureEvent)  rawTimeSignatureEvents.add((RawTimeSignatureEvent)rawTrackEvent);

            } else {
               rawAnalysisStatisticsContainer.numUnsupportedEvents++;
               System.out.println("...Unsupported Event Type");
            }
         }

         trackNumber++;
      }
      // Persist track events via RawTrackEventService layer
      rawNoteEvents = rawTrackEventService.addRawNoteEvents(rawNoteEvents);
      rawSustainPedalEvents = rawTrackEventService.addRawSustainPedalEvents(rawSustainPedalEvents);
      rawTempoEvents = rawTrackEventService.addRawTempoEvents(rawTempoEvents);
      rawTimeSignatureEvents = rawTrackEventService.addRawTimeSignatureEvents(rawTimeSignatureEvents);

      // Set raw analysis' fields
      raw.setRawNoteEvents(rawNoteEvents);
      raw.setRawSustainPedalEvents(rawSustainPedalEvents);
      raw.setRawTempoEvents(rawTempoEvents);
      raw.setRawTimeSignatureEvents(rawTimeSignatureEvents);

      // Create persistent statistics from the statistics container class
      RawAnalysisStatistics rawAnalysisStatistics = new RawAnalysisStatistics(rawAnalysisStatisticsContainer);
      raw.setRawAnalysisStatistics(rawAnalysisStatistics);
      System.out.println("RawAnalysisService Parse data tracking results: " + rawAnalysisStatisticsContainer);
      return raw;
   }

   private static RawHeader analyzeSMFHeader(StandardMidiFile.Header hdr, Long fkMidiFileAnalysisId){
      RawHeader _header = new RawHeader();
      _header.setFkMidiFileAnalysisId(fkMidiFileAnalysisId);
      _header.setFormat(hdr.format());
      _header.setNumTracks(hdr.numTracks());
      _header.setDivision(hdr.division());
      return _header;
   }
   private static RawTrackEvent analyzeSMFTrackEvent(StandardMidiFile.TrackEvent smfTrackEvent, RawAnalysisStatisticsContainer rawAnalysisStatisticsContainer){
      if (smfTrackEvent.eventHeader() == 255) { // META
         rawAnalysisStatisticsContainer.numMetaEvents++;
         return MetaEventHandler.handleMetaEvent(smfTrackEvent, rawAnalysisStatisticsContainer);
      } else if (smfTrackEvent.eventHeader() == 240) { // SYSEX
         rawAnalysisStatisticsContainer.numSysexEvents++;
         System.out.println("Sysex Message Event");
      } else { // MIDI
         rawAnalysisStatisticsContainer.numMidiEvents++;
         return MidiEventHandler.handleMidiEvent(smfTrackEvent, rawAnalysisStatisticsContainer);
      }
      return null;
   }

}
