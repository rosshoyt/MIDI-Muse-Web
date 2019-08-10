package com.rosshoyt.analysis.model.file;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import lombok.*;


import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class MidiFileDetail {

   public MidiFileDetail(MidiFileAnalysis midiFileAnalysis) {
      this.midiFileAnalysis = midiFileAnalysis;
   }

   @Id
   private long id;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id")
   @MapsId
   @ToString.Exclude
   @JsonBackReference
   private MidiFileAnalysis midiFileAnalysis;

   private String fullFileName;
   private String fileName;
   private String fileExtension;



   @OneToOne
//         (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST,
//               mappedBy = "midiFileAnalysis")
//   @JsonManagedReference
   private FileByteData fileByteData;


}
