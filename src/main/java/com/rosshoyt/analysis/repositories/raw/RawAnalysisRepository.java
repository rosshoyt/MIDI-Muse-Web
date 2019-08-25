package com.rosshoyt.analysis.repositories.raw;

import com.rosshoyt.analysis.model.raw.RawAnalysis;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface RawAnalysisRepository extends CrudRepository<RawAnalysis, Long> {
   Optional<RawAnalysis> findByFkMidiFileAnalysisId(Long fkMidiFileAnalysisId);
}
