package com.rosshoyt.analysis.model.musical;


import com.rosshoyt.analysis.model.musical.MusicalEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Data
@NoArgsConstructor
@Transactional
@Entity
public class SustainPedal extends MusicalEvent {
   @Id
   Long id;
}
