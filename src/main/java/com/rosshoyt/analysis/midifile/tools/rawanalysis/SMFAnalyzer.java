package com.rosshoyt.analysis.midifile.tools.rawanalysis;




import com.rosshoyt.analysis.midifile.tools.handlers.MetaEventHandler;
import com.rosshoyt.analysis.midifile.tools.handlers.MidiEventHandler;
import com.rosshoyt.analysis.midifile.tools.kaitai.StandardMidiFile;

import com.rosshoyt.analysis.model.file.FileByteData;
import com.rosshoyt.analysis.model.file.MidiFileDetail;
import com.rosshoyt.analysis.model.kaitai.smf.*;


import java.util.ArrayList;
import java.util.List;


/**
 * Middle level file analyzer.
 * Works with one uploaded .mid/.midi file to extract useful information and return
 * SQL database-persistable analysis data (RawAnalysis)
 */

public class SMFAnalyzer {


   public static _Header analyzeSMFHeader(StandardMidiFile.Header hdr, _Header _header){
      _header.setFormat(hdr.format());
      _header.setNumTracks(hdr.numTracks());
      _header.setDivision(hdr.division());
      return _header;
   }

   public static MidiFileDetail getMidiFileDetail(String fileName, String extension, RawAnalysis rawAnalysis, byte[] fileData) {
      MidiFileDetail mfd = new MidiFileDetail();
      mfd.setId(rawAnalysis.getId());
      mfd.setFileName(fileName);
      mfd.setFileExtension(extension);
      mfd.setFullFileName(fileName + "." + extension);
      mfd.setFileByteData(new FileByteData(rawAnalysis.getId(), fileData));
      return mfd;
   }
//   public static _Track analyzeSMFTrack(StandardMidiFile.Track smfTrack, _Track track){
//
//      return track;
//   }
   public static _TrackEvent analyzeSMFTrackEvent(StandardMidiFile.TrackEvent smfTrackEvent){
      if (smfTrackEvent.eventHeader() == 255) {
         return MetaEventHandler.handleMetaEvent(smfTrackEvent);
      } else if (smfTrackEvent.eventHeader() == 240) {
         System.out.println("Sysex Message Event");
         //eventIsSupported = false;
         // Sysex message
      } else {
         return MidiEventHandler.handleMidiEvent(smfTrackEvent);
      }

      return null;
   }









}
