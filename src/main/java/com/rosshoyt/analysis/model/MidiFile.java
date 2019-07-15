package com.rosshoyt.analysis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;
import javax.persistence.*;


/**
 *
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class MidiFile {

   @Id
   @GeneratedValue
   private Long id;
   @NonNull
   private String fileName;
   @NonNull
   private String fileType;
   @Lob
   private byte[] data;

   @OneToOne(fetch = FetchType.LAZY)
   MidiFileAnalysis analysis;

}
