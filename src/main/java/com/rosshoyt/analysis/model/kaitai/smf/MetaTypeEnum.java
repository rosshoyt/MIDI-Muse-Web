package com.rosshoyt.analysis.model.kaitai.smf;

import java.util.HashMap;
import java.util.Map;

public enum MetaTypeEnum {
   SEQUENCE_NUMBER(0),
   TEXT_EVENT(1),
   COPYRIGHT(2),
   SEQUENCE_TRACK_NAME(3),
   INSTRUMENT_NAME(4),
   LYRIC_TEXT(5),
   MARKER_TEXT(6),
   CUE_POINT(7),
   MIDI_CHANNEL_PREFIX_ASSIGNMENT(32),
   END_OF_TRACK(47),
   TEMPO(81),
   SMPTE_OFFSET(84),
   TIME_SIGNATURE(88),
   KEY_SIGNATURE(89),
   SEQUENCER_SPECIFIC_EVENT(127);

   private final long id;
   MetaTypeEnum(long id) { this.id = id; }
   public long id() { return id; }
   private static final Map<Long, MetaTypeEnum> byId = new HashMap<Long,MetaTypeEnum>(15);
   static {
      for (MetaTypeEnum e : MetaTypeEnum.values())
         byId.put(e.id(), e);
   }
   public static MetaTypeEnum byId(long id) { return byId.get(id); }
}