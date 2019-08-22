package com.rosshoyt.analysis.model.music;
import com.rosshoyt.analysis.model.abstractions.BaseReferencingEntity;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Chord extends BaseReferencingEntity {

   private String chordName;
   private Duration realTimelength;

   @ManyToMany(fetch = FetchType.LAZY)
   private Set<Note> notes;



}
