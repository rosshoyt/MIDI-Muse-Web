package com.rosshoyt.analysis.repositories;

import com.rosshoyt.analysis.model.file.FileByteData;

import com.rosshoyt.analysis.model.file.MidiFileDetail;
import org.springframework.data.repository.CrudRepository;

public interface FileDetailRepository extends CrudRepository<MidiFileDetail, Long> {

}
