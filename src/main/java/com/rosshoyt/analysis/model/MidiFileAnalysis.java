package com.rosshoyt.analysis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Base Spring JPA Persistence Wrapper for JSON Response to
 * MIDIAnalysis file upload
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class MidiFileAnalysis {

   @Id
   @GeneratedValue
   private Long id;

   @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   @MapsId
   private RawAnalysis rawAnalysis;

   @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   @MapsId
   private MusicalAnalysis musicalAnalysis;

   @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
   private MidiFile midiFile;

}
