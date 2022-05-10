package com.example.leadupdate;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Expression.in;
import static com.cloudant.client.api.query.Expression.ne;
import static com.cloudant.client.api.query.Operation.and;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import com.cloudant.client.api.query.Selector;
import com.example.model.Lead;
import com.example.model.Country;


public class NoticerCountryUpdate {
	


	public static void main(String[] args) throws IOException {
		
		NoticerCountryUpdate obj = new NoticerCountryUpdate();
		//Database leadDb = leadDataUpdate.getCloudantDatabase("lead");

		
		Database leadDb = obj.getCloudantDatabase("lead");
		Database countrydb = obj.getCloudantDatabase("geo");
		Selector selector = null;

		//selector = and(eq("boname", "LEAD"),eq("selectedBrand","TSS"),eq("offeringname","Maintenance-Logo-HW Maintenance"),in("country",country));

		selector = eq("boname","LEAD");
		
		String builder = new QueryBuilder(selector).build();// fields("_id","_rev","role","userEmail","country").build();
		QueryResult<Lead> result = leadDb.query(builder, Lead.class);
		
		List<Lead> leadList = result.getDocs();
		int i = 1,j=1,count=0;
		System.out.println("Total Lead documents: "+leadList.size());
//		for (Lead lead : leadList) {
//		
//			if(i%200 == 0)
//			{
//			 try {
//				Thread.sleep(1000);
//			 } catch (InterruptedException e) {
//				
//			  }	
//			}
//			try {
//			String noticer_email = lead.getNoticer();
//			System.out.println("count ::"+count++); //moudutt1@in.ibm.com
//            System.out.println("noticer_email ::"+noticer_email); //moudutt1@in.ibm.com
//            
//            
//			
////			String[] data = noticer_email.split("@");
//		     int data1 = noticer_email.indexOf('@');
//		     String countrycode = noticer_email.substring(data1+1, data1+3);
//			//System.out.println("data ::"+data); //moudutt1@in.ibm.com
////			System.out.println("data1 ::"+data1); //moudutt1@in.ibm.com
//			//System.out.println("countrycode ::"+countrycode); //moudutt1@in.ibm.com
//			
//			
//			Selector selectorcountry = null;
//
//			//selector = and(eq("boname", "LEAD"),eq("selectedBrand","TSS"),eq("offeringname","Maintenance-Logo-HW Maintenance"),in("country",country));
//
//			selectorcountry = eq("boname","COUNTRY");
//			String builder1 = new QueryBuilder(selectorcountry).build();// fields("_id","_rev","role","userEmail","country").build();
//			QueryResult<Country> result1 = countrydb.query(builder1, Country.class);
//			
//			List<Country> countryList = result1.getDocs();
//			for (Country country : countryList) {
//				
//				if(country.getCc().equals(countrycode)) {
//				
//					System.out.println("country.getName() ::"+country.getName());
//					System.out.println("country.getCc() ::"+country.getCc());
//					System.out.println("countrycode ::"+countrycode);
//
//					lead.setNoticer_country(country.getName());
//				}
//				j++;
//				}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//			System.out.println("lead.getNoticer_country ::"+lead.getNoticer_country());
//
//
//                  i++;

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
	
}
