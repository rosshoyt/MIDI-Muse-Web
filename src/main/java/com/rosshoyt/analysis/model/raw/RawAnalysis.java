package com.rosshoyt.analysis.model.raw;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.abstractions.TickOrderedEventComparator;
import com.rosshoyt.analysis.model.internal.RawEventCountContainer;
import com.rosshoyt.analysis.model.raw.meta_events.Tempo;
import com.rosshoyt.analysis.model.raw.meta_events.TimeSignature;
import com.rosshoyt.analysis.model.raw.midi_events._NoteOffEvent;
import com.rosshoyt.analysis.model.raw.midi_events._NoteOnEvent;
import com.rosshoyt.analysis.model.raw.midi_events.controller_events._SustainPedalEvent;
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
public class RawAnalysis {

   public RawAnalysis(MidiFileAnalysis midiFileAnalysis) {
      this.midiFileAnalysis = midiFileAnalysis;
   }

   @Id
   private Long id;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id")
   @MapsId
   @ToString.Exclude
   @JsonBackReference
   private MidiFileAnalysis midiFileAnalysis;

   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "rawAnalysis")
   @JsonManagedReference
   private RawEventCounts rawEventCounts;

   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "rawAnalysis")
   @JsonManagedReference
   private _Header header;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   private List<_TrackEvent> trackEvents;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @SortComparator(TickOrderedEventComparator.class)
   private SortedSet<_NoteOnEvent> noteOnEvents = new TreeSet<>();

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @SortComparator(TickOrderedEventComparator.class)
   private SortedSet<_NoteOffEvent> noteOffEvents = new TreeSet<>();

   // (Controller Event)
   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @SortComparator(TickOrderedEventComparator.class)
   private SortedSet<_SustainPedalEvent> sustainPedalEventList = new TreeSet<>();

   // Meta Events
   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @SortComparator(TickOrderedEventComparator.class)
   private SortedSet<Tempo> tempoEvents = new TreeSet<>();

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @SortComparator(TickOrderedEventComparator.class)
   private SortedSet<TimeSignature> timeSignatureEvents = new TreeSet<>();

//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_NoteOnEvent> noteOnEventList;
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//   private List<_NoteOffEvent> noteOffEventList;
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


}
