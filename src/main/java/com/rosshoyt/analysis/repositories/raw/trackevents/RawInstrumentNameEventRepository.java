package com.rosshoyt.analysis.repositories.raw.trackevents;

import com.rosshoyt.analysis.model.raw.meta_events.RawInstrumentNameEvent;
import com.rosshoyt.analysis.repositories.abstractions.BaseRepository;
import org.springframework.data.repository.CrudRepository;

public interface RawInstrumentNameEventRepository extends BaseRepository<RawInstrumentNameEvent, Long> {
}
