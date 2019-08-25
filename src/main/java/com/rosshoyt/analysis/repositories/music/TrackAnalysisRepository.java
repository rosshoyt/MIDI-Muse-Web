package com.rosshoyt.analysis.repositories.music;

import com.rosshoyt.analysis.model.music.Track;
import com.rosshoyt.analysis.repositories.abstractions.BaseReferencingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackAnalysisRepository extends BaseReferencingRepository<Track, Long> {

}
