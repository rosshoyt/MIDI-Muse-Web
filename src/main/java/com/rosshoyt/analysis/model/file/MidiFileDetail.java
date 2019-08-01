package com.rosshoyt.analysis.model.file;

import lombok.*;


import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MidiFileDetail {

   @Id
   private long id;

   private String fullFileName;
   private String fileName;
   private String fileExtension;

   /*
    * MThd header derived fields (in near order of appearance)
    */
   private int midiFileFormatType; // 0, 1 or 2
   private int numTracks;
   private float divisionType;

   @OneToOne
   private FileByteData fileByteData;

}
