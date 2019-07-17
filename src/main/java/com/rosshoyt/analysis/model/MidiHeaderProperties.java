package com.rosshoyt.analysis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
   @GeneratedValue
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
