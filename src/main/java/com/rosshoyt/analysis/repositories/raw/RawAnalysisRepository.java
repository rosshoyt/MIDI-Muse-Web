package com.rosshoyt.analysis.repositories.raw;

import com.rosshoyt.analysis.model.raw.RawAnalysis;
import com.rosshoyt.analysis.repositories.abstractions.BaseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RawAnalysisRepository extends CrudRepository<RawAnalysis, Long> {
   Optional<RawAnalysis> findByFkMidiFileAnalysisId(Long fkMidiFileAnalysisId);
}
