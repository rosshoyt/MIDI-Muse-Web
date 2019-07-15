package com.rosshoyt.analysis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Class which represents a standard MIDI file header.
 *
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "midifile_header")
public class MidiHeaderProperties {

   private int midiFileType;
   private String comment;
   private String title;
   private String author;
   private Date date;
   private String copyrightInfo;


   @Override
   public String toString(){
      StringBuilder sb;
      return "MidiHeader Info-"
            + "\nTitle: " + title
            + "\nAuthor: " + author
            + "\nDate: " + date
            + "\nCopyright Info: " + copyrightInfo
            + "\nComment: " + comment;
   }


}
