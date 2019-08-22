package com.rosshoyt.analysis.repositories.raw.trackevents;

import com.rosshoyt.analysis.model.raw.meta_events.RawTempoEvent;
import com.rosshoyt.analysis.repositories.abstractions.BaseRepository;
import org.springframework.data.repository.CrudRepository;

public interface RawTempoEventRepository extends BaseRepository<RawTempoEvent, Long> {
}
