package com.rosshoyt.analysis.midifile.tools.musicanalysis;

import com.rosshoyt.analysis.model.kaitai.smf.RawAnalysis;

import com.rosshoyt.analysis.model.kaitai.smf._TrackEvent;

import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOffEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOnEvent;
import com.rosshoyt.analysis.model.musical.MusicalAnalysis;
import com.rosshoyt.analysis.model.musical.MusicalArrangement;
import com.rosshoyt.analysis.model.musical.Track;

import java.util.ArrayList;
import java.util.List;

/**
* High Level music analyzer which
*
 */
public class MusicAnalyzer {

   public static MusicalAnalysis analyzeRaw(RawAnalysis rawAnalysis, MusicalAnalysis musicalAnalysis) {

//      System.out.println("Starting MUSICAL ANALYSIS...");
//      musicalAnalysis.setId(rawAnalysis.getId());
//      musicalAnalysis.setMusicalArrangement(getArrangement(rawAnalysis));
//      musicalAnalysis.setTracks(getTracks(rawAnalysis));
//


      //mus.setTotalNotes(totalNoteCount);

      return musicalAnalysis;
   }

   public static List<Track> getTracks(RawAnalysis rawAnalysis) {
      List<Track> tracks = new ArrayList<>();
//      System.out.println("Analyzing RawTracks -> Tracks");
//      for(_Track rawTrack: rawAnalysis.getTracks()){
//         Track trackMusAnalysis = new Track();
//
//         tracks.add(trackMusAnalysis);
//      }
      return tracks;
   }

   public static MusicalArrangement getArrangement (RawAnalysis rawAnalysis){
      MusicalArrangement musicalArrangement = new MusicalArrangement();
      return musicalArrangement;
   }
}
