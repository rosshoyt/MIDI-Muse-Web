package com.rosshoyt.analysis.model.file;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class MidiFileDetail {

   @Id
   private long id;

   private String fullFileName;
   private String fileName;
   private String fileExtension;



   @OneToOne
   private FileByteData fileByteData;

}
