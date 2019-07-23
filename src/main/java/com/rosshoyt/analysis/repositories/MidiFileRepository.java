package com.rosshoyt.analysis.repositories;

import com.rosshoyt.analysis.model.MidiFile;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface MidiFileRepository extends CrudRepository<MidiFile, Long> {

}
