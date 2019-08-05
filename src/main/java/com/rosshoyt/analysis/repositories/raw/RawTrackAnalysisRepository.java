package com.rosshoyt.analysis.repositories.raw;

import com.rosshoyt.analysis.model.kaitai.smf._Track;
import org.springframework.data.repository.CrudRepository;

public interface RawTrackAnalysisRepository extends CrudRepository<_Track, Long> {

}
