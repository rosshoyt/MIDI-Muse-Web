package com.rosshoyt.analysis.services.raw;

import com.rosshoyt.analysis.model.internal.RawEventCountContainer;
import com.rosshoyt.analysis.model.musical.SustainPedal;
import com.rosshoyt.analysis.model.raw.RawEventCounts;
import com.rosshoyt.analysis.model.raw.meta_events.Tempo;
import com.rosshoyt.analysis.model.raw.meta_events.TimeSignature;
import com.rosshoyt.analysis.model.raw.midi_events._NoteOffEvent;
import com.rosshoyt.analysis.model.raw.midi_events._NoteOnEvent;
import com.rosshoyt.analysis.model.raw.midi_events.controller_events._SustainPedalEvent;
import com.rosshoyt.analysis.tools.midifile.handlers.MetaEventHandler;
import com.rosshoyt.analysis.tools.midifile.handlers.MidiEventHandler;
import com.rosshoyt.analysis.tools.midifile.parsing.kaitai.StandardMidiFile;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.internal.ValidParseResultContainer;
import com.rosshoyt.analysis.model.raw.RawAnalysis;

import com.rosshoyt.analysis.model.raw._Header;
import com.rosshoyt.analysis.model.raw._TrackEvent;
import com.rosshoyt.analysis.repositories.raw.RawAnalysisRepository;
import com.rosshoyt.analysis.repositories.raw.RawTrackEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RawAnalysisService {
   // CRUD Repos
   private final RawAnalysisRepository rawAnalysisRepository;
   private final RawTrackEventRepository rawTrackEventRepository;



   @Autowired
   public RawAnalysisService(RawAnalysisRepository rawAnalysisRepository, RawTrackEventRepository rawTrackEventRepository) {
      this.rawAnalysisRepository = rawAnalysisRepository;
      this.rawTrackEventRepository = rawTrackEventRepository;
   }
   public Optional<RawAnalysis> findRawAnalysisByMFA(MidiFileAnalysis mfa){
      return rawAnalysisRepository.findById(mfa.getId());
   }


   public RawAnalysis addRawAnalysis(MidiFileAnalysis midiFileAnalysis, ValidParseResultContainer parseResult) {
      RawAnalysis rawAnalysis = new RawAnalysis(midiFileAnalysis);
      return rawAnalysisRepository.save(analyzeSMF (parseResult.smf, rawAnalysis));
   }

   /**
    * Takes an empty RawAnalysis and analyzes KaitaiStruct SMF into that container
    * @param smf KaitaiStruct SMF parse result
    * @param raw Persistent entity to hold raw analysis
    * //@param fkMidiFileAnalysisId id field of base database entry, the MidiFileAnalysis
    * @return raw RawAnalysis object containing analysis
    */
   private RawAnalysis analyzeSMF(StandardMidiFile smf, RawAnalysis raw) {
      // TODO type2 parsing
      // First analyze midi file header -
      raw.setHeader(analyzeSMFHeader(smf.hdr(), new _Header(raw)));

      // Create detached container for raw data analysis of midi file -
      RawEventCountContainer rawEventCountContainer = new RawEventCountContainer();


      SortedSet<_NoteOnEvent> noteOnEvents = new TreeSet<>();
      SortedSet<_NoteOffEvent> noteOffEvents = new TreeSet<>();
      SortedSet<_SustainPedalEvent> sustainPedalEvents = new TreeSet<>();
      SortedSet<Tempo> tempoEvents = new TreeSet<>();
      SortedSet<TimeSignature> timeSignatureEvents = new TreeSet<>();



      // Parse Track's events
      int trackNumber = 0;
      for (StandardMidiFile.Track smfTrack : smf.tracks()) {
         System.out.println("...Analyzing midi track #" + trackNumber + "... ");
         rawEventCountContainer.numTotalEvents += smfTrack.events().event().size();

         Long currentTick = 0L;
         for (StandardMidiFile.TrackEvent event : smfTrack.events().event()) {
            // Increment midi tick values;
            Integer vTime = event.vTime().value();
            currentTick += vTime;
            Integer channel = event.channel();

            System.out.print("Track #" + trackNumber + " channel #" + channel + " event @" + currentTick + ": ");

            // Create trackEvent reference and set to null
            _TrackEvent _trackEvent = analyzeSMFTrackEvent(event, rawEventCountContainer);// = new _TrackEvent();

            if(_trackEvent != null) {
               _trackEvent.setVTime(vTime);
               _trackEvent.setTick(currentTick);
               _trackEvent.setChannel(channel);
               _trackEvent.setTrackNumber(trackNumber);
               _trackEvent.setFkMidiFileAnalysisId(raw.getId());
               // Parse results and persist in correct list
               if(_trackEvent instanceof _NoteOnEvent) noteOnEvents.add((_NoteOnEvent)_trackEvent);
               else if(_trackEvent instanceof _NoteOffEvent) noteOffEvents.add((_NoteOffEvent) _trackEvent);
               else if(_trackEvent instanceof _SustainPedalEvent) sustainPedalEvents.add((_SustainPedalEvent)_trackEvent);
               else if(_trackEvent instanceof Tempo) tempoEvents.add((Tempo)_trackEvent);
               else if (_trackEvent instanceof TimeSignature) timeSignatureEvents.add((TimeSignature)_trackEvent);

            } else {
               rawEventCountContainer.numUnsupportedEvents++;
               System.out.println("...Unsupported Event Type");
            }
         }

         trackNumber++;
      }
      // Set raw analysis fields
      raw.setNoteOnEvents(noteOnEvents);
      raw.setNoteOffEvents(noteOffEvents);
      raw.setSustainPedalEventList(sustainPedalEvents);
      raw.setTempoEvents(tempoEvents);
      raw.setTimeSignatureEvents(timeSignatureEvents);

      // Set persistent data tracked fields from simple tracking class
      RawEventCounts rawEventCounts = new RawEventCounts(rawEventCountContainer);
      rawEventCounts.setRawAnalysis(raw);
      raw.setRawEventCounts(rawEventCounts);
      System.out.println("RawAnalysisService Parse data tracking results: " + rawEventCountContainer);
      return raw;
   }

   private static _Header analyzeSMFHeader(StandardMidiFile.Header hdr, _Header _header){
      _header.setFormat(hdr.format());
      _header.setNumTracks(hdr.numTracks());
      _header.setDivision(hdr.division());
      return _header;
   }
   private static _TrackEvent analyzeSMFTrackEvent(StandardMidiFile.TrackEvent smfTrackEvent, RawEventCountContainer rawEventCountContainer){
      if (smfTrackEvent.eventHeader() == 255) { // META
         rawEventCountContainer.numMetaEvents++;
         return MetaEventHandler.handleMetaEvent(smfTrackEvent, rawEventCountContainer);
      } else if (smfTrackEvent.eventHeader() == 240) { // SYSEX
         rawEventCountContainer.numSysexEvents++;
         System.out.println("Sysex Message Event");
      } else { // MIDI
         rawEventCountContainer.numMidiEvents++;
         return MidiEventHandler.handleMidiEvent(smfTrackEvent, rawEventCountContainer);
      }
      return null;
   }

}
