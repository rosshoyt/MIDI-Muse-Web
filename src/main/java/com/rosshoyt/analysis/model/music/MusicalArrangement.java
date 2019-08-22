package com.rosshoyt.analysis.model.music;

import com.rosshoyt.analysis.model.abstractions.BaseReferencingEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@Entity
public class MusicalArrangement extends BaseReferencingEntity {

}
