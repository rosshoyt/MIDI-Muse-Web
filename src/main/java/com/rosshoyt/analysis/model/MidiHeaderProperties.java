package com.rosshoyt.analysis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Class which represents a standard MIDI file header.
 *
 */
@Data
@ToString
@NoArgsConstructor
@Entity
public class MidiHeaderProperties {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private long id;

   private int midiFileType;
   private String comment;
   private String title;
   private String author;
   private Date date;
   private String copyrightInfo;

//
//   @Override
//   public String toString(){
//      StringBuilder sb;
//      return "MidiHeader Info-"
//            + "\nTitle: " + title
//            + "\nAuthor: " + author
//            + "\nDate: " + date
//            + "\nCopyright Info: " + copyrightInfo
//            + "\nComment: " + comment;
//   }


}
