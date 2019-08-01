package com.rosshoyt.analysis.model.musical;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString
@Entity
public class Note extends MusicalEvent {
   @Id
   private Long id;

   @ManyToMany(fetch = FetchType.LAZY)
   private Set<Note> notes;

   private int pitch;
   private int velocity;
}