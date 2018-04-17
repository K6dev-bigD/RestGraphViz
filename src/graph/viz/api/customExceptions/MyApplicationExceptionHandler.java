package graph.viz.api.customExceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Provider
public class MyApplicationExceptionHandler implements ExceptionMapper<InvalidParameterException>{

	@Override
	public Response toResponse(InvalidParameterException exception) {

		JsonObject dataset = new JsonObject();
		Gson gson = new Gson();
		dataset.addProperty("lable","error");
		dataset.addProperty("message",exception.getMessage()+" not set as query parameter or is empty.");
		String data = gson.toJson(dataset);
		
		return Response.status(Status.BAD_REQUEST).entity(data).build();
	}


}
