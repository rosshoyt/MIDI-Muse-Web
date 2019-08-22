package com.rosshoyt.analysis.model.raw.meta_events;

import com.rosshoyt.analysis.model.raw.abstractions.RawTrackEvent;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class RawSMPTEOffsetEvent extends RawTrackEvent {

}