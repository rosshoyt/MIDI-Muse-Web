package com.rosshoyt.analysis.repositories;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MidiFileAnalysisRepository
      extends JpaRepository<MidiFileAnalysis, Long> {
   //MidiFileAnalysis findByFileName(String fileName);
}