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
 * Created by UGAM\kundan.kumar on 23/10/15.
 */
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

	private GraphDAO graphDAO ;
	private String divisionId;


	public CategoryResource(String divisionId) {

		System.out.println("[ CategoryResource ] inside constructor.");
		this.graphDAO=new GraphDAO();
		this.divisionId=divisionId;

	}


	@GET
	public Response getCategoriesApi(@Context UriInfo uriInfo,
			@QueryParam("from") @DefaultValue("0") int from,
			@QueryParam("stock") @DefaultValue("5") int stock){

		System.out.println("[ CategoryResource ] inside CategoryResource default.");
		System.out.println("div-uriInfo="+uriInfo.getAbsolutePath().toString());

		List<Node> listOfCategories=this.graphDAO.getNodeChildLevel("category",this.divisionId, from, stock);
		// convert your list to json
		Gson gson = new Gson();
		String data = gson.toJson(listOfCategories);

		return Response.ok(data).build();


	}

	@GET
	@Path("{categoryName}")
	public Response categoryValidation(@PathParam("categoryName") @DefaultValue("") String categoryName,
			@QueryParam("categoryId") @DefaultValue("") String categoryId) throws InvalidParameterException, JSONException{


		System.out.println("[ CategoryResource ] inside categories/categoryname="+categoryName);


		if(Utilities.emptyPropertyCheck(categoryId)){

			throw new InvalidParameterException("categoryId");
		}

		// division validation
		boolean catStatus=this.graphDAO.validateNode(categoryId, categoryName);
		// convert your property to json
		JsonObject dataset = new JsonObject();
		dataset.addProperty(categoryName, catStatus);
		Gson gson = new Gson();
		String data = gson.toJson(dataset);

		return Response.ok(data).build();

	}


	@GET
	@Path("{categoryName}/products")
	public Response getCategoryProducts(@PathParam("categoryName") String categoryName,
			@QueryParam("categoryId") @DefaultValue("") String categoryId,
			@QueryParam("from") @DefaultValue("0") int from,
			@QueryParam("stock") @DefaultValue("10") int stock
			) throws InvalidParameterException, JSONException {

		System.out.println("[ CategoryResource ] inside /category/products.");

		if(Utilities.emptyPropertyCheck(categoryId)){

			throw new InvalidParameterException("categoryId");

		}else{
			// retailer validation
			boolean status=this.graphDAO.validateNode(categoryId, categoryName);

			if(status==false){
				
				throw new NotFoundException("Category",categoryName);  

			}
		}

		List<Node> listOfProducts=this.graphDAO.getNodeProducts("category",categoryId, from, stock);
		// convert your list to json
		Gson gson = new Gson();
		String data = gson.toJson(listOfProducts);

		return Response.ok(data).build();

	}

	
	@Path("{categoryName}/departments")
	public DepartmentResource getDepartmentsApi(@PathParam("categoryName") @DefaultValue("") String categoryName,
			@QueryParam("categoryId") @DefaultValue("") String categoryId) throws InvalidParameterException, JSONException {

		System.out.println("[ CategoryResource ] inside /departments redirect.");

		if(Utilities.emptyPropertyCheck(categoryId)){

			throw new InvalidParameterException("categoryId");

		}else{

			// Category validation
			boolean status=this.graphDAO.validateNode(categoryId, categoryName);

			if(status==false){

				throw new NotFoundException("Category",categoryName);  

			}
		}
		return new DepartmentResource(categoryId);
	}
}
