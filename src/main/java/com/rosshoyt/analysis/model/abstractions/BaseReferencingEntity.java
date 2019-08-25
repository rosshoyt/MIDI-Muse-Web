package com.rosshoyt.analysis.model.abstractions;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.MappedSuperclass;


@Data
@NoArgsConstructor
@MappedSuperclass
@ToString(callSuper = true)
public abstract class BaseReferencingEntity extends BaseEntity {


//   public BaseReferencingEntity(Long fkMidiFileAnalysisId) {
//      this.fkMidiFileAnalysisId = fkMidiFileAnalysisId;
//   }

   private Long fkMidiFileAnalysisId;
}
