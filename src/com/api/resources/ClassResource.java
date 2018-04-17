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

/**
 * Created by UGAM\kishore.damodara on 23/10/15.
 */
@Produces(MediaType.APPLICATION_JSON)
public class ClassResource {
	
	private GraphDAO graphDAO ;
	private String departmentId;
	
	public ClassResource(String departmentId) {

		System.out.println("[ ClassResource ] inside constructor.");
		this.graphDAO=new GraphDAO();
		this.departmentId=departmentId;

	}
	
    @GET
    public Response getClassesApi(@Context UriInfo uriInfo,
			@QueryParam("from") @DefaultValue("0") int from,
			@QueryParam("stock") @DefaultValue("5") int stock){
    	
    	System.out.println("[ ClassResource ] inside ClassResource default.");

		List<Node> listOfClasses=this.graphDAO.getNodeChildLevel("class",this.departmentId, from, stock);
		// convert your list to json
		Gson gson = new Gson();
		String data = gson.toJson(listOfClasses);

		return Response.ok(data).build();
    }
    
    @GET
	@Path("{className}")
	public Response classValidation(@PathParam("className") @DefaultValue("") String className,
			@QueryParam("classId") @DefaultValue("") String classId) throws InvalidParameterException, JSONException{

		System.out.println("[ ClassResource ] inside /classes/classname="+className);

		if(Utilities.emptyPropertyCheck(classId)){

			throw new InvalidParameterException("classId");
		}

		// division validation
		boolean classStatus=this.graphDAO.validateNode(classId, className);
		// convert your property to json
		JsonObject dataset = new JsonObject();
		dataset.addProperty(className, classStatus);
		Gson gson = new Gson();
		String data = gson.toJson(dataset);

		return Response.ok(data).build();

	}
    

	@GET
	@Path("{className}/products")
	public Response getcategoryProducts(@PathParam("className") @DefaultValue("") String className,
			@QueryParam("classId") @DefaultValue("") String classId,
			@QueryParam("from") @DefaultValue("0") int from,
			@QueryParam("stock") @DefaultValue("10") int stock
			) throws InvalidParameterException, JSONException {

		System.out.println("[ ClassResource ] inside /classes/products.");

		if(Utilities.emptyPropertyCheck(classId)){

			throw new InvalidParameterException("classId");

		}else{
			// class validation
			boolean status=this.graphDAO.validateNode(classId, className);

			if(status==false){
				
				throw new NotFoundException("Class",className);  

			}
		}

		List<Node> listOfProducts=this.graphDAO.getNodeProducts("class",classId, from, stock);
		// convert your list to json
		Gson gson = new Gson();
		String data = gson.toJson(listOfProducts);

		return Response.ok(data).build();

	}
    
    

    @Path("{className}/subClasses")
    public SubClassResource getSubClassesApi(@PathParam("className") String className,
    		@QueryParam("classId") @DefaultValue("") String classId) throws InvalidParameterException, JSONException {
    	
    	System.out.println("[ ClassResource ] inside /subclasses redirect.");

		if(Utilities.emptyPropertyCheck(classId)){

			throw new InvalidParameterException("classId");

		}else{

			// retailer validation
			boolean status=this.graphDAO.validateNode(classId, className);

			if(status==false){

				throw new NotFoundException("Class",className);  

			}
		}

        return new SubClassResource(classId);
    }
}
