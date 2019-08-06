package com.rosshoyt.analysis.model.kaitai.smf;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;
import java.util.List;
@Data
@Transactional
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

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   private List<_TrackEvent> trackEventContainerList;






}