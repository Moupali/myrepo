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


public class NoticerCountryAgent {
	

	
	public static Map<String, String> country = new HashMap<String, String>();
	public static Map<String, String> code = new HashMap<String, String>();


	
	public static void main(String[] args) throws IOException {
		
		NoticerCountryAgent obj = new NoticerCountryAgent();

		
		Database leadDb = obj.getCloudantDatabase("lead");
		Selector selector = null;
//		selector = and(eq("boname", "LEAD"),
//		eq("selectedBrand","TSS"),
//		eq("offeringname","Maintenance-Logo-HW Maintenance"),
//		in("country",country));

		selector =  and(eq("boname", "LEAD"),ne("_id",""),eq("noticer_country",""));
		String builder = new QueryBuilder(selector).build();// fields("_id","_rev","role","userEmail","country").build();
		QueryResult<Lead> result = leadDb.query(builder, Lead.class);
		
		List<Lead> leadList = result.getDocs();
		int i = 1,j=1,count=0;
		System.out.println("Total Lead documents: "+leadList.size());
		
		for (Lead lead : leadList) {
//			if(count==4)
//			{
//				break;
//			}else {

			if(i%200 == 0)
			{
			 try {
				Thread.sleep(1000);
			 } catch (InterruptedException e) {
				
			  }	
			}
//			System.out.println("lead id ::::::::::::::"+lead.get_id() + "country is :::"+lead.getNoticer_country());
			   try {

				   
			          String noticer_email = lead.getNoticer();
			          //System.out.println("lead id :"+ lead.get_id());
                      System.out.println("lead id ::::::::::::::"+lead.get_id()+"   noticer_email ::"+noticer_email); //moudutt1@in.ibm.com
            
   		              int data1 = noticer_email.indexOf('@');
		              String countrycode = noticer_email.substring(data1+1, data1+3);
						System.out.println("countrycode..." +countrycode);

//		              Map<String,String> code =  new HashMap<>();
//						code =  countryService.getCountryCode();
//						
//
//						String data = code.get(cell.getStringCellValue().trim());
//						//System.out.println("DATA..." +data);
//						lead.setCountry(data);
		              
		               Map<String,String> code =  new HashMap<>();
				       code =  obj.getCountryList();
				

				       String data = country.get(countrycode);

				       lead.setNoticer_country(data);
				       System.out.println("data:::::"+data);
				       com.cloudant.client.api.model.Response res = leadDb.update(lead);	
				       System.out.println("lead.getNoticer_country if ::"+lead.getNoticer_country());
				    
			
			     }catch(Exception e) {
			    	e.printStackTrace();
			      }
			    
			   System.out.println("count:::"+ count++);
		
		
	     }
		//}
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
	
     public List<Country> getAllCountry() {
		
 		NoticerCountryAgent obj = new NoticerCountryAgent();

 		Database countrydb = obj.getCloudantDatabase("geo");
		Selector selector = null;
		selector = eq("boname", "COUNTRY");
		String builder = (new QueryBuilder(selector).sort(Sort.asc("name"))).build();
		QueryResult<Country> result = countrydb.query(builder, Country.class);
		List<Country> country = result.getDocs();
		
		
      return country;
	}
     
     public Map<String, String> getCountryList() {
 		

 		try {
 			List<Country> countryList = getAllCountry();
 			System.out.println("countryList 1:::::"+countryList);
 	        
 			Iterator<Country> iter = countryList.iterator();
 			while (iter.hasNext()) {
 				////System.out.println("inside counter loop:");
 				Country i = iter.next();
 				String countrycode = i.getCc();
 				String countryname = i.getName();
 				
// 				 System.out.println("countrycode:::"+countrycode);
// 				 System.out.println("countryname:::"+countryname);
 	      		   country.put(countrycode,countryname);
 			     		code.put(countryname,countrycode);
 				
 		
 			}

              } catch (Exception exception) {
             	System.out.println("exception ::"+exception);

             	}
 		//return code;
 		System.out.println("country is :::"+country);
 		return country;

 	}


	
	
}
