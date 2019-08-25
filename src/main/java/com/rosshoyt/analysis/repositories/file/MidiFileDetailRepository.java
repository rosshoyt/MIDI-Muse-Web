package com.rosshoyt.analysis.repositories.file;



import com.rosshoyt.analysis.model.file.MidiFileDetail;
import com.rosshoyt.analysis.repositories.abstractions.BaseReferencingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidiFileDetailRepository extends BaseReferencingRepository<MidiFileDetail, Long> {

}
