package com.rosshoyt.analysis.model.file;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import lombok.*;

import javax.annotation.Nonnull;
import javax.persistence.*;


/**
 * Holds byte[] of a Standard Midi File
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileByteData {
   public FileByteData(MidiFileAnalysis midiFileAnalysis, byte[] file) {
      this.midiFileAnalysis = midiFileAnalysis;
      this.file = file;
   }

   @Id
   private Long id;

   @OneToOne
   @JoinColumn(name = "id")
   @MapsId
   @ToString.Exclude
   @JsonBackReference
   private MidiFileAnalysis midiFileAnalysis;

   @Lob
   @ToString.Exclude
   private byte[] file;

//   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//   @JoinColumn(name = "id")
//
//   private MidiFileDetail midiFileDetail;





}
