package com.rosshoyt.analysis.repositories;

import com.rosshoyt.analysis.model.kaitai.smf.RawAnalysis;
import org.springframework.data.repository.CrudRepository;

public interface RawAnalysisRepository extends CrudRepository<RawAnalysis, Long> {
}
