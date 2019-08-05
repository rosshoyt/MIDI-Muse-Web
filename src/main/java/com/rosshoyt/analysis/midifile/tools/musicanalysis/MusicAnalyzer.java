package com.rosshoyt.analysis.midifile.tools.musicanalysis;

import com.rosshoyt.analysis.model.kaitai.smf.RawAnalysis;
import com.rosshoyt.analysis.model.kaitai.smf._Track;
import com.rosshoyt.analysis.model.kaitai.smf._TrackEvent;
import com.rosshoyt.analysis.model.kaitai.smf._TrackEventContainer;
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

   public static MusicalAnalysis analyzeMusic(RawAnalysis rawAnalysis) {
      MusicalAnalysis mus = new MusicalAnalysis();
      System.out.println("Starting MUSICAL ANALYSIS...");
      mus.setId(rawAnalysis.getId());
      mus.setMusicalArrangement(getArrangement(rawAnalysis));
      mus.setTracks(getTracks(rawAnalysis));



      //mus.setTotalNotes(totalNoteCount);

      return mus;
   }

   public static List<Track> getTracks(RawAnalysis rawAnalysis) {
      List<Track> tracks = new ArrayList<>();
      System.out.println("Analyzing RawTracks -> Tracks");
      for(_Track rawTrack: rawAnalysis.getTracks()){
         Track trackMusAnalysis = new Track();

         tracks.add(trackMusAnalysis);
      }
      return tracks;
   }

   public static MusicalArrangement getArrangement (RawAnalysis rawAnalysis){
      MusicalArrangement musicalArrangement = new MusicalArrangement();
      return musicalArrangement;
   }
}
