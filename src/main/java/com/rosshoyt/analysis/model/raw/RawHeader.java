package com.rosshoyt.analysis.model.raw;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rosshoyt.analysis.model.abstractions.BaseReferencingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RawHeader extends BaseReferencingEntity {

   private int format;
   private int numTracks;
   private short division;


}
