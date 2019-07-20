package com.rosshoyt.analysis.midi_file_tools;




import com.rosshoyt.analysis.midi_file_tools.exceptions.InvalidMidiFileException;

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
   // TODO Refactor to avoid code duplication in analyze() methods

   public MidiFileAnalysis analyze(File file) throws IOException, InvalidMidiFileException {
      System.out.print("---Starting MIDI FILE ANALYSIS---");
      System.out.println("Validating and parsing file");
      StandardMidiFile smf;
      try{
         smf = midiFileValidator.validate(file);
      } catch(Exception e) {
         if(e instanceof InvalidMidiFileException) System.out.println("File not valid MIDI File extension");
         else if(e instanceof KaitaiStream.UnexpectedDataError) System.out.println("MIDI File contained invalid data");
         else e.printStackTrace();
         return null;
      }
      System.out.println("Validation passed, analyzing parse results");
      MidiFileAnalysis mfa = analyzeStandardMidiFile(smf);
      //TODO refactor to not extract byte[] twice (in below line and in MidiFileValidator class)
      mfa = addRawFileToMFA(mfa, file);

      return mfa;




   }
   public MidiFileAnalysis analyze(MultipartFile multipartFile) throws InvalidMidiFileException, IOException {
      MidiFileAnalysis midiFileAnalysis;

//      System.out.println("---Starting STANDARD MIDI FILE Analysis---");
//      if(FileUtils.isValidSMF(multipartFile)) {
//         System.out.println("--MultipartFile " + multipartFile.getOriginalFilename() + "passed validation--\n...Beginning parse...");
//         StandardMidiFile smf;
//         //smf = midiFileParser.parse(multipartFile);
//         //midiFileAnalysis = analyzeStandardMidiFile(smf);
//      } else throw new InvalidMidiFileException();
//      //return midiFileAnalysis;
      return null;
   }





   private MidiFileAnalysis analyzeStandardMidiFile(StandardMidiFile smf){
      System.out.println("---Analyzing the Kaitai Struct SMF parse---");
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
   private static MidiFileAnalysis addRawFileToMFA(MidiFileAnalysis mfa, File file){
      byte[] data;
      try{
         data = FileUtils.getByteArray(file);
      } catch(Exception e) {
         e.printStackTrace();
         return null;
      }
      MidiFile midiFile = new MidiFile();
      midiFile.setId(mfa.getId());
      midiFile.setData(data);
      midiFile.setFileName(file.getName());
      midiFile.setFileType(FileUtils.getExtension(file));
      mfa.setMidiFile(midiFile);
      return mfa;
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
