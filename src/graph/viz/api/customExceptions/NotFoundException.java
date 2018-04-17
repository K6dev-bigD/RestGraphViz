package graph.viz.api.customExceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NotFoundException extends WebApplicationException {
	
	public NotFoundException(String level ,String property) {
		
		super(Response.status(Response.Status.NOT_FOUND)
	             .entity("{\"lable\":\"Error\",\"message\":\""+level+" "+"\'"+property+"\' not found OR wrong "+level.toLowerCase()+"Id parameter provided.\"}").type(MediaType.TEXT_PLAIN).build());
	}

}
