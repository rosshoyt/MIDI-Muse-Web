package com.rosshoyt.analysis.midifile.tools.handlers;

import com.rosshoyt.analysis.midifile.tools.kaitai.StandardMidiFile;
import com.rosshoyt.analysis.model.kaitai.smf._TrackEvent;
import com.rosshoyt.analysis.model.kaitai.smf.meta_events.Tempo;
import com.rosshoyt.analysis.model.kaitai.smf.meta_events.TimeSignature;

public class MetaEventHandler {
   public static _TrackEvent handleMetaEvent(StandardMidiFile.TrackEvent event) {
      //System.out.println(" Meta Message Encountered"); TODO REFACTOR ENUM TO PRINT TYPE HERE (THIS LINE)
      if(event.metaEventBody() != null && event.metaEventBody().metaType() != null) {
         switch ((int)event.metaEventBody().metaType().id()) { // TODO test this line
            case 81: {
               System.out.print("Meta Message Tempo Event\n");
               return parseTempoEvent(event.metaEventBody().body());
            }
            case 88: {
               System.out.print("Meta Message Time Signature Event\n");
               return parseTimeSignatureEvent(event.metaEventBody().body());
            }
            default: {
               System.out.print("Other Meta Message Encountered");
               return null;
            }
         }
      }
      System.out.println("MetaEventBody or MetaType was null");
      return null;
   }
   private static Tempo parseTempoEvent(byte[] body) {
      // body.length (will?) be 3
      Tempo tempo = new Tempo();
      System.out.println("Parsing tempo from byte[]: " + body);
      // TODO test tempo extraction from byte[]
      int x = (body[0] & 0xff) << 16 | (body[1] & 0xff) << 8 | (body[2] & 0xff);
      System.out.println("Extracted microseconds/quarternote = " + x);
      tempo.setNumMicrosecondsPerQuarterNote(x);
      return tempo;
   }

   private static TimeSignature parseTimeSignatureEvent(byte[] body) {
      // body.length() (will?) be 4
      // TODO check for correct value, throw exception
      TimeSignature timeSignature = new TimeSignature();
      timeSignature.setNumerator(body[0]);
      timeSignature.setDenominator((int)Math.pow((double)2, body[1])); // raise 2 to the value of body[1]
      timeSignature.setMidiClocksPerMetronomeClick(body[2]);
      timeSignature.setNum32ndNotesPerBeat(body[3]);
      System.out.println("Extracted Time Signature: " + timeSignature.getNumerator() + "/" + timeSignature.getDenominator());
      System.out.println("32nd notes/beat: " + timeSignature.getNum32ndNotesPerBeat() + ", Midi Clocks per Metronome beat: "
            + timeSignature.getMidiClocksPerMetronomeClick());
      return timeSignature;
   }
}
