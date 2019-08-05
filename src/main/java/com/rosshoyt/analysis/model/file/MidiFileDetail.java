package com.rosshoyt.analysis.model.file;

import lombok.*;


import javax.persistence.*;

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



   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private FileByteData fileByteData;

}
