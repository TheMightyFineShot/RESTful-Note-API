package com.note.models;

import java.util.ArrayList;

import com.note.controller.NoteHolderManipulator;

/*
 * Model that holds all notes
 */
public class NoteHolder {
	
	private static ArrayList<Note> notes = new ArrayList<Note>();
	
	public static NoteHolderManipulator noteManager = new NoteHolderManipulator(notes);

	public ArrayList<Note> getNotes() {
		return notes;
	}
	public NoteHolderManipulator getNoteManager() {
		return noteManager;
	}
}
