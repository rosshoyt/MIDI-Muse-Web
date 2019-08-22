package com.rosshoyt.analysis.repositories;
import com.rosshoyt.analysis.model.MidiFileAnalysis;

import com.rosshoyt.analysis.repositories.abstractions.BaseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface MidiFileAnalysisRepository extends CrudRepository<MidiFileAnalysis, Long> {

}