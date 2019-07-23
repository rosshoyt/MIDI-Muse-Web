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
   @GeneratedValue(strategy=GenerationType.SEQUENCE)
   private Long id;

   //@OneToOne(mappedBy = "midiFileAnalysis", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   @OneToOne
   private RawAnalysis rawAnalysis;

   ///@OneToOne(mappedBy = "midiFileAnalysis", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   @OneToOne
   private MusicalAnalysis musicalAnalysis;

   //@OneToOne(mappedBy = "midiFileAnalysis", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   @OneToOne
   private MidiFile midiFile;

}
