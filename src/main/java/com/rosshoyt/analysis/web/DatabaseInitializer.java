package com.rosshoyt.analysis.web;

import com.rosshoyt.analysis.services.file.MidiFileValidatingParserService;
import com.rosshoyt.analysis.services.MidiFileAnalysisService;
import com.rosshoyt.analysis.tools.utils.CustomFileUtils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;


import java.io.File;
import java.util.*;

@Component
@EntityScan("com.rosshoyt.analysis")
class DatabaseInitializer implements CommandLineRunner {

   // Utilites to pre-load 'Example' MIDI files into database
   private static final String PRELOADED_MIDI_FILES_DIR = "preloaded-midi-files";
   private static CustomFileUtils.LocalDirectoryScanner directoryScanner = new CustomFileUtils.LocalDirectoryScanner(
         PRELOADED_MIDI_FILES_DIR, MidiFileValidatingParserService.MIDI_FILE_EXTENSIONS_SUPPORTED);


   private final MidiFileAnalysisService midiFileAnalysisService;

   public DatabaseInitializer(MidiFileAnalysisService midiFileAnalysisService) {
      this.midiFileAnalysisService = midiFileAnalysisService;
   }

//
//   public Initializer(MidiFileAnalysisService midiFileAnalysisService) {
//      this.midiFileAnalysisService = midiFileAnalysisService;
//
//   }

   @Override
   public void run(String... strings) {
      fillMFAR();
   }

   private void fillMFAR() {
      List<File> preloadMidiFiles = directoryScanner.getFiles();

      for(File file: preloadMidiFiles) {
         System.out.println("Analyzing pre-loaded midi file " + file.getName());
         try {

           midiFileAnalysisService.addMidiFile(file);


         } catch (Exception e) {
            e.printStackTrace();
         }

      }
      System.out.println("All entries added to repo, here they are ->");
      midiFileAnalysisService.getAllMidiFileAnalyses().forEach(System.out::println);
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
