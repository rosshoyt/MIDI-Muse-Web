package com.rosshoyt.analysis.repositories.abstractions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
   Iterable<T> findAllByFkMidiFileAnalysisId(Long fkMidiFileAnalysisId);
}
