package com.rosshoyt.analysis.model.abstractions;

import lombok.Data;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@ToString(callSuper = true)
public abstract class FlaggableEvent extends BaseReferencingEntity {


   private boolean flagged;
   private String flagMessage;

//   @Enumerated(EnumType.STRING)
//   private Enum<FlagType> flagType;
}
