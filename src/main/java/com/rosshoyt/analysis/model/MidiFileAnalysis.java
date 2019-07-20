package com.rosshoyt.analysis.model;

import lombok.*;

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
@ToString
@Entity
public class MidiFileAnalysis {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;

   @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   @MapsId
   private RawAnalysis rawAnalysis;

   @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   @MapsId
   private MusicalAnalysis musicalAnalysis;

   @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
   @MapsId
   private MidiFile midiFile;

}
