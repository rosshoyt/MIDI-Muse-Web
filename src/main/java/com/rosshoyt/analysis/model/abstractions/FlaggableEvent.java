package com.rosshoyt.analysis.model.abstractions;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public abstract class FlaggableEvent {
   private boolean flagged;
   @Enumerated(EnumType.STRING)
   private Enum<FlagType> flagType;
}
