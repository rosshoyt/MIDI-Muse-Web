package com.rosshoyt.analysis.repositories;

import com.rosshoyt.analysis.model.kaitai.smf._Track;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackAnalysisRepository extends CrudRepository<_Track, Long> {

}
