package com.rosshoyt.analysis.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Chord {
   @Id
   @GeneratedValue
   private Long id;
   private String chordName;
   private Duration realTimelength;

}
