package com.rosshoyt.analysis.midi_file_tools;




import com.rosshoyt.analysis.midi_file_tools.exceptions.InvalidMidiFileException;

import com.rosshoyt.analysis.midi_file_tools.kaitai.StandardMidiFile;
import com.rosshoyt.analysis.model.MidiFile;
import com.rosshoyt.analysis.model.MidiFileAnalysis;

import com.rosshoyt.analysis.model.MusicalAnalysis;
import com.rosshoyt.analysis.model.RawAnalysis;
import com.rosshoyt.analysis.utils.MidiFileUtils;
import io.kaitai.struct.ByteBufferKaitaiStream;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.sound.midi.InvalidMidiDataException;
import java.io.*;
import java.util.Properties;


/**
 * High level file analyzer.
 * Works with one uploaded .mid/.midi file to extract useful information and return
 *
 * SQL database-persistable analysis data
 */

public class MidiFileAnalyzer {
   
   private KaitaiSMFParser smfParser;
   /**
    *
    */
   public MidiFileAnalyzer() {
      this.smfParser = new KaitaiSMFParser();
   }
   // TODO Refactor to avoid code duplication in analyze() methods

   public MidiFileAnalysis analyze(MultipartFile multipartFile) throws InvalidMidiFileException, IOException {
      MidiFileAnalysis midiFileAnalysis;
      System.out.println("---Starting STANDARD MIDI FILE Analysis---");
      if(MidiFileUtils.isValidSMF(multipartFile)) {
         System.out.println("--MultipartFile " + multipartFile.getOriginalFilename() + "passed validation--\n...Beginning parse...");
         StandardMidiFile smf;
         smf = smfParser.parse(multipartFile);
         midiFileAnalysis = analyzeKaitaiSMF(smf);
      } else throw new InvalidMidiFileException();
      return midiFileAnalysis;
   }

   public MidiFileAnalysis analyze(File file) throws InvalidMidiFileException, IOException {
      System.out.print("---Starting MIDI FILE ANALYSIS---\n...Validating file...");
      if(MidiFileUtils.isValidSMF(file)) {
         System.out.print("MIDI File \"" + file.getName() + "\" passed validation\n--Parsing SMF with Kaitai Struct--\n");
         byte[] fileData = MidiFileUtils.fileToByteArray(file);

         StandardMidiFile smf = smfParser.parse(fileData);
         MidiFileAnalysis mfa = analyzeKaitaiSMF(smf);

         MidiFile midiFile = new MidiFile();
         midiFile.setData(fileData);
         midiFile.setFileName(file.getName());
         midiFile.setFileType(MidiFileUtils.getExtension(file));

         mfa.setMidiFile(midiFile);
         return mfa;

      } else throw new InvalidMidiFileException();
   }



   public MidiFileAnalysis analyzeKaitaiSMF(StandardMidiFile smf){
      System.out.println("---Analyzing the Kaitai SMF parse---");
      MidiFileAnalysis midiFileAnalysis = new MidiFileAnalysis();
      System.out.println("...Creating raw analysis...");
      RawAnalysis rawAnalysis = analyzeRaw(smf);
      midiFileAnalysis.setRawAnalysis(rawAnalysis);
      System.out.println("...Creating musical analysis...");
      MusicalAnalysis musicalAnalysis = analyzeMusic(rawAnalysis);
      midiFileAnalysis.setMusicalAnalysis(musicalAnalysis);
      System.out.println("---Analysis complete---");
      return midiFileAnalysis;
   }

   private static MusicalAnalysis analyzeMusic(RawAnalysis raw) {
      MusicalAnalysis mus = new MusicalAnalysis();
      return mus;
   }

   public static RawAnalysis analyzeRaw(StandardMidiFile smf) {
      RawAnalysis raw = new RawAnalysis();
      raw.setMidiFileFormatType(smf.hdr().format());
      raw.setNumTracks(smf.hdr().numTracks());
      raw.setDivisionType(smf.hdr().division());
      return raw;
   }
//   public static MidiFile getPersistableMidiFile(){
//      // TODO refactor -> find new location for this functionality
//      System.out.println("...Adding Binary File Data to the MidiFileAnalysis object for DB storage...");
//
//      MidiFile midiFile = new MidiFile();
//
//      midiFileAnalysis.setMidiFile(midiFile);
//
//
//   }



}
