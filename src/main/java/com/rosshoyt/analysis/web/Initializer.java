package com.rosshoyt.analysis.web;

import com.rosshoyt.analysis.midi_file_tools.MidiFileAnalyzer;
import com.rosshoyt.analysis.midi_file_tools.MidiFileValidator;
import com.rosshoyt.analysis.midi_file_tools.ParseResult;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.repositories.MidiFileAnalysisRepository;
import com.rosshoyt.analysis.utils.LocalDirectoryScanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;


import java.io.File;
import java.util.*;

@Component
@EntityScan("com.rosshoyt.analysis")
class Initializer implements CommandLineRunner {

   // Utilites to pre-load 'Example' MIDI files into database
   private static final String PRELOADED_MIDI_FILES_DIR = "preloaded-midi-files";
   private static LocalDirectoryScanner directoryScanner = new LocalDirectoryScanner(
         PRELOADED_MIDI_FILES_DIR, MidiFileValidator.MIDI_FILE_EXTENSIONS_SUPPORTED);

   private MidiFileAnalyzer midiFileAnalyzer;

   private final MidiFileAnalysisRepository repository;

   public Initializer(MidiFileAnalysisRepository repository) {

      this.repository = repository;
      midiFileAnalyzer = new MidiFileAnalyzer();
   }

   @Override
   public void run(String... strings) {
      fillMFAR();
   }

   private void fillMFAR() {
      List<File> preloadMidiFiles = directoryScanner.getFiles();

      for(File file: preloadMidiFiles) {
         System.out.println("Analyzing pre-loaded midi file " + file.getName());
         try {

            ParseResult parseResult= midiFileAnalyzer.initialParse(file);

            MidiFileAnalysis mfa = repository.save(new MidiFileAnalysis());
            mfa = midiFileAnalyzer.analyzeParseResult(mfa, parseResult);
            repository.save(mfa);

            System.out.println(mfa);
            //repository.save(midiFileAnalysis);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      System.out.println("All entries added to repo, here they are ->");
      repository.findAll().forEach(System.out::println);
   }


/*      Stream.of(fakeFileNames).forEach(name ->
            repository.save(new MidiFileAnalysis(name))
      );

      MidiFileAnalysis mfa = repository.findByFileName("coolsong.mid");
      ArrayList<Chord> chords = new ArrayList<>();


      chords.add(Chord.builder().chordName("C major").realTimelength(Duration.ofSeconds(20)).build());
      chords.add(Chord.builder().chordName("F major").realTimelength(Duration.ofSeconds(10)).build());
      chords.add(Chord.builder().chordName("Ab minor").realTimelength(Duration.ofSeconds(10)).build());
      mfa.setChords(chords);
*/




//   private List<File> getPreloadedMidiFiles(Path dir){
//      System.out.println("---Scanning preloaded .MIDI/.MID files---");
//      ArrayList<File> midiFiles = new ArrayList<>();
//      try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir)) {
//
//
//      }
//   }
//    private static String[] fakeFileNames = new String[] { "coolsong.mid", "beethovens42ndSymphony.midi",
//         "hoegartenIsBad.mid", "howdyM8.midi", "testing1234.midi","filmScore.mid","piano_recording.mid",
//         "temp.mid","comicSansDanceSong.midi", "helloWorld.midi","asdf1234.mid","bestSongEvarr.mid"
//   };

}
