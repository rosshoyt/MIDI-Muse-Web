package com.rosshoyt.analysis.model.kaitai.smf;

import com.rosshoyt.analysis.model.kaitai.smf.meta_events.Tempo;
import com.rosshoyt.analysis.model.kaitai.smf.meta_events.TimeSignature;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events.NoteOffEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events.NoteOnEvent;
import com.rosshoyt.analysis.model.kaitai.smf.midi_events.controller_events.SustainPedalEvent;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Track {
   @Id
   private Long id;
   private Long fkRawAnalysisId;


//   @OneToMany(fetch =  FetchType.LAZY)
//   private ArrayList<MidiEvent> midiEvents;
   //MIDI EVENTS
   @OneToMany
   private List<NoteOnEvent> noteOns;
   @OneToMany
   private List<NoteOffEvent> noteOffs;
   @OneToMany
   private List<SustainPedalEvent> sustainPedalEvents;
   // META EVENTS
   @OneToMany
   private List<Tempo> tempos;
   @OneToMany
   private List<TimeSignature> timeSignatures;







}