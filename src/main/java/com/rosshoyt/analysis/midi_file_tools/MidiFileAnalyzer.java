package com.rosshoyt.analysis.midi_file_tools;




import com.rosshoyt.analysis.midi_file_tools.exceptions.InvalidMidiFileException;

import com.rosshoyt.analysis.midi_file_tools.exceptions.UnexpectedMidiDataException;
import com.rosshoyt.analysis.midi_file_tools.kaitai.StandardMidiFile;
import com.rosshoyt.analysis.model.MidiFile;
import com.rosshoyt.analysis.model.MidiFileAnalysis;

import com.rosshoyt.analysis.model.MusicalAnalysis;
import com.rosshoyt.analysis.model.RawAnalysis;
import com.rosshoyt.analysis.utils.FileUtils;
import io.kaitai.struct.KaitaiStream;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;


/**
 * High level file analyzer.
 * Works with one uploaded .mid/.midi file to extract useful information and return
 *
 * SQL database-persistable analysis data
 */

public class MidiFileAnalyzer {
   

   private MidiFileValidator midiFileValidator;
   /**
    *
    */
   public MidiFileAnalyzer() {

      this.midiFileValidator = new MidiFileValidator();
   }

//   public MidiFileAnalysis analyzeParseResult(MidiFileAnalysis midiFileAnalysis,
//                                              ParseResult parseResult){
//      System.out.println("---Analyzing the Kaitai Struct SMF parse---");
//      //MidiFileAnalysis midiFileAnalysis = new MidiFileAnalysis();
//
//      System.out.println("...Creating raw analysis...");
//      RawAnalysis rawAnalysis = analyzeRaw(parseResult.smf);
//      rawAnalysis.setMidiFileAnalysis(midiFileAnalysis);
//      midiFileAnalysis.setRawAnalysis(rawAnalysis);
//
//      System.out.println("...Creating musical analysis...");
//      MusicalAnalysis musicalAnalysis = analyzeMusic(rawAnalysis);
//      musicalAnalysis.setMidiFileAnalysis(midiFileAnalysis);
//      midiFileAnalysis.setMusicalAnalysis(musicalAnalysis);
//
//      System.out.println("---Analysis complete---");
//      return midiFileAnalysis;
//   }

   public static RawAnalysis analyzeRaw(StandardMidiFile smf) {
      RawAnalysis raw = new RawAnalysis();
      raw.setMidiFileFormatType(smf.hdr().format());
      raw.setNumTracks(smf.hdr().numTracks());
      raw.setDivisionType(smf.hdr().division());
      return raw;
   }

   public static MusicalAnalysis analyzeMusic(RawAnalysis raw) {
      MusicalAnalysis mus = new MusicalAnalysis();
      return mus;
   }
//   public static MidiFileAnalysis addFileData(MidiFileAnalysis mfa, ParseResult parseResult){
//
//      return mfa;
//   }


//   private static MidiFileAnalysis addRawFileToMFA(MidiFileAnalysis mfa, Fidatale file){
//      byte[] data;
//      try{
//         data = FileUtils.getByteArray(file);
//      } catch(Exception e) {
//         e.printStackTrace();
//         return null;
//      }
//      MidiFile midiFile = new MidiFile();
//      midiFile.setId(mfa.getId());
//      midiFile.setData(data);
//      midiFile.setFileName(file.getName());
//      midiFile.setFileType(FileUtils.getExtension(file));
//      mfa.setMidiFile(midiFile);
//      return mfa;
//   }



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
