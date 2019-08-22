package com.rosshoyt.analysis.model.music;


import com.rosshoyt.analysis.model.music.abstractions.MusicalEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@Entity
public class SustainPedal extends MusicalEvent {
   @Id
   Long id;
}
