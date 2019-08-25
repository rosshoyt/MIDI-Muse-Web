package com.rosshoyt.analysis.repositories.raw.trackevents;

import com.rosshoyt.analysis.model.raw.midi_events.abstractions.RawNoteEvent;
import com.rosshoyt.analysis.repositories.abstractions.BaseReferencingRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RawNoteEventRepository extends BaseReferencingRepository<RawNoteEvent, Long> {
   /*@Query("SELECT e FROM #{#entityName} e WHERE e.fkMidiFileAnalysisId = fkMidiFileAnalysisId")
   Iterable<RawNoteEvent> findByFkMidiFileAnalysisId(Long fkBaseEntityId);*/
}
