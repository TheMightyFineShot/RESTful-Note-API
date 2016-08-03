package com.note.REST.Interface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.note.models.Note;
import com.note.models.NoteHolder;
 
/*
 * Note REST Service
 */
@Path("/")
public class NoteRESTService {
	
	/*
	 * POST, receives a JSON body and sends it to the Note Manager to be added
	 */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response addNote(InputStream stream) throws FileNotFoundException{
		StringBuilder sBuilder = new StringBuilder();
		Gson gson = new Gson();
		Note n = new Note();
		int statusToReturn = 200;
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(stream));
			String line = null;
			while ((line = in.readLine()) != null) {
				sBuilder.append(line);
			}
			n.setBody(sBuilder.toString());
	        NoteHolder.noteManager.addNote(n);
		} catch (JsonSyntaxException | IOException e) {
			statusToReturn = 415;
			return Response.status(statusToReturn).entity(gson.toJson(n)).build();
		}

		return Response.status(statusToReturn).entity(gson.toJson(n)).build();
	}
 
	/*
	 * GET, Returns in JSON format the requested Note
	 */
	@GET
	@Path("{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNoteByID(@PathParam("ID") String id) {
		int statusToReturn = 200;
		int idRecieved = Integer.parseInt(id);
		Gson gson = new Gson();
		Note n = NoteHolder.noteManager.getNoteByID(idRecieved);
		
		if(n==null){
			statusToReturn = 404;
		}
		
		return Response.status(statusToReturn).entity(gson.toJson(n)).build();
	}
	
	/*
	 * GET, Returns all notes or if parameter query is specified , notes whos body contains the provided Parameter
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllNotesWithOptionalParam(@QueryParam("query") String query) {
		int statusToReturn = 200;
		Gson gson = new Gson();
		String response = null;
		
		if(NoteHolder.noteManager.getAllNotes()==null){
			statusToReturn = 404;
			return Response.status(statusToReturn).entity("Currently no notes!").build();
		}
		
		if(query != null){
			response = gson.toJson(NoteHolder.noteManager.getAllNotesWithParam(query));
		}
		else{
			response = gson.toJson(NoteHolder.noteManager.getAllNotes());
		}
		
		return Response.status(statusToReturn).entity(response).build();
	}
}
