package com.rosshoyt.analysis.model.file;

import lombok.*;

import javax.annotation.Nonnull;
import javax.persistence.*;


/**
 * Holds byte[] of a Standard Midi File
 */
@Data
@AllArgsConstructor
@ToString
@Entity
public class FileByteData {

   @Id
   private Long id;

   @Lob
   @ToString.Exclude
   private byte[] file;







}
