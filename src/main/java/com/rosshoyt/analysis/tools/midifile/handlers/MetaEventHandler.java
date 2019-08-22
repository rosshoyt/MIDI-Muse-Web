package com.rosshoyt.analysis.tools.midifile.handlers;

import com.rosshoyt.analysis.model.internal.RawAnalysisStatisticsContainer;
import com.rosshoyt.analysis.tools.midifile.parsing.kaitai.StandardMidiFile;
import com.rosshoyt.analysis.model.raw.abstractions.RawTrackEvent;
import com.rosshoyt.analysis.model.raw.meta_events.RawTempoEvent;
import com.rosshoyt.analysis.model.raw.meta_events.RawTimeSignatureEvent;

public class MetaEventHandler {
   public static RawTrackEvent handleMetaEvent(StandardMidiFile.TrackEvent event, RawAnalysisStatisticsContainer rawAnalysisStatisticsContainer) {
      //System.out.println(" Meta Message Encountered"); TODO REFACTOR ENUM TO PRINT TYPE HERE (THIS LINE)
      if(event.metaEventBody() != null && event.metaEventBody().metaType() != null) {
         switch ((int)event.metaEventBody().metaType().id()) { // TODO test this line
            case 81: {
               System.out.print("Meta Message Tempo Event\n");
               rawAnalysisStatisticsContainer.numTempoEvents++;
               return parseTempoEvent(event.metaEventBody().body());
            }
            case 88: {
               System.out.print("Meta Message Time Signature Event\n");
               rawAnalysisStatisticsContainer.numTimeSignatureEvents++;
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
   public static RawTempoEvent parseTempoEvent(byte[] body) {
      // body.length (will?) be 3
      RawTempoEvent rawTempoEvent = new RawTempoEvent();
      System.out.print("Parsing tempo event... ");
      // TODO test tempo extraction from byte[]
      int x = (body[0] & 0xff) << 16 | (body[1] & 0xff) << 8 | (body[2] & 0xff);
      System.out.println("Extracted microseconds/quarternote = " + x);
      rawTempoEvent.setNumMicrosecondsPerQuarterNote(x);
      return rawTempoEvent;
   }

   public static RawTimeSignatureEvent parseTimeSignatureEvent(byte[] body) {
      // body.length() (will?) be 4
      // TODO check for correct value, throw exception
      RawTimeSignatureEvent rawTimeSignatureEvent = new RawTimeSignatureEvent();
      rawTimeSignatureEvent.setNumerator(body[0]);
      rawTimeSignatureEvent.setDenominator((int)Math.pow((double)2, body[1])); // raise 2 to the value of body[1]
      rawTimeSignatureEvent.setMidiClocksPerMetronomeClick(body[2]);
      rawTimeSignatureEvent.setNum32ndNotesPerBeat(body[3]);
      System.out.println("Extracted Time Signature: " + rawTimeSignatureEvent.getNumerator() + "/" + rawTimeSignatureEvent.getDenominator());
      System.out.println("32nd notes/beat: " + rawTimeSignatureEvent.getNum32ndNotesPerBeat() + ", Midi Clocks per Metronome beat: "
            + rawTimeSignatureEvent.getMidiClocksPerMetronomeClick());
      return rawTimeSignatureEvent;
   }
}
