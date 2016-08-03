package com.note.controller;

import java.util.ArrayList;

import com.note.models.Note;

/*
 * This class is used to manipulate the Note Holder
 */
public class NoteHolderManipulator {
	
	private ArrayList<Note> notes = null;
	//Used to provide unique ID's
	private int curID = 0;
	
	//Constructor
	public NoteHolderManipulator(ArrayList<Note> noteListToManage){
		notes = noteListToManage;
	}
	
	/*
	 * Returns the note that is associated with the provided ID
	 */
	public Note getNoteByID(int id){
		
		Note theFoundNote = null;
		
		for(Note n : notes){
			
			 if(n.getId() == id){
				 theFoundNote = n;
			 }
		
		}
		
		return theFoundNote;
	}
	
	/*
	 * Returns all notes that contain a specied string in their body
	 */
	public ArrayList<Note> getAllNotesWithParam(String param){
		ArrayList<Note> tempNotes = new ArrayList<Note>();
		
		for(Note n : notes){
			
			 if(n.getBody().contains(param)){
				 tempNotes.add(n);
			 }
		
		}
		
		return tempNotes;
		
	}
	
	/*
	 * returns all notes
	 */
	public ArrayList<Note> getAllNotes(){
		return notes;
	}
	
	/*
	 * adds note to the Note Holder
	 */
	public void addNote(Note noteToAdd){
		curID++;
		noteToAdd.setId(curID);
		notes.add(noteToAdd);
	}

	
}
