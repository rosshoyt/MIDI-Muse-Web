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
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;
   @NonNull
   private String fileName;
   @NonNull
   private String fileType;

   @Lob
   @ToString.Exclude
   private byte[] data;

   @OneToOne(fetch = FetchType.LAZY)
   MidiFileAnalysis analysis;

}
