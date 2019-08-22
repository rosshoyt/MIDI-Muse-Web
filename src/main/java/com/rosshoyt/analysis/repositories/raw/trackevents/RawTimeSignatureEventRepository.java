package com.rosshoyt.analysis.repositories.raw.trackevents;

import com.rosshoyt.analysis.model.raw.meta_events.RawTimeSignatureEvent;
import com.rosshoyt.analysis.repositories.abstractions.BaseRepository;
import org.springframework.data.repository.CrudRepository;

public interface RawTimeSignatureEventRepository extends BaseRepository<RawTimeSignatureEvent, Long> {
}
