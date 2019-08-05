package com.rosshoyt.analysis.repositories.music;

import com.rosshoyt.analysis.model.musical.Track;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackAnalysisRepository extends CrudRepository<Track, Long> {

}
