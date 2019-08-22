package com.rosshoyt.analysis.model.abstractions;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;


@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseReferencingEntity extends BaseEntity {


//   public BaseReferencingEntity(Long fkMidiFileAnalysisId) {
//      this.fkMidiFileAnalysisId = fkMidiFileAnalysisId;
//   }

   private Long fkMidiFileAnalysisId;
}
