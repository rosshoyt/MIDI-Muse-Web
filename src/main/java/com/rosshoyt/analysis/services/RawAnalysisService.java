package com.rosshoyt.analysis.services;

import com.rosshoyt.analysis.midifile.tools.kaitai.StandardMidiFile;
import com.rosshoyt.analysis.midifile.tools.rawanalysis.SMFAnalyzer;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.internal.ValidatedParseResult;
import com.rosshoyt.analysis.model.kaitai.smf.RawAnalysis;

import com.rosshoyt.analysis.model.kaitai.smf._Header;
import com.rosshoyt.analysis.model.kaitai.smf._TrackEvent;
import com.rosshoyt.analysis.repositories.raw.RawAnalysisRepository;
import com.rosshoyt.analysis.repositories.raw.RawTrackEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class  RawAnalysisService {
   // CRUD Repos
   private final RawAnalysisRepository rawAnalysisRepository;
   private final RawTrackEventRepository rawTrackEventRepository;

   // Utilities
   //private static RawSMFAnalyzer rawSMFAnalyzer = new RawSMFAnalyzer();
   @Autowired
   public RawAnalysisService(RawAnalysisRepository rawAnalysisRepository, RawTrackEventRepository rawTrackEventRepository) {
      this.rawAnalysisRepository = rawAnalysisRepository;
      this.rawTrackEventRepository = rawTrackEventRepository;
   }
   public Optional<RawAnalysis> findRawAnalysisByMFA(MidiFileAnalysis mfa){
      return rawAnalysisRepository.findById(mfa.getId());
   }


   public RawAnalysis addRawAnalysis(MidiFileAnalysis midiFileAnalysis, ValidatedParseResult parseResult) {
      RawAnalysis rawAnalysis = new RawAnalysis(midiFileAnalysis);
      return rawAnalysisRepository.save(analyzeSMF (parseResult.smf, rawAnalysis));
   }
   /**
    * Takes an empty RawAnalysis and analyzes KaitaiStrcut SMF
    * @param smf KaitaiStruct SMF parse result
    * @param raw Persistent entity to hold raw analysis
    * //@param fkMidiFileAnalysisId id field of base database entry, the MidiFileAnalysis
    * @return raw RawAnalysis object containing analysis
    */
   private RawAnalysis analyzeSMF(StandardMidiFile smf, RawAnalysis raw) {
      //if (raw.getHeader().format() != 2) { // TODO type2 parsing
      raw.setHeader(SMFAnalyzer.analyzeSMFHeader(smf.hdr(),new _Header(raw)));

      List<_TrackEvent> _eventList = new ArrayList<>();

      // Parse Track's events
      int trackNumber = 0;
      for (StandardMidiFile.Track smfTrack : smf.tracks()) {
         System.out.println("...Analyzing midi track #" + trackNumber + "... ");
//         _Track _track = new _Track();
//         _track.setTrackNumber(trackNumber);
//         _track.setFkMidiFileAnalysisId(raw.getId());
//         _track.setNumTrackEvents(smfTrack.events().event().size());

         //SMFAnalyzer.analyzeSMFTrack(smfTrack, _track);

         Long currentTick = 0L;
         for (StandardMidiFile.TrackEvent event : smfTrack.events().event()) {
            // Increment midi tick values;
            Integer vTime = event.vTime().value();
            currentTick += vTime;

            System.out.print("Track event @" + currentTick + ": ");

            // Create trackEvent reference and set to null
            _TrackEvent trackEvent = SMFAnalyzer.analyzeSMFTrackEvent(event);// = new _TrackEvent();

            if(trackEvent != null) {
               trackEvent.setVTime(vTime);
               trackEvent.setTick(currentTick);
               trackEvent.setChannel(event.channel());
               trackEvent.setTrackNumber(trackNumber);
               trackEvent.setFkMidiFileAnalysisId(raw.getId());
               _eventList.add(rawTrackEventRepository.save(trackEvent));
            } else {
               System.out.println("...Unsupported Event Type");
            }
         }
//         _track.setTrackEventList(_trackEventList);
//         _tracks.add(_track);
         trackNumber++;
      }
      raw.setTrackEvents(_eventList);
      return raw;
   }
}
