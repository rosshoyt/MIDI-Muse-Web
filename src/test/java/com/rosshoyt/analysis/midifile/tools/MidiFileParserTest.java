package com.rosshoyt.analysis.midifile.tools;

import com.rosshoyt.analysis.midifile.tools.kaitai.KaitaiSMFParser;
import com.rosshoyt.analysis.midifile.tools.kaitai.StandardMidiFile;
import com.rosshoyt.analysis.utils.FileUtils;
import org.junit.Test;

import java.io.File;

public class MidiFileParserTest {
   private static File smfType1 = new File("preloaded-midi-files/pianocon.mid");
   @Test
   public void test(){
      StandardMidiFileParser parser = new KaitaiSMFParser();

      try {
         ValidatedParseResult parseResult = parser.parse(FileUtils.getByteArray(smfType1));

         StandardMidiFile smf = parseResult.smf;

         int trackCounter = 0;
         for(StandardMidiFile.Track smfTrack : smf.tracks()){
            System.out.println("ANALYZING TRACK #" + trackCounter);//: " + event.eventType());

            //StandardMidiFile.TrackEvents trackEvents = track.events();
            //System.out.println("TrackEvents raw: " + trackEvents.);
            int trackEventCounter = 0;
            for(StandardMidiFile.TrackEvent event : smfTrack.events().event()){
               StandardMidiFile.TrackEvents events = smfTrack.events();

               switch (event.eventType()) {
                  case 224:
                     StandardMidiFile.NoteOnEvent noteOnEvent = new StandardMidiFile.NoteOnEvent(event.eventBody()._io(), event);
                     System.out.println("Trc#" + trackCounter + " @" + event.vTime().value() + " TrcEvent# " + trackEventCounter + " NOTE_ON" + "[note = " + noteOnEvent.note() + ", vel = " + noteOnEvent.velocity() + "]");

                     //StandardMidiFile.NoteOffEvent noteOnEvent = event(StandardMidiFile.NoteOnEvent);
                     //System.out.println("Track #" + trackCounter +" TrackEvent# " + trackEventCounter + " NOTE_ON [note = " + noteOnEvent.note() + ", vel = " + noteOnEvent.velocity() + "]");
                     break;
                  case 128:
                     System.out.println("Trc#" + trackCounter /*+ " @" + event.vTime().value() */+ " TrcEvent# " + trackEventCounter + " NOTE_OFF");//[note = " + noteOnEvent.note() + ", vel = " + noteOnEvent.velocity() + "]");

                     //StandardMidiFile.NoteOffEvent noteOffEvent = new StandardMidiFile.NoteOffEvent(event._io(), event, smf);
                     //System.out.println("Track #" + trackCounter +" TrackEvent# " + trackEventCounter + " NOTE_ON [note = " + noteOffEvent.note() + ", vel = " + noteOffEvent.velocity() + "]");
                     break;
               }

               trackEventCounter++;
            }

            trackCounter++;
         }



      } catch(Exception e) {
         e.printStackTrace();
      }
   }
}
