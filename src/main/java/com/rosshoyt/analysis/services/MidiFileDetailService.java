package com.rosshoyt.analysis.services;

import com.rosshoyt.analysis.midifile.tools.rawanalysis.SMFAnalyzer;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.file.FileByteData;
import com.rosshoyt.analysis.model.file.MidiFileDetail;
import com.rosshoyt.analysis.model.internal.ValidatedParseResult;
import com.rosshoyt.analysis.repositories.file.FileByteDataRepository;
import com.rosshoyt.analysis.repositories.file.MidiFileDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MidiFileDetailService {
   private final MidiFileDetailRepository midiFileDetailRepository;
   private final FileByteDataRepository fileByteDataRepository;

   @Autowired
   public MidiFileDetailService(MidiFileDetailRepository midiFileDetailRepository, FileByteDataRepository fileByteDataRepository) {
      this.midiFileDetailRepository = midiFileDetailRepository;
      this.fileByteDataRepository = fileByteDataRepository;
   }

   public List<MidiFileDetail> getMidiFileDetailList() {
      List<MidiFileDetail> midiFileDetailList = new ArrayList<>();
      midiFileDetailRepository.findAll().forEach(midiFileDetailList::add);
      return midiFileDetailList;
   }

   public MidiFileDetail addMidiFileDetail(MidiFileAnalysis mfa, ValidatedParseResult parseResult) {
      MidiFileDetail mfd = new MidiFileDetail(mfa);
      mfd.setFileName(parseResult.fileName);
      mfd.setFileExtension(parseResult.extension);
      mfd.setFullFileName(parseResult.fileName + "." + parseResult.extension);
      mfd.setFileByteData(fileByteDataRepository.save(new FileByteData(mfa, parseResult.data)));
      return midiFileDetailRepository.save(mfd);
   }
}
