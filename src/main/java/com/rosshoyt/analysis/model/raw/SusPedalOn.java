package com.rosshoyt.analysis.model.raw;

import lombok.*;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class SusPedalOn extends ControllerEvent {
}
