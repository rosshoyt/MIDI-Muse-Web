package com.rosshoyt.analysis.model.kaitai.smf;

// This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild

import io.kaitai.struct.KaitaiStruct;

import javax.persistence.*;
import java.util.ArrayList;


/**
 * Standard MIDI file, typically knows just as "MID", is a standard way
 * to serialize series of MIDI events, which is a protocol used in many
 * music synthesizers to transfer music data: notes being played,
 * effects being applied, etc.
 *
 * Internally, file consists of a header and series of tracks, every
 * track listing MIDI events with certain header designating time these
 * events are happening.
 *
 * NOTE: Rarely, MIDI files employ certain stateful compression scheme
 * to avoid storing certain elements of further elements, instead
 * reusing them from events which happened earlier in the
 * stream. Kaitai Struct (as of v0.9) is currently unable to parse
 * these, but files employing this mechanism are relatively rare.
 */

@Entity
public class RawAnalysis {
   @OneToOne()
   private Header hdr;
   @OneToMany(fetch = FetchType.LAZY)
   private ArrayList<Track> tracks;
   
   
   @Id
   @GeneratedValue(strategy =  GenerationType.SEQUENCE)
   private Long _rootID;
   
   
   //private KaitaiStruct _parent;
  
}