package com.rosshoyt.analysis.repositories.raw.trackevents;

import com.rosshoyt.analysis.model.raw.midi_events.controller_events.RawSustainPedalEvent;
import com.rosshoyt.analysis.repositories.abstractions.BaseRepository;
import org.springframework.data.repository.CrudRepository;

public interface RawSustainPedalEventRepository extends BaseRepository<RawSustainPedalEvent, Long> {
}
