package com.rosshoyt.analysis.model;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MidiFileAnalysisRepository
      extends JpaRepository<MidiFileAnalysis, Long> {
   MidiFileAnalysis findByFileName(String fileName);
}