package com.api.resources;

import javax.ws.rs.Path;

/**
 * Created by UGAM\kishore.damodara on 23/10/15.
 */
@Path("/api.ugam.com/v1")
public class GraphQueryResource {

	@Path("/retailers")
	public RetailerResource retailerResource() {
		
		System.out.println("[ GraphQueryResource ] inside /app/retailers.");
		
		

		return new RetailerResource();
	}
}
