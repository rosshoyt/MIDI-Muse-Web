package com.rosshoyt.analysis.model.kaitai.smf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class _Header {
   public _Header(RawAnalysis rawAnalysis) {
      this.rawAnalysis = rawAnalysis;
   }

   @Id
   private Long id;

   @OneToOne
   @JoinColumn(name = "id")
   @MapsId
   @ToString.Exclude
   private RawAnalysis rawAnalysis;

   private int format;
   private int numTracks;
   private short division;

}
