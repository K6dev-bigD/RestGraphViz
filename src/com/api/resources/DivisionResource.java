package com.api.resources;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONException;

import com.api.dao.GraphDAO;
import com.api.utils.Utilities;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import graph.viz.api.customExceptions.InvalidParameterException;
import graph.viz.api.customExceptions.NotFoundException;
import graph.viz.pojo.Node;

/**
 * Created by UGAM\kishore.damodara on 23/10/15.
 */
@Produces(MediaType.APPLICATION_JSON)
public class DivisionResource {

	private GraphDAO graphDAO ;
	private String retailerId;


	public DivisionResource(String retailerId) {

		System.out.println("[ DivisionResource ] inside constructor.");
		this.graphDAO=new GraphDAO();
		this.retailerId=retailerId;

	}

	// get list of Divisions for a retailer [if retailer is present]
	@GET
	public Response getDivisionsApi(@QueryParam("from") @DefaultValue("0") int from,
			@QueryParam("stock") @DefaultValue("5") int stock){

		System.out.println("[ DivisionResource ] inside DivisionResource default.");

		// pass id and get nodes outgoing to it 
		List<Node> listOfDivisions=this.graphDAO.getNodeChildLevel("divisions",this.retailerId, from, stock);
		// convert your list to json
		Gson gson = new Gson();
		String data = gson.toJson(listOfDivisions);

		return Response.ok(data).build();


	}

	@GET
	@Path("{divisionName}")
	public Response divisionValidation(@PathParam("divisionName") @DefaultValue("") String divisionName,
			@QueryParam("divisionId") @DefaultValue("") String divisionId) throws InvalidParameterException, JSONException{


		System.out.println("[ DivisionResource ] inside /division/divisionname="+divisionName);

		if(Utilities.emptyPropertyCheck(divisionId)){

			throw new InvalidParameterException("divisionId");
		}
		// division validation
		boolean divStatus=this.graphDAO.validateNode(divisionId, divisionName);
		// convert your property to json
		JsonObject dataset = new JsonObject();
		dataset.addProperty(divisionName, divStatus);
		Gson gson = new Gson();
		String data = gson.toJson(dataset);

		return Response.ok(data).build();


	}


	@GET
	@Path("{divisionName}/products")
	public Response getDivisionProductsApi(@PathParam("divisionName") String divisionName,
			@QueryParam("divisionId") @DefaultValue("") String divisionId,
			@QueryParam("from") @DefaultValue("0") int from,
			@QueryParam("stock") @DefaultValue("10") int stock
			) throws InvalidParameterException, JSONException{

		System.out.println("[ DivisionResource ] inside /division/products.");

		if(Utilities.emptyPropertyCheck(divisionId)){

			throw new InvalidParameterException("divisionId");

		}else{
			// retailer validation
			boolean Status=this.graphDAO.validateNode(divisionId, divisionName);

			if(Status==false){

				throw new NotFoundException("Division",divisionName);  
			}
		}
		List<Node> listOfProducts=this.graphDAO.getNodeProducts("division",divisionId, from, stock);
		// convert your list to json
		Gson gson = new Gson();
		String data = gson.toJson(listOfProducts);

		return Response.ok(data).build();

	}


	@Path("{divisionName}/categories")
	public CategoryResource getCategoriesApi(@PathParam("divisionName") String divisionName,
			@QueryParam("divisionId") @DefaultValue("") String divisionId) throws InvalidParameterException, JSONException {

		System.out.println("[ DivisionResource ] inside /categories redirect.");

		if(Utilities.emptyPropertyCheck(divisionId)){

			throw new InvalidParameterException("divisionId");

		}else{
			// divisiom validation
			boolean Status=this.graphDAO.validateNode(divisionId, divisionName);

			if(Status==false){

				throw new NotFoundException("Division",divisionName);  
			}
		}

		return new CategoryResource(divisionId);
	}

}
