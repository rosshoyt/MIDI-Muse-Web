package com.rosshoyt.analysis.repositories.raw;

import com.rosshoyt.analysis.model.kaitai.smf._TrackEvent;
import org.springframework.data.repository.CrudRepository;

public interface RawTrackEventRepository extends CrudRepository<_TrackEvent, Long> {

}
