package com.rosshoyt.analysis.model;

import lombok.*;

import javax.annotation.Nonnull;
import javax.persistence.*;


/**
 *
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class MidiFile {

   @Id
   private Long id;
   
   @Lob
   @ToString.Exclude
   private byte[] file;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id")
   @MapsId
   @ToString.Exclude 
   private MidiFileAnalysis midiFileAnalysis;

   @NonNull
   private String fileName;
   
   @NonNull
   private String fileExtension;

   public MidiFile(String fileName, String fileExtension, byte[] file){
      this.fileName = fileName;
      this.fileExtension = fileExtension;
      this.file = file;
   }


}
