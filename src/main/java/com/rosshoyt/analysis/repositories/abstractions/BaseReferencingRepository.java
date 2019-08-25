package com.rosshoyt.analysis.repositories.abstractions;

import com.rosshoyt.analysis.model.abstractions.BaseReferencingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

@NoRepositoryBean
public interface BaseReferencingRepository <T, ID extends Serializable> extends CrudRepository<T, ID> {
   //@Query("SELECT e FROM #{#entityName} e WHERE e.fkMidiFileAnalysisId = fkMidiFileAnalysisId")
   List<T> findByFkMidiFileAnalysisId(Long fkBaseEntityID);

}
/*
   CODE TESTED IN BELOW COMMENTS:
   @Query("SELECT e FROM #{#entityName} e WHERE e.fkMidiFileAnalysisId = fkMidiFileAnalysisId")
   Iterable<T> findByFkMidiFileAnalysisId(Long fkMidiFileAnalysisId);
 */
   /* WITH @QUERY ACTIVATED, HIBERNATE PERFORMED:
   In RawTrackEventService.getRawNoteEvents()
Hibernate:
    select
        rawnoteeve0_.id as id2_26_,
        rawnoteeve0_.fk_midi_file_analysis_id as fk_midi_3_26_,
        rawnoteeve0_.flag_message as flag_mes4_26_,
        rawnoteeve0_.flagged as flagged5_26_,
        rawnoteeve0_.tick as tick6_26_,
        rawnoteeve0_.channel as channel7_26_,
        rawnoteeve0_.track_number as track_nu8_26_,
        rawnoteeve0_.v_time as v_time9_26_,
        rawnoteeve0_.note as note10_26_,
        rawnoteeve0_.velocity as velocit11_26_,
        rawnoteeve0_.dtype as dtype1_26_
    from
        raw_note_event rawnoteeve0_
    where
        rawnoteeve0_.fk_midi_file_analysis_id=rawnoteeve0_.fk_midi_file_analysis_id
Returning list: []
    */
   /* WITH @QUERY DEACTIVATED, HIBERNATE PERFORMED:
   In RawTrackEventService.getRawNoteEvents()
Hibernate:
    select
        rawnoteeve0_.id as id2_26_,
        rawnoteeve0_.fk_midi_file_analysis_id as fk_midi_3_26_,
        rawnoteeve0_.flag_message as flag_mes4_26_,
        rawnoteeve0_.flagged as flagged5_26_,
        rawnoteeve0_.tick as tick6_26_,
        rawnoteeve0_.channel as channel7_26_,
        rawnoteeve0_.track_number as track_nu8_26_,
        rawnoteeve0_.v_time as v_time9_26_,
        rawnoteeve0_.note as note10_26_,
        rawnoteeve0_.velocity as velocit11_26_,
        rawnoteeve0_.dtype as dtype1_26_
    from
        raw_note_event rawnoteeve0_
    where
        rawnoteeve0_.fk_midi_file_analysis_id=rawnoteeve0_.fk_midi_file_analysis_id
Returning list: []
    */
