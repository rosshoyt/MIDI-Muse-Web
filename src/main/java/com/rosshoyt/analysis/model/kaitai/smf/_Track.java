package com.rosshoyt.analysis.model.kaitai.smf;

import lombok.Data;


import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class _Track {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long id;
   // FKs
   private Long fkMidiFileAnalysisId;
   // Track Data
   private int trackNumber;
   private int numTrackEvents;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<_TrackEventContainer> trackEventContainers;






}