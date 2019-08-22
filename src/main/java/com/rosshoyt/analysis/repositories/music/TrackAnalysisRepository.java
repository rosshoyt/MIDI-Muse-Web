package com.rosshoyt.analysis.repositories.music;

import com.rosshoyt.analysis.model.music.Track;
import com.rosshoyt.analysis.repositories.abstractions.BaseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackAnalysisRepository extends BaseRepository<Track, Long> {

}
