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
public class DepartmentResource {

	private GraphDAO graphDAO ;
	private String categoryId;


	public DepartmentResource(String categoryId) {

		System.out.println("[ DepartmentResource ] inside constructor.");
		this.graphDAO=new GraphDAO();
		this.categoryId=categoryId;

	}

	@GET
	public Response getDepartments(@Context UriInfo uriInfo,
			@QueryParam("from") @DefaultValue("0") int from,
			@QueryParam("stock") @DefaultValue("5") int stock){

		System.out.println("[ DepartmentResource ] inside DepartmentResource default.");
		System.out.println("div-uriInfo="+uriInfo.getAbsolutePath().toString());

		List<Node> listOfDept=this.graphDAO.getNodeChildLevel("department",this.categoryId, from, stock);
		// convert your list to json
		Gson gson = new Gson();
		String data = gson.toJson(listOfDept);

		return Response.ok(data).build();

	}

	@GET
	@Path("{departmentName}")
	public Response depaartmentValidation(@PathParam("departmentName") @DefaultValue("") String departmentName,
			@QueryParam("departmentId") @DefaultValue("") String departmentId) throws InvalidParameterException, JSONException{

		System.out.println("[ DepartmentResource ] inside /departments/departmentname="+departmentName);

		if(Utilities.emptyPropertyCheck(departmentId)){

			throw new InvalidParameterException("departmentId");
		}

		// division validation
		boolean deptStatus=this.graphDAO.validateNode(departmentId, departmentName);
		// convert your property to json
		JsonObject dataset = new JsonObject();
		dataset.addProperty(departmentName, deptStatus);
		Gson gson = new Gson();
		String data = gson.toJson(dataset);

		return Response.ok(data).build();

	}
	
	
	@GET
	@Path("{departmentName}/products")
	public Response getcategoryProducts(@PathParam("departmentName") @DefaultValue("") String departmentName,
			@QueryParam("departmentId") @DefaultValue("") String departmentId,
			@QueryParam("from") @DefaultValue("0") int from,
			@QueryParam("stock") @DefaultValue("10") int stock
			) throws InvalidParameterException, JSONException {

		System.out.println("[ DepartmentResource ] inside /department/products.");

		if(Utilities.emptyPropertyCheck(departmentId)){

			throw new InvalidParameterException("departmentId");

		}else{
			// retailer validation
			boolean rStatus=this.graphDAO.validateNode(departmentId, departmentName);

			if(rStatus==false){
				
				throw new NotFoundException("Department",departmentName);  

			}
		}

		List<Node> listOfProducts=this.graphDAO.getNodeProducts("department",departmentId, from, stock);
		// convert your list to json
		Gson gson = new Gson();
		String data = gson.toJson(listOfProducts);

		return Response.ok(data).build();

	}
	
	
	@Path("{departmentName}/classes")
	public ClassResource getClassesApi(@PathParam("departmentName") @DefaultValue("") String departmentName,
			@QueryParam("departmentId")  @DefaultValue("") String departmentId) throws InvalidParameterException, JSONException {

		System.out.println("[ DepartmentResource ] inside /classes redirect.");

		if(Utilities.emptyPropertyCheck(departmentId)){

			throw new InvalidParameterException("departmentId");

		}else{

			// retailer validation
			boolean deptStatus=this.graphDAO.validateNode(departmentId, departmentName);

			if(deptStatus==false){

				throw new NotFoundException("Department",departmentName);  
			}
		}

		return new ClassResource(departmentId);
	}
}
