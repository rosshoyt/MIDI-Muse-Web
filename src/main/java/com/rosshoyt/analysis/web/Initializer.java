package com.rosshoyt.analysis.web;

import com.rosshoyt.analysis.midi_file_tools.MidiFileAnalyzer;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.repositories.MidiFileAnalysisRepository;
import com.rosshoyt.analysis.utils.FileExtensionValidator;
import com.rosshoyt.analysis.utils.LocalDirectoryScanner;
import com.rosshoyt.analysis.utils.MidiFileUtils;

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
         PRELOADED_MIDI_FILES_DIR, MidiFileUtils.MIDI_FILE_EXTENSIONS_SUPPORTED);

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

            MidiFileAnalysis midiFileAnalysis = midiFileAnalyzer.analyze(file);
            System.out.println(midiFileAnalysis);
            repository.save(midiFileAnalysis);
         } catch (Exception e) {
            e.printStackTrace();
         }
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



      repository.findAll().forEach(System.out::println);
   }
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
