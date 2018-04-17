package graph.viz.api.customExceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;


public class InvalidParameterException extends WebApplicationException {
	
	public InvalidParameterException(String property) throws JSONException {
		
		super(Response.status(Response.Status.BAD_REQUEST)
	             .entity("{\"lable\":\"Error\",\"message\":\"query parameter \'"+property+"\' not set OR is set as empty\"}").type(MediaType.APPLICATION_JSON).build());
		
	
	}


}
