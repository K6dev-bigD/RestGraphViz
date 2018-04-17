package com.api.resources;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

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
public class RetailerResource {

	private GraphDAO graphDAO ;

	public RetailerResource() {

		System.out.println("[ RetailerResource ] inside constructor.");
		this.graphDAO=new GraphDAO();

	}

	// to get list of retailers
	@GET
	public Response getRetailersApi(@QueryParam("from") @DefaultValue("0") int from,
			@QueryParam("stock") @DefaultValue("5") int stock){

		System.out.println("[ RetailerResource ] inside default get.");

		List<String> listOfRetailer=this.graphDAO.getRetailers("retailer",from,stock);
		// convert your list to json
		Gson gson = new Gson();
		String data = gson.toJson(listOfRetailer);

		return Response.ok(data).build();
	}


	@GET
	@Path("{retailerName}")
	public Response retailerValidationApi(@PathParam("retailerName") @DefaultValue("") String retailerName,
			@QueryParam("retailerId") @DefaultValue("") String retailerId) throws JSONException{

		System.out.println("[ RetailerResource ] inside /retailer/retailername="+retailerName);

		if(Utilities.emptyPropertyCheck(retailerId)){

			throw new InvalidParameterException("retailerId");
		}

		boolean status=this.graphDAO.validateNode(retailerId, retailerName);
		// convert your property to json
		JsonObject dataset = new JsonObject();
		Gson gson = new Gson();
		dataset.addProperty(retailerName, status);
		String data = gson.toJson(dataset);

		return Response.ok(data).build();

	}

	@GET
	@Path("{retailerName}/products")
	public Response getRetailerProductsApi(@PathParam("retailerName") String retailerName,
			@QueryParam("retailerId") @DefaultValue("") String retailerId,
			@QueryParam("from") @DefaultValue("0") int from,
			@QueryParam("stock") @DefaultValue("10") int stock
			) throws JSONException{

		System.out.println("[ RetailerResource ] inside /retailer/products.");

		if(Utilities.emptyPropertyCheck(retailerId)){

			throw new InvalidParameterException("retailerId");

		}else{
			// retailer validation
			boolean Status=this.graphDAO.validateNode(retailerId, retailerName);

			if(Status==false){
				
				throw new NotFoundException("Retailer",retailerName);  

			}
		}

		List<Node> listOfProducts=this.graphDAO.getNodeProducts("retailer",retailerId, from,stock);
		
		String datajson=Utilities.jsonfyNode("Products",retailerName, listOfProducts);
		
		return Response.ok(datajson).build();

	}



	@Path("{retailerName}/divisions")
	public DivisionResource getDivisionsApi(@PathParam("retailerName") String retailerName,
			@QueryParam("retailerId") @DefaultValue("") String retailerId
			) throws JSONException{

		System.out.println("[ RetailerResource ] inside /divisions redirect.");

		if(Utilities.emptyPropertyCheck(retailerId)){

			throw new InvalidParameterException("retailerId");

		}else{
			// retailer validation
			boolean rStatus=this.graphDAO.validateNode(retailerId, retailerName);

			if(rStatus==false){
				
				throw new NotFoundException("Retailer",retailerName);  

			}
		}
		return new DivisionResource(retailerId);

	}




}
