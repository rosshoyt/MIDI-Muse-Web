package com.rosshoyt.analysis.midi_file_tools;

import com.rosshoyt.analysis.midi_file_tools.kaitai.StandardMidiFile;
import com.rosshoyt.analysis.utils.MidiFileUtils;

import org.springframework.web.multipart.MultipartFile;
import io.kaitai.struct.ByteBufferKaitaiStream;

import java.io.IOException;
import java.io.File;

public class KaitaiSMFParser {
    public StandardMidiFile parse(MultipartFile multipartFile) throws IOException {
        return parse(multipartFile.getBytes());
     }
     public StandardMidiFile parse(File file) throws IOException {
        return parse(MidiFileUtils.fileToByteArray(file));
     }
     public StandardMidiFile parse(byte[] rawFile) {
        return new StandardMidiFile(new ByteBufferKaitaiStream(rawFile));
     }
}
