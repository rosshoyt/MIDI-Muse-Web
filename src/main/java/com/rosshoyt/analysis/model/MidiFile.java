package com.rosshoyt.analysis.model;

import lombok.*;

import javax.annotation.Nonnull;
import javax.persistence.*;


/**
 * TODO Move byte[] data to separate from the midifile naming data
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class MidiFile {

   @Id
   private Long id;


   //TODO Remove this reference if keeps causing problems, manually set id
//   @OneToOne(fetch = FetchType.EAGER)
//   @JoinColumn(name = "id")
//   @ToString.Exclude
//   @MapsId
//   private MidiFileAnalysis midiFileAnalysis;

   @Lob
   @ToString.Exclude
   private byte[] file;


   @NonNull
   private String fileName;
   
   @NonNull
   private String fileExtension;

   // TODO Add full file name support
   //String fullFileName;

   public MidiFile(String fileName, String fileExtension, byte[] file){
      this.fileName = fileName;
      this.fileExtension = fileExtension;
      this.file = file;
   }


}
