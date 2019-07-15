package com.rosshoyt.analysis.repositories;

import com.rosshoyt.analysis.model.MidiFile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MidiFileRepository extends JpaRepository<MidiFile, Long> {
      MidiFile findByFileName(String fileName);
}
