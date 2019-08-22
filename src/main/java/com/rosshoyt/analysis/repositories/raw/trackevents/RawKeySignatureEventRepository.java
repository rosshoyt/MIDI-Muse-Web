package com.rosshoyt.analysis.repositories.raw.trackevents;

import com.rosshoyt.analysis.model.raw.meta_events.RawKeySignatureEvent;
import com.rosshoyt.analysis.repositories.abstractions.BaseRepository;
import org.springframework.data.repository.CrudRepository;

public interface RawKeySignatureEventRepository extends BaseRepository<RawKeySignatureEvent, Long> {
}
