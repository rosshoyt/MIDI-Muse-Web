package com.rosshoyt.analysis.midi_file_tools;

import com.rosshoyt.analysis.midi_file_tools.exceptions.InvalidFileException;
import com.rosshoyt.analysis.midi_file_tools.exceptions.InvalidMidiFileException;
import com.rosshoyt.analysis.midi_file_tools.kaitai.StandardMidiFile;
import com.rosshoyt.analysis.utils.FileExtensionValidator;
import com.rosshoyt.analysis.utils.FileUtils;
import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStream;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class MidiFileValidator {
   public static final String[] MIDI_FILE_EXTENSIONS_SUPPORTED =  { "mid", "midi", "smf"};
   private static FileExtensionValidator extensionValidator = new FileExtensionValidator(MIDI_FILE_EXTENSIONS_SUPPORTED);


   public ParseResult validate(File file) throws InvalidMidiFileException, IOException {
      System.out.println("...Validating file...");
      if(file != null && extensionValidator.extensionIsSupported(file)) {
         ParseResult parseResult = new ParseResult();
         parseResult.fileName = FileUtils.getFileNameWithoutExtension(file.getName());
         parseResult.extension = FileUtils.getExtension(file);
         return parse(parseResult, FileUtils.getByteArray(file));
      }
      else throw new InvalidMidiFileException();
   }
   public ParseResult validate(MultipartFile multipartFile) throws InvalidMidiFileException, IOException{
      System.out.println("...Validating multipart file...");
      if(multipartFile != null && extensionValidator.extensionIsSupported(multipartFile)) {
         ParseResult parseResult = new ParseResult();
         parseResult.fileName = FileUtils.getFileNameWithoutExtension(multipartFile.getName());
         parseResult.extension = multipartFile.getContentType();
         return parse(parseResult, multipartFile.getBytes());
      }
      else throw new InvalidMidiFileException();
   }
   private ParseResult parse(ParseResult parseResult, byte[] data) throws KaitaiStream.UnexpectedDataError {
      System.out.print("...Parsing SMF with Kaitai Struct...\n");
      // Kaitai Struct SMF parse
      parseResult.smf = new StandardMidiFile(new ByteBufferKaitaiStream(data));
      // Retain data for later use
      parseResult.data = data;
      return parseResult;
   }


}
