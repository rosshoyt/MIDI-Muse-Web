package com.rosshoyt.analysis.model.raw;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NoteOn extends MidiEvent {

   private int note;
   private int velocity;








}
