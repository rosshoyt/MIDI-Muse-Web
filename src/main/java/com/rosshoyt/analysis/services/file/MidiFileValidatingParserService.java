package com.rosshoyt.analysis.services.file;

import com.rosshoyt.analysis.exceptions.InvalidMidiFileDataException;
import com.rosshoyt.analysis.exceptions.UnexpectedMidiDataException;
import com.rosshoyt.analysis.model.internal.ValidParseResultContainer;
import com.rosshoyt.analysis.tools.midifile.parsing.StandardMidiFileParser;
import com.rosshoyt.analysis.tools.midifile.parsing.kaitai.KaitaiSMFParser;
import com.rosshoyt.analysis.tools.utils.CustomFileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class MidiFileValidatingParserService {

   public static ValidParseResultContainer validateAndParse(File file) throws  IOException, InvalidMidiFileDataException, UnexpectedMidiDataException {
      System.out.println("...Validating file...");
      if(extensionValidator.extensionIsSupported(file)) {
         ValidParseResultContainer parseResult = parser.parse(CustomFileUtils.getByteArray(file));
         parseResult.fileName = CustomFileUtils.getFileNameWithoutExtension(file.getName());
         parseResult.extension = CustomFileUtils.getExtension(file);
         return parseResult;
      }
      else throw new InvalidMidiFileDataException();
   }

   public static ValidParseResultContainer validateAndParse(MultipartFile multipartFile) throws IOException, UnexpectedMidiDataException, InvalidMidiFileDataException {
      System.out.println("...Validating multipart file...");
      if(extensionValidator.extensionIsSupported(multipartFile)) {
         ValidParseResultContainer parseResult = parser.parse(multipartFile.getBytes());
         parseResult.fileName = CustomFileUtils.getFileNameWithoutExtension(multipartFile.getName());
         parseResult.extension = multipartFile.getContentType();
         return parseResult;
      }
      else throw new InvalidMidiFileDataException();
   }
   // Utils
   public static final String[] MIDI_FILE_EXTENSIONS_SUPPORTED =  { "mid", "midi", "smf"};
   private static FileExtensionValidatorService extensionValidator = new FileExtensionValidatorService(MIDI_FILE_EXTENSIONS_SUPPORTED);
   private static StandardMidiFileParser parser = new KaitaiSMFParser();
}
