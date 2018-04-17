package com.api.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;


import graph.viz.api.customExceptions.ConnectionException;
import graph.viz.pojo.Node;

public class GraphDAO {

	//private static String titanGraph ;
	private String titanGraph ;

	public GraphDAO() {

		System.out.println("[ GraphDAO ] inside constructor.");
		titanGraph="";
		
		
		/*
			System.out.println("[ GraphDao ] inside constructor.");
			try {
				GraphDAO.titanGraph = TitanConnection.getConnection();
			} catch (ConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
	}
	
	
	// validate the name and nodeId belongs to same node 
	public boolean validateNode(String nodeId, String nodeName){

		System.out.println("[ GraphDAO ]retailer validation.");

		boolean isValidRetailer=false;

		//retailer validation logic here ........
		isValidRetailer=true;

		return isValidRetailer;


	}
	

	//get List of products from the node 
	public List<Node> getNodeProducts(String level,String nodeId,int from,int stock) {
		
		// edge "pd" is taken by default for fetching products
		// calculate to from stock

		System.out.println("[ GraphDAO ] getNodeProducts.");
		List<Node> listOfProduct = new ArrayList<Node>();
		Node n1=new Node(1L,"plable1","pname1");
		Node n2=new Node(12L,"plable2","pname2");
		Node n3=new Node(123L,"plable3","pname3");
		Node n4=new Node(1234L,"plable4","pname4");
		Node n5=new Node(12345L,"plable5","pname5");
		Node n6=new Node(123456L,"plable6","pname6");
		listOfProduct.add(n1);
		listOfProduct.add(n2);
		listOfProduct.add(n3);
		listOfProduct.add(n4);
		listOfProduct.add(n5);
		listOfProduct.add(n6);
		
		return listOfProduct;

	}
	
	//get List of products from the node 
	public List<Node> getNodeChildLevel(String level,String nodeId,int from,int stock) {
		
		// edge "cn" is taken by default for fetching products
		// calculate to from stock
		// not work for get retailers 
		System.out.println("[ GraphDAO ] getNodeChildLevel.");

		List<Node> listOfchild= new ArrayList<Node>();
		Node n1=new Node(1L,"clable1","cname1");
		Node n2=new Node(12L,"clable2","cname2");
		Node n3=new Node(123L,"clable3","cname3");
		listOfchild.add(n1);
		listOfchild.add(n2);
		listOfchild.add(n3);
		
		
		return listOfchild;

	}


	//get List of retailers
	public List<String> getRetailers(String level,int from , int stock) {
		
		// edge "cn" is taken by default for fetching products
				// calculate to from stock

		System.out.println("[ GraphDAO ] getRetailers.");

		List<String> listOfRetailer=new ArrayList<String>();
		listOfRetailer.add(level);
		listOfRetailer.add(from+"");
		listOfRetailer.add(stock+"");
		listOfRetailer.add("amazon");
		listOfRetailer.add("ebay");
		listOfRetailer.add("staples");
		
		/*
		long starttime=System.currentTimeMillis();
		TitanTransaction titanTransaction=titanGraph.newTransaction();
		
		Iterator iterator=new GremlinPipeline<Vertex, Vertex>(titanTransaction.getVertices()).has("name","ebay").outE("cn").inV().has("name", "watches").outE("pd").
	              range(0, 4)
	              .inV()
	              .iterator();
		
		long endtime=System.currentTimeMillis();
		
		long total = starttime-endtime;
		
		System.out.println("total time ="+total);*/

		return listOfRetailer;

	}
	

	/*//validate retailer
	public boolean validateRetailer(String rName) {

		System.out.println("[ GraphDAO ]retailer validation.");

		boolean isValidRetailer=false;

		//retailer validation logic here ........
		isValidRetailer=true;

		return isValidRetailer;

	}*/




	/*//validate divisions based for a retailer
	public boolean validateDivision(String rName,String divName) {

		System.out.println("[ GraphDAO ]division validation.");

		boolean isValidDivision=false;

		//retailer validation logic here ........
		isValidDivision=true;

		return isValidDivision;

	}
	 */

	/*//get List of divisions for a retailers 
	public List<String> getRetailerDivisions(String retailerID,int form,int stock) {

		System.out.println("[ GraphDAO ] getRetailerDivisions.");

		List<String> listOfDivisions=new ArrayList<String>();
		listOfDivisions.add("watches");
		listOfDivisions.add("apparels");
		listOfDivisions.add("mobiles");

		return listOfDivisions;

	}
*/

	/*
	//validate divisions based for a retailer
	public static boolean validateCategory(String divName,String divStatus) {

		System.out.println("[ GraphDAO ]division validation.");

		boolean isValidCategory=false;

		//retailer validation logic here ........
		isValidCategory=true;

		return isValidCategory;

	}
	 */


	/*//get List of divisions for a retailers
	public  List<String> getDivisionCategories(String divisionId) {

		System.out.println("[ GraphDAO ] getDivisionCategories.");

		List<String> listOfCategories=new ArrayList<String>();
		listOfCategories.add("cat1");
		listOfCategories.add("cat2");
		listOfCategories.add("cat3");

		return listOfCategories;

	}

	//get List of divisions for a retailers
	public static List<String> getCategoryDepartments(String categoryId) {

		System.out.println("[ GraphDAO ] getCategoryDepartments.");

		List<String> listOfDepartments=new ArrayList<String>();
		listOfDepartments.add("dept1");
		listOfDepartments.add("dept2");
		listOfDepartments.add("dept3");

		return listOfDepartments;

	}


	//get List of divisions for a retailers
	public  List<String> getDepartmentClasses(String deptId) {

		System.out.println("[ GraphDAO ] getDepartmentClasses.");

		List<String> listOfClasses=new ArrayList<String>();
		listOfClasses.add("class1");
		listOfClasses.add("class2");
		listOfClasses.add("class3");

		return listOfClasses;

	}


	//get List of divisions for a retailers
	public static List<String> getClassSubclasses(String classId) {

		System.out.println("[ GraphDAO ] getClassSubclasses.");

		List<String> listOfsubclasses=new ArrayList<String>();
		listOfsubclasses.add("subclasss1");
		listOfsubclasses.add("subclasss2");
		listOfsubclasses.add("subclasss3");

		return listOfsubclasses;

	}*/

}


