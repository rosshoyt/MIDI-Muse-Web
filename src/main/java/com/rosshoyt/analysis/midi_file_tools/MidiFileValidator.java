package com.rosshoyt.analysis.midi_file_tools;

import com.rosshoyt.analysis.midi_file_tools.exceptions.InvalidFileException;
import com.rosshoyt.analysis.midi_file_tools.exceptions.InvalidMidiFileException;
import com.rosshoyt.analysis.midi_file_tools.exceptions.UnexpectedMidiDataException;
import com.rosshoyt.analysis.midi_file_tools.kaitai.KaitaiSMFParser;
import com.rosshoyt.analysis.midi_file_tools.kaitai.StandardMidiFile;
import com.rosshoyt.analysis.utils.FileExtensionValidator;
import com.rosshoyt.analysis.utils.FileUtils;
import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStream;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class MidiFileValidator {

   public ParseResult validate(File file) throws  IOException, InvalidMidiFileException, UnexpectedMidiDataException {
      System.out.println("...Validating file...");
      if(file != null && extensionValidator.extensionIsSupported(file)) {
         ParseResult parseResult = parser.parse(FileUtils.getByteArray(file));
         parseResult.fileName = FileUtils.getFileNameWithoutExtension(file.getName());
         parseResult.extension = FileUtils.getExtension(file);
         return parseResult;
      }
      else throw new InvalidMidiFileException();

   }
   public ParseResult validate(MultipartFile multipartFile) throws IOException, InvalidMidiFileException, UnexpectedMidiDataException {
      System.out.println("...Validating multipart file...");
      if(multipartFile != null && extensionValidator.extensionIsSupported(multipartFile)) {
         ParseResult parseResult = parser.parse(multipartFile.getBytes());
         parseResult.fileName = FileUtils.getFileNameWithoutExtension(multipartFile.getName());
         parseResult.extension = multipartFile.getContentType();
         return parseResult;
      }
      else throw new InvalidMidiFileException();
   }
   // Utils
   public static final String[] MIDI_FILE_EXTENSIONS_SUPPORTED =  { "mid", "midi", "smf"};
   private static FileExtensionValidator extensionValidator = new FileExtensionValidator(MIDI_FILE_EXTENSIONS_SUPPORTED);
   private static StandardMidiFileParser parser = new KaitaiSMFParser();
}
