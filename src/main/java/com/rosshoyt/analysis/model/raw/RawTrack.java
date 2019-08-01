package com.rosshoyt.analysis.model.raw;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
@Entity
public class RawTrack {
   @Id // TODO auto-gen id (many-one)
   private long id;

   private int trackNumber;
   private String trackName;

   private int numMidiEvents;

   @OneToMany(fetch = FetchType.LAZY)
   private List<NoteOn> noteOnList;
   @OneToMany(fetch = FetchType.LAZY)
   private List<NoteOff> noteOffList;

   @OneToMany(fetch = FetchType.LAZY)
   private List<SusPedalOn> susPedalOnList;
   @OneToMany(fetch = FetchType.LAZY)
   private List<SusPedalOff> susPedalOffList;
   @OneToMany(fetch = FetchType.LAZY)
   private List<TempoEvent> tempoEventList;

}
