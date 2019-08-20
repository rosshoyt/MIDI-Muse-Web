package com.rosshoyt.analysis.model.musical;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

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