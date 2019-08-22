package com.rosshoyt.analysis.model.music;

import com.rosshoyt.analysis.model.music.abstractions.MusicalEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
public class Note extends MusicalEvent {


//   @ManyToMany(fetch = FetchType.LAZY)
//   private Set<Note> notes;

   private int pitch;
   private int velocityOn;
   private int velocityOff;
}