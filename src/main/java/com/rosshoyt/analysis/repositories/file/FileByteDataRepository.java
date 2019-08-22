package com.rosshoyt.analysis.repositories.file;

import com.rosshoyt.analysis.model.file.FileByteData;
import com.rosshoyt.analysis.repositories.abstractions.BaseRepository;
import org.springframework.data.repository.CrudRepository;

public interface FileByteDataRepository extends BaseRepository<FileByteData, Long> {
}
