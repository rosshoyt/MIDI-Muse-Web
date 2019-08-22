package com.rosshoyt.analysis.model.abstractions;

import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class BaseEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long id;


}
