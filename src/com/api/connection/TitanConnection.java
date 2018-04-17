package com.api.connection;
/*
import java.util.Map;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;

import graph.viz.api.customExceptions.ConnectionException;


public class TitanConnection {

	//private TitanGraphConfig titanConfiguration;

	private static TitanGraph titanGraph = null;


	// responsible for initi the connection .. should be called once when initializing connection 
	public static void initConnection() {

		if(titanGraph == null ){

			Configuration conf = new BaseConfiguration();
			conf.setProperty("storage.backend", "hbase");
			conf.setProperty("storage.port", "2181");
			conf.setProperty("storage.hbase.table","ProductGraph");
			conf.setProperty("storage.hostname","10.10.20.52");
			titanGraph = TitanFactory.open(conf);
		}
	}
	
	public static TitanGraph getConnection() throws ConnectionException{

		TitanConnection.initConnection();
		return titanGraph;

	}

	public static void closeConnection() {
		if(!titanGraph.isClosed()) {
			titanGraph.shutdown();
		}
	}

}
*/