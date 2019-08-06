package com.rosshoyt.analysis.midifile.tools;




import com.rosshoyt.analysis.midifile.tools.handlers.MetaEventHandler;
import com.rosshoyt.analysis.midifile.tools.handlers.MidiEventHandler;
import com.rosshoyt.analysis.midifile.tools.kaitai.StandardMidiFile;

import com.rosshoyt.analysis.model.file.FileByteData;
import com.rosshoyt.analysis.model.file.MidiFileDetail;
import com.rosshoyt.analysis.model.kaitai.smf.*;
import com.rosshoyt.analysis.model.kaitai.smf.meta_events.Tempo;
import com.rosshoyt.analysis.model.kaitai.smf.meta_events.TimeSignature;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOffEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOnEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events.controller_events._SustainPedalEvent;


import java.util.ArrayList;
import java.util.List;


/**
 * Middle level file analyzer.
 * Works with one uploaded .mid/.midi file to extract useful information and return
 * SQL database-persistable analysis data (RawAnalysis)
 */

public class SMFAnalyzer {


   public static MidiFileDetail getMidiFileDetail(String fileName, String extension, RawAnalysis rawAnalysis, byte[] fileData) {
      MidiFileDetail mfd = new MidiFileDetail();
      mfd.setId(rawAnalysis.getId());
      mfd.setFileName(fileName);
      mfd.setFileExtension(extension);
      mfd.setFullFileName(fileName + "." + extension);
      mfd.setFileByteData(new FileByteData(rawAnalysis.getId(), fileData));
      return mfd;
   }

   /**
    * Takes an empty RawAnalysis and analyzes KaitaiStrcut SMF
    * @param smf KaitaiStruct SMF parse result
    * @param raw Persistent entity to hold raw analysis
    * @param fkMidiFileAnalysisId id field of base database entry, the MidiFileAnalysis
    * @return raw RawAnalysis object containing analysis
    */
   public static RawAnalysis analyzeRaw(StandardMidiFile smf, Long fkMidiFileAnalysisId, RawAnalysis raw) {
      // Parse SMF Header
      raw.setId(fkMidiFileAnalysisId);
      raw.setHeader(analyzeHeader(smf));

      //raw.setNumMidiMessages(countNumMidiEvents(smf.tracks()));


      // TODO remove the original parse loop from analyzeMusic()
      //if (smf.hdr().format() != 2) {

      List<_Track> _tracks = new ArrayList<>();
      int trackNumber = 0;
      // Parse Track's events
      for (StandardMidiFile.Track smfTrack : smf.tracks()) {
         System.out.println("...Analyzing midi track #" + trackNumber + "... ");
         _Track _track = new _Track();
         _track.setTrackNumber(trackNumber);
         _track.setFkMidiFileAnalysisId(fkMidiFileAnalysisId);
         _track.setNumTrackEvents(smfTrack.events().event().size());

         Long currentTick = 0L;
         List<_TrackEvent> _trackEventContainerList = new ArrayList<>();
         for (StandardMidiFile.TrackEvent event : smfTrack.events().event()) {
            // Increment midi tick values;
            Integer vTime = event.vTime().value();
            currentTick += vTime;
            System.out.print("Track event @" + currentTick + ": ");


            // Set trackEvent to null
            _TrackEvent container = null;// = new _TrackEvent();


//            boolean eventIsSupported = true;
            if (event.eventHeader() == 255) {
               container = MetaEventHandler.handleMetaEvent(event);
            } else if (event.eventHeader() == 240) {
               System.out.println("Sysex Message Event");
               //eventIsSupported = false;
               // Sysex message
            } else {
               container = MidiEventHandler.handleMidiEvent(event);
            }
            //
            if(container != null) {

               container.setVTime(vTime);
               container.setTick(currentTick);
               container.setChannel(event.channel());
               container.setTrackNumber(trackNumber);
               container.setFkMidiFileAnalysisId(fkMidiFileAnalysisId);
               _trackEventContainerList.add(container);
            } else {
               System.out.print("...Unsupported Track event type.\n");
            }
         }
         _track.setTrackEventContainerList(_trackEventContainerList);
         _tracks.add(_track);
         trackNumber++;
      }
      raw.setTracks(_tracks);
      return raw;
   }







   private static _Header analyzeHeader(StandardMidiFile smf){
      _Header _Header = new _Header();
      _Header.setFormat(smf.hdr().format());
      _Header.setNumTracks(smf.hdr().numTracks());
      _Header.setDivision(smf.hdr().division());
      return _Header;
   }

}
