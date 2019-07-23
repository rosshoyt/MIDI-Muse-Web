package com.rosshoyt.analysis.repositories;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MidiFileAnalysisRepository
      extends CrudRepository<MidiFileAnalysis, Long> {

}