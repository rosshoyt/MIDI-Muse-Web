package com.rosshoyt.analysis;
import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.MidiFileAnalysisRepository;
import com.rosshoyt.analysis.model.Chord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;
@Component
class Initializer implements CommandLineRunner {

   private final MidiFileAnalysisRepository repository;

   public Initializer(MidiFileAnalysisRepository repository) {
      this.repository = repository;
   }

   @Override
   public void run(String... strings) {
      Stream.of("coolsong.mid", "beethoven420.midi", "hoegardensucks.mid",
            "howdym8.midi").forEach(name ->
            repository.save(new MidiFileAnalysis(name))
      );

      MidiFileAnalysis mfa = repository.findByFileName("coolsong.mid");
      Chord c = Chord.builder().chordName("C major")
            .realTimelength(Duration.ofSeconds(20))
            //.description("Reactive with Spring Boot + React")
            //.date(Instant.parse("2018-12-12T18:00:00.000Z"))
            .build();
      mfa.setChords(Collections.singleton(c));
      repository.save(mfa);

      repository.findAll().forEach(System.out::println);
   }
}
