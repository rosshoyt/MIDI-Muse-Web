package com.rosshoyt.analysis.repositories.music;

import com.rosshoyt.analysis.model.music.Note;
import com.rosshoyt.analysis.repositories.abstractions.BaseRepository;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends BaseRepository<Note, Long> {
}
