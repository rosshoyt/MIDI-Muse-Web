package com.rosshoyt.analysis.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rosshoyt.analysis.model.abstractions.BaseEntity;
import com.rosshoyt.analysis.model.file.MidiFileDetail;
import com.rosshoyt.analysis.model.raw.RawAnalysis;
import com.rosshoyt.analysis.model.music.MusicalAnalysis;

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
public class MidiFileAnalysis extends BaseEntity {


   @OneToOne(fetch = FetchType.LAZY)
   @JsonManagedReference
   private RawAnalysis rawAnalysis;

   @OneToOne(fetch = FetchType.LAZY, mappedBy = "midiFileAnalysis")
   @JsonManagedReference
   private MusicalAnalysis musicalAnalysis;

   @OneToOne(fetch = FetchType.EAGER, mappedBy = "midiFileAnalysis")
   @JsonManagedReference
   private MidiFileDetail midiFileDetail;

}
