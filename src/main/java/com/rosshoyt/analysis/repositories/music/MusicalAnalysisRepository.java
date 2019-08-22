package com.rosshoyt.analysis.repositories.music;

import com.rosshoyt.analysis.model.music.MusicalAnalysis;
import com.rosshoyt.analysis.repositories.abstractions.BaseRepository;
import org.springframework.data.repository.CrudRepository;

public interface MusicalAnalysisRepository extends BaseRepository<MusicalAnalysis, Long> {
}
