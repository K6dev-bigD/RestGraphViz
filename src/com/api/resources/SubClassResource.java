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
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import graph.viz.api.customExceptions.InvalidParameterException;
import graph.viz.api.customExceptions.NotFoundException;
import graph.viz.pojo.Node;
import jersey.repackaged.com.google.common.base.Ticker;

/**
 * Created by UGAM\kishore.damodara on 23/10/15.
 */
@Produces(MediaType.APPLICATION_JSON)
public class SubClassResource {
	
	private GraphDAO graphDAO ;
	private String classId;
	
	public SubClassResource(String classId) {

		System.out.println("[ SubClassResource ] inside constructor.");
		this.graphDAO=new GraphDAO();
		this.classId=classId;

	}
	
    @GET
    public Response getSubClasses(@Context UriInfo uriInfo,
			@QueryParam("from") @DefaultValue("0") int from,
			@QueryParam("stock") @DefaultValue("5")int stock){
    	
    	System.out.println("[ SubClassResource ] inside SubClassResource default.");

		List<Node> listOfSubclasses=this.graphDAO.getNodeChildLevel("subClass",this.classId, from, stock);
		// convert your list to json
		Gson gson = new Gson();
		String data = gson.toJson(listOfSubclasses);

		return Response.ok(data).build();
    }
    
    
    @GET
	@Path("{subclassName}")
	public Response subclassValidation(@PathParam("subclassName") String subclassName,
			@QueryParam("subclassId") @DefaultValue("") String subclassId) throws InvalidParameterException, JSONException{

		System.out.println("[ SubClassResource ] inside /subclasses/subclassname="+subclassName);

		if(Utilities.emptyPropertyCheck(subclassId)){

			throw new InvalidParameterException("subclassId");
		}

		// subclass validation
		boolean subclassStatus=this.graphDAO.validateNode(subclassId, subclassName);
		// convert your property to json
		JsonObject dataset = new JsonObject();
		dataset.addProperty(subclassName, subclassStatus);
		Gson gson = new Gson();
		String data = gson.toJson(dataset);

		return Response.ok(data).build();

	}
    
    
    @GET
	@Path("{subclassName}/products")
	public Response getcategoryProducts(@PathParam("subclassName") @DefaultValue("") String subclassName,
			@QueryParam("subclassId") @DefaultValue("") String subclassId,
			@QueryParam("from") @DefaultValue("0") int from,
			@QueryParam("stock") @DefaultValue("10") int stock
			) throws InvalidParameterException, JSONException {

		System.out.println("[ SubClassResource ] inside /subclasses/products.");

		if(Utilities.emptyPropertyCheck(subclassId)){

			throw new InvalidParameterException("subclassId");

		}else{
			// Subclass validation
			boolean status=this.graphDAO.validateNode(subclassId, subclassName);

			if(status==false){
				
				throw new NotFoundException("SubClass",subclassName);  

			}
		}

		List<Node> listOfProducts=this.graphDAO.getNodeProducts("subClass",subclassId, from, stock);
		// convert your list to json
		Gson gson = new Gson();
		String data = gson.toJson(listOfProducts);

		return Response.ok(data).build();

	}
    
    
    
}
