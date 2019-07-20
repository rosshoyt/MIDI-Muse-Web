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
   public static FileExtensionValidator extensionValidator = new FileExtensionValidator(MIDI_FILE_EXTENSIONS_SUPPORTED);


   public StandardMidiFile validate(File file) throws InvalidMidiFileException, IOException {
      if(file != null && extensionValidator.extensionIsSupported(file))
         return validate(FileUtils.getByteArray(file));
      else throw new InvalidMidiFileException();
   }
   public StandardMidiFile validate(MultipartFile multipartFile) throws InvalidMidiFileException, IOException{
      if(multipartFile != null && extensionValidator.extensionIsSupported(multipartFile))
         return validate(FileUtils.getByteArray(multipartFile));
      else throw new InvalidMidiFileException();
   }
   public StandardMidiFile validate(byte[] data) throws KaitaiStream.UnexpectedDataError {
      System.out.print("Parsing file with Kaitai Struct\n");
      return new StandardMidiFile(new ByteBufferKaitaiStream(data));
   }


}
