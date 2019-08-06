package com.rosshoyt.analysis.midifile.tools.musicanalysis;

import com.rosshoyt.analysis.model.kaitai.smf._Track;
import com.rosshoyt.analysis.model.kaitai.smf._TrackEvent;

import com.rosshoyt.analysis.model.kaitai.smf.midi_events._NoteOnEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events.controller_events._SustainPedalEvent;
import com.rosshoyt.analysis.model.musical.Track;

public class TrackAnalyzer {
   public static Track analyzeTrack(_Track _track, Track analyzedTrack){
//      for(_TrackEventContainer eventContainer: _track.getTrackEventContainerList()){
////         _TrackEvent trackEvent = eventContainer.getTrackEvent();
////         if(trackEvent instanceof _SustainPedalEvent){
////            _SustainPedalEvent sustainPedalEvent = (_SustainPedalEvent)trackEvent;
////            System.out.println("Analyzing Sustain Pedal event from raw track, value = " + sustainPedalEvent.getValue());
////         }
////      }
      return analyzedTrack;
   }
}
class _TrackEventAnalyzer {

}

