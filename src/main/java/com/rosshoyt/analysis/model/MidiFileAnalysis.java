package com.rosshoyt.analysis.model;

import com.rosshoyt.analysis.model.file.MidiFileDetail;
import com.rosshoyt.analysis.model.kaitai.smf.RawAnalysis;
import com.rosshoyt.analysis.model.musical.MusicalAnalysis;

import lombok.*;

import javax.persistence.*;

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


   ///@OneToOne(mappedBy = "midiFileAnalysis", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   @OneToOne(fetch = FetchType.LAZY)
   private MusicalAnalysis musicalAnalysis;

   //@OneToOne(mappedBy = "midiFileAnalysis", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
   @OneToOne(fetch = FetchType.LAZY)
   private RawAnalysis rawAnalysis;

   @OneToOne(fetch = FetchType.EAGER)
   private MidiFileDetail midiFileDetail;

}
