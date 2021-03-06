package com.rosshoyt.analysis.model.file;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.abstractions.BaseReferencingEntity;
import lombok.*;


import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class MidiFileDetail extends BaseReferencingEntity {

   public MidiFileDetail(MidiFileAnalysis midiFileAnalysis) {
      this.midiFileAnalysis = midiFileAnalysis;
   }


   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id")
   @MapsId
   @ToString.Exclude
   @JsonBackReference
   private MidiFileAnalysis midiFileAnalysis;

   private String fullFileName;
   private String fileName;
   private String fileExtension;


   private int format;
   private int numTracks;
   private short division;


   @OneToOne
//         (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST,
//               mappedBy = "midiFileAnalysis")
//   @JsonManagedReference
   private FileByteData fileByteData;


}
