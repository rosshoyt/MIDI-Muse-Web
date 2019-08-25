package com.rosshoyt.analysis.model.raw;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.abstractions.BaseReferencingEntity;
import com.rosshoyt.analysis.model.abstractions.TickOrderedEventComparator;
import com.rosshoyt.analysis.model.raw.meta_events.RawTempoEvent;
import com.rosshoyt.analysis.model.raw.meta_events.RawTimeSignatureEvent;
import com.rosshoyt.analysis.model.raw.midi_events.RawNoteOffEvent;
import com.rosshoyt.analysis.model.raw.midi_events.RawNoteOnEvent;
import com.rosshoyt.analysis.model.raw.midi_events.abstractions.RawNoteEvent;
import com.rosshoyt.analysis.model.raw.midi_events.controller_events.RawSustainPedalEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;


@Data
@NoArgsConstructor
@Entity
public class RawAnalysis extends BaseReferencingEntity {

   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JsonManagedReference
   private RawAnalysisStatistics rawAnalysisStatistics;

   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JsonManagedReference
   private RawHeader rawHeader;

   //      @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_NoteOnEvent> noteOnEventList;
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<RawNoteEvent> rawNoteEvents;
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<RawSustainPedalEvent> rawSustainPedalEvents;
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<RawTempoEvent> rawTempoEvents;
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<RawTimeSignatureEvent> rawTimeSignatureEvents;

   //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   //private List<RawTrackEvent> trackEvents;

//   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//   @SortComparator(TickOrderedEventComparator.class)
//   private SortedSet<RawNoteOnEvent> noteOnEvents = new TreeSet<>();
//
//   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//   @SortComparator(TickOrderedEventComparator.class)
//   private SortedSet<RawNoteOffEvent> noteOffEvents = new TreeSet<>();

   // (Controller Event)
//   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//   @SortComparator(TickOrderedEventComparator.class)
//   private SortedSet<RawSustainPedalEvent> sustainPedalEventList = new TreeSet<>();

   // Meta Events
//   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//   @SortComparator(TickOrderedEventComparator.class)
//   private SortedSet<RawTempoEvent> rawTempoEventEvents = new TreeSet<>();
//
//   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//   @SortComparator(TickOrderedEventComparator.class)
//   private SortedSet<RawTimeSignatureEvent> rawTimeSignatureEventEvents = new TreeSet<>();


   // Meta Events
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_TrackEvent> copyrightEventList;
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_TrackEvent> cuePointEventList;
//   //private List<_TrackEvent> endOfTrackEventList;
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_TrackEvent> instrumentNameEventList;
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_TrackEvent> keySignatureEventList;
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_TrackEvent> lyricTextEventList;
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_TrackEvent> markerTextEventList;
//
//   /**
//    * The MIDI channel prefix meta message specifies a MIDI channel
//    * so that meta messages that follow are specific to a channel.
//    */
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_TrackEvent> midiChannelPrefixAssignmentEventList;
//   //private List<_TrackEvent> sequenceNumberEventList;
//   //private List<_TrackEvent> sequencerSpecificEventList;
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_TrackEvent> sequenceTrackNameEventList;
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_TrackEvent> smpteOffsetEventList;
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_TrackEvent> tempoEventList;
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_TrackEvent> textEventList;
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_TrackEvent> timeSignatureEventList;
/*
OLD CONSTRUCTOR AND BACK REFERENCE TO BASE ENTITY
 */
//   public RawAnalysis(MidiFileAnalysis midiFileAnalysis) {
//      this.midiFileAnalysis = midiFileAnalysis;
//   }
//
//   @OneToOne(fetch = FetchType.LAZY)
//   @JoinColumn(name = "id")
//   @MapsId
//   @ToString.Exclude
//   @JsonBackReference
//   private MidiFileAnalysis midiFileAnalysis;

}
