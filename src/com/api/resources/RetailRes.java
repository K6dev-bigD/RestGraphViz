package com.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.api.dao.GraphDAO;

@Path(value = "/retailers")
public class RetailRes {

	//private final TitanGraphConfig titanConfiguration;
	private static String titanGraph ;
	
	@Context
    UriInfo uriInfo;

	public RetailRes() {
		
		titanGraph="";
	}


/*

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRetailersName() throws ParseException {

		System.out.println("[ Retailer-Resource ] Default Retailer Service .");
		
		System.out.println("uriInfo1="+uriInfo.getAbsolutePath().toString());

		JSONObject jsondata = new JSONObject();
		jsondata.put("retailers", "[\"amazon\" , \"ebay\" , \"staples\"]");
		String data = jsondata.toJSONString();

		//TitanTransaction transaction = titanGraph.newTransaction();
		//transaction.commit();

		return  Response.status(200).entity(data).header("Access-Control-Allow-Origin", "*").build();
	}



	@GET
	@Path("{rName}")
	public Response validateRetailer(@PathParam("rName") String rName) {


		System.out.println("[ Retailer-Resource ]  retailer validation service .");

		//TitanTransaction transaction = titanGraph.newTransaction();

		System.out.println("uriInfo2="+uriInfo.getAbsolutePath().toString());

		JSONObject errorJson = new JSONObject();
		boolean status=RetailRes.isValidRetailer(rName);	
		errorJson.put(rName, status);
		String data = errorJson.toJSONString();

		//transaction.commit();

		return Response.status(200).entity(data).build();

	}

  //  @GET   // here get is important because the after routing the reply to client will come from here
	
	@Path("{rName}/divisions")
	public DivisionResource redirectDivisions(@PathParam("rName") String rName) throws ParseException {


		System.out.println("[ Retailer-Resource ]  retailer redirect to division service called .");
		
		System.out.println("uriInfo3="+uriInfo.getAbsolutePath().toString());

		//TitanTransaction transaction = titanGraph.newTransaction();
		// validate retailer
		boolean status=RetailRes.isValidRetailer(rName);	
		String retailerId="1234";
		//get retailer
		//transaction.commit();

		return new DivisionResource();

	}

	
	@Path("{rName}/divisions/{divisionName}")
	public Response validateDivision(@PathParam("divisionName") String divisionName) throws ParseException {


		System.out.println("[ Retailer-Resource ]  retailer redirect to division service called .");
		
		System.out.println("uriInfo3="+uriInfo.getAbsolutePath().toString());

		//TitanTransaction transaction = titanGraph.newTransaction();
		// validate retailer
		//boolean status=RetailResource.isValidRetailer(rName);	
		String retailerId="1234";
		//get retailer
		//transaction.commit();

		return Response.status(200).entity("div validation").build();

	}
	
	
	
	
	@GET
	@Path("{rName}/products")
	public Response getRetailerProducts(@PathParam("rName") String rName) {


		System.out.println("[ Retailer-Resource ]  retailer products service called .");
		
		System.out.println("uriInfo4="+uriInfo.getAbsolutePath().toString());
		
		JSONObject errorJson = new JSONObject();
		errorJson.put("msg", "[ Retailer-products ] request processed !");
		String data = errorJson.toJSONString();
		//TitanTransaction transaction = titanGraph.newTransaction();
		//transaction.commit();


		return Response.status(200).entity(data).build();

	}


	private static boolean isValidRetailer(String rname){

		boolean isValidRetailer=false;
		
		//retailer validation logic here ........
		isValidRetailer=RetailerDAO.validateRetailer(rname);


		return isValidRetailer; 
	}
*/

}