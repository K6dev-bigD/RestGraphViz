package com.api.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import graph.viz.pojo.Node;


public class Utilities {

	/*public static boolean isValidRetailer(String rname){

		boolean isValidRetailer=false;
		System.out.println("[ Retailer-resource-validator-private ] retailer validation .");

		//retailer validation logic here ........
		isValidRetailer=Rdao.validateRetailer(rname);


		return isValidRetailer; 
	}*/

	public static URI getRedirectionURI(UriInfo uriInfo,String redirectionPath) throws URISyntaxException{

		String baseUri= uriInfo.getBaseUri().toString();
		String redirectUri=redirectionPath.startsWith("/")? redirectionPath.substring(1):redirectionPath;

		String absoluteRedirectedUri=baseUri+redirectUri;

		URI url = new URI(absoluteRedirectedUri);

		return url;

	}

	public static URI getRedirectionURI(UriInfo uriInfo,String redirectionPath,String firstUri) throws URISyntaxException{

		String baseUri= uriInfo.getBaseUri().toString();
		String redirectUri=redirectionPath.startsWith("/")? redirectionPath.substring(1):redirectionPath;

		String absoluteRedirectedUri=baseUri+redirectUri+"?"+"firstUri="+firstUri;

		URI url = new URI(absoluteRedirectedUri);

		return url;

	}

	public static boolean emptyPropertyCheck(String property){

		if(property.isEmpty() || property.equals("\"\"") || property.length()==0){

			return true;

		}else{

			return false;
		}

	}

	public static String jsonfyNode(String jsontype,String lable ,List<Node> list){

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		JsonArray result = (JsonArray) gson.toJsonTree(list,
				new TypeToken<List<Node>>() {
		}.getType());

		JsonObject obj=new JsonObject();
		obj.addProperty("jsontype", jsontype);
		obj.addProperty("lable", lable);
		obj.add("children", result);
		
		String data = gson.toJson(obj);
		
		return data;
	}


}
