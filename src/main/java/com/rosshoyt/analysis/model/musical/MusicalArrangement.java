package com.rosshoyt.analysis.model.musical;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MusicalArrangement {
    @Id
    private Long id;


}
