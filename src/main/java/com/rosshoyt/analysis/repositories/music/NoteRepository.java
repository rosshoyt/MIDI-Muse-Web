package com.rosshoyt.analysis.repositories.music;

import com.rosshoyt.analysis.model.musical.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {
}
