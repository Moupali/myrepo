package com.example.leadupdate;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Expression.in;
import static com.cloudant.client.api.query.Operation.and;
import static com.cloudant.client.api.query.Expression.ne;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import com.cloudant.client.api.query.Selector;
import com.cloudant.client.api.query.Sort;
import com.example.model.Lead;
import com.example.model.Country;


public class ApprovalDateUpdate {
	

	
	public static Map<String, String> country = new HashMap<String, String>();
	public static Map<String, String> code = new HashMap<String, String>();


	
	public static void main(String[] args) throws IOException {
		
		ApprovalDateUpdate obj = new ApprovalDateUpdate();

		
		Database leadDb = obj.getCloudantDatabase("lead");
		Selector selector = null;
//		eq("selectedBrand","TSS"),
//		eq("offeringname","Maintenance-Logo-HW Maintenance"),
//		in("country",country));
		
		//selector = eq("boname", "LEAD");

		selector =  and(eq("boname", "LEAD"));
		String builder = new QueryBuilder(selector).build();// fields("_id","_rev","role","userEmail","country").build();
		QueryResult<Lead> result = leadDb.query(builder, Lead.class);
		
		List<Lead> leadList = result.getDocs();
		int i = 1,j=1,count=0;
		System.out.println("Total Lead documents: "+leadList.size());
		
		for (Lead lead : leadList) {
			if(count==400)
			{
				break;
			}else {

			if(i%200 == 0)
			{
			 try {
				Thread.sleep(1000);
			 } catch (InterruptedException e) {
				
			  }	
			}
//			System.out.println("lead id ::::::::::::::"+lead.get_id() + "country is :::"+lead.getNoticer_country());
			   try {

				   
			          Number approvalDate = lead.getApprovaldate();
			          Number activationDate= lead.getActivationdate();
			          
			          System.out.println("lead id :"+ lead.get_id());
            
			          if(activationDate == null) {
							//lead.setFirstapprovaldate(approvalDate);
						}else {
							//lead.setFirstapprovaldate(activationDate);

						}
                      System.out.println("approval date::"+lead.getApprovaldate()); //moudutt1@in.ibm.com
                      System.out.println("activate date::"+lead.getActivationdate()); //moudutt1@in.ibm.com
                      System.out.println("approval date::"+lead.getFirstapprovaldate()); //moudutt1@in.ibm.com

		              //com.cloudant.client.api.model.Response res = leadDb.update(lead);	
				    
			
			     }catch(Exception e) {
			    	e.printStackTrace();
			      }
			    
			   System.out.println("count:::"+ count++);
		
		
	     }
		}
		
	}


	private Database getCloudantDatabase(String databaseName) {
		// TODO Auto-generated method stub
		URL url = null;
		try {
			url = new URL(CLOUDANT_URL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		ClientBuilder client = ClientBuilder.url(url).username(CLOUDANT_USERNAME).password(CLOUDANT_PASSWORD);
		CloudantClient c = client.build();
		Database database = c.database(databaseName, true);
		return database;

	}
	
    	
}
