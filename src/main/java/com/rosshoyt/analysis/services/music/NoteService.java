package com.rosshoyt.analysis.services.music;

import com.rosshoyt.analysis.model.MidiFileAnalysis;
import com.rosshoyt.analysis.model.musical.Note;
import com.rosshoyt.analysis.model.raw.RawAnalysis;
import com.rosshoyt.analysis.repositories.music.NoteRepository;
import com.rosshoyt.analysis.tools.musicdata.extraction.NoteEventStitcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NoteService {
   private final NoteRepository noteRepository;

   public NoteService(NoteRepository noteRepository) {
      this.noteRepository = noteRepository;
   }
   public Iterable<Note> addNoteList(RawAnalysis rawAnalysis, MidiFileAnalysis midiFileAnalysis) {
      SortedSet<Note> notes = new TreeSet<>();
      if (!rawAnalysis.getNoteOnEvents().isEmpty()) {
         System.out.println("Creating NoteList from RawAnalysis noteOn and noteOff sets");
         System.out.println("Raw Analysis NoteOn and Note off lists that will be analyzed musically:");
         //rawAnalysis.getNoteOnEvents().forEach(System.out::println);
         //rawAnalysis.getNoteOffEvents().forEach(System.out::println);

         notes = NoteEventStitcher.stitchNotes(rawAnalysis.getHeader().getNumTracks(), rawAnalysis.getRawEventCounts(),
               rawAnalysis.getNoteOnEvents(), rawAnalysis.getNoteOffEvents());
      }
      System.out.printf("---NOTE STITCH RESULTS---\nCreated %d notes from list of %d noteOn and %d noteOffs\n",
            notes.size(), rawAnalysis.getRawEventCounts().getNumNoteOnEvents(),rawAnalysis.getRawEventCounts().getNumNoteOffEvents() );
      System.out.println("Notes Stitched: ");
      notes.forEach(System.out::println);
      return noteRepository.saveAll(notes);
   }
}
