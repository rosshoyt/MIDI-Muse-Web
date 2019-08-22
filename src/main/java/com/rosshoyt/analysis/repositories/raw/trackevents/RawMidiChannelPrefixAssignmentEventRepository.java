package com.rosshoyt.analysis.repositories.raw.trackevents;

import com.rosshoyt.analysis.model.raw.meta_events.RawMidiChannelPrefixAssignmentEvent;
import com.rosshoyt.analysis.repositories.abstractions.BaseRepository;
import org.springframework.data.repository.CrudRepository;

public interface RawMidiChannelPrefixAssignmentEventRepository extends BaseRepository<RawMidiChannelPrefixAssignmentEvent, Long> {
}
