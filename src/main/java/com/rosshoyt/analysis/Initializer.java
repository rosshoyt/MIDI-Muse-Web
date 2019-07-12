package com.rosshoyt.analysis;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.MidiFileAnalysisRepository;
import com.rosshoyt.analysis.model.Chord;
import com.rosshoyt.analysis.utils.LocalDirectoryScanner;
import com.rosshoyt.analysis.web.FileUploadController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

   private static String[] fakeFileNames = new String[] { "coolsong.mid", "beethovens42ndSymphony.midi",
         "hoegartenIsBad.mid", "howdyM8.midi", "testing1234.midi","filmScore.mid","piano_recording.mid",
         "temp.mid","comicSansDanceSong.midi", "helloWorld.midi","asdf1234.mid","bestSongEvarr.mid"
   };

   private static final String PRELOADED_MIDI_FILES_DIR = "preloaded-midi-files";



   private static LocalDirectoryScanner directoryScanner = new LocalDirectoryScanner(
         PRELOADED_MIDI_FILES_DIR, FileUploadController.MIDI_FILE_EXTENSIONS_SUPPORTED);

   private final MidiFileAnalysisRepository repository;

   public Initializer(MidiFileAnalysisRepository repository) {
      this.repository = repository;
   }

   @Override
   public void run(String... strings) {
      fillMFAR();
   }

   private void fillMFAR() {
      List<File> preloadMIDIFIles = directoryScanner.getFiles();
      for(File file: preloadMIDIFIles)



/*      Stream.of(fakeFileNames).forEach(name ->
            repository.save(new MidiFileAnalysis(name))
      );

      MidiFileAnalysis mfa = repository.findByFileName("coolsong.mid");
      ArrayList<Chord> chords = new ArrayList<>();


      chords.add(Chord.builder().chordName("C major").realTimelength(Duration.ofSeconds(20)).build());
      chords.add(Chord.builder().chordName("F major").realTimelength(Duration.ofSeconds(10)).build());
      chords.add(Chord.builder().chordName("Ab minor").realTimelength(Duration.ofSeconds(10)).build());
      mfa.setChords(chords);

      repository.save(mfa);*/


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
}
