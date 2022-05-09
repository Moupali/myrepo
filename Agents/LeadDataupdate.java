
package com.example.leadupdate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.Selector;
import com.example.model.Lead;
import com.example.model.Offering;
import com.cloudant.client.api.query.QueryResult;
import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Operation.and;
import static com.cloudant.client.api.query.Expression.in;
import static com.cloudant.client.api.query.Expression.ne;
import static com.cloudant.client.api.query.Expression.regex;

import java.util.List;




public class LeadDataupdate {
	
	
	//DNSO dev credentials --------------------------
//	private static final String CLOUDANT_URL = "https://e2994c2f-ed81-4b92-9d36-4c24a77c9dc2-bluemix:142ea6cf02f00857b7809b0b13ad3454aaaba608a3aadd315aa6a54250b08d8c@e2994c2f-ed81-4b92-9d36-4c24a77c9dc2-bluemix.cloudantnosqldb.appdomain.cloud";
//	private static final String CLOUDANT_USERNAME = "e2994c2f-ed81-4b92-9d36-4c24a77c9dc2-bluemix";
//	private static final String CLOUDANT_PASSWORD = "142ea6cf02f00857b7809b0b13ad3454aaaba608a3aadd315aa6a54250b08d8c";
//	
	//DNSO test credentials :::::::::::::::::::
	
//	private static final String CLOUDANT_URL = "https://4956aa17-e14f-4f6e-86f8-c377ae59ce11-bluemix:af779f766ed2f7fcf0e33c87bb39d1ce85dffe3f7155297d38ea2c8b639aabf6@4956aa17-e14f-4f6e-86f8-c377ae59ce11-bluemix.cloudantnosqldb.appdomain.cloud";
//	private static final String CLOUDANT_USERNAME = "4956aa17-e14f-4f6e-86f8-c377ae59ce11-bluemix";
//	private static final String CLOUDANT_PASSWORD = "af779f766ed2f7fcf0e33c87bb39d1ce85dffe3f7155297d38ea2c8b639aabf6";
//
	
//// DNSO  prod credentials ::::::::::
//	
	private static final String CLOUDANT_URL = "https://590388f0-bf43-4c98-a3eb-d83920dd4873-bluemix:75eaab4907209a4e9bc8040dbef3271ce87bf73c87aee9518a5d382e52a4eea4@590388f0-bf43-4c98-a3eb-d83920dd4873-bluemix.cloudantnosqldb.appdomain.cloud";
	private static final String CLOUDANT_USERNAME = "590388f0-bf43-4c98-a3eb-d83920dd4873-bluemix";
	private static final String CLOUDANT_PASSWORD = "75eaab4907209a4e9bc8040dbef3271ce87bf73c87aee9518a5d382e52a4eea4";

	
	
	public static void main(String[] args) throws IOException {
		
		
		LeadDataupdate leadDataUpdate= new LeadDataupdate();
		
		Database leadDb = leadDataUpdate.getCloudantDatabase("lead");
		Selector selector = null;
		
//		String imtarray[] = emeacountry.split(",");
		String country [] = {"618","848","724"};
	
		
		//"flow": "2"
		// "status": "42",
//		selector = and(eq("boname", "LEAD"),eq("opportunityowner","mkubiak@sk.ibm.com")); 848 618 724
		//selector = and(eq("boname", "LEAD"),eq("selectedBrand","TSS"),eq("offeringname","Services-Other/Individual Services HW"),eq("country","618"));
		//selector = and(eq("boname", "LEAD"),eq("selectedBrand","TSS"),eq("offeringname","Maintenance-Logo-HW Maintenance"),in("country",country));

		//selector = and(eq("boname", "LEAD"),eq("flow","2"),eq("selectedBrand","SYSTEM"),eq("status","34"));
		//selector = and(eq("boname", "LEAD"),eq("flow","2"),eq("selectedBrand","SYSTEM"),eq("status","34"),ne("status","9"));
		
		//sselector = and(eq("boname", "LEAD"),eq("status","1"),eq("selectedBrand","SBU"),in("country","897"));

//	    selector = and(eq("boname", "LEAD"),
//	    eq("status","1"),eq("offeringname","Lab Services"),eq("selectedBrand","SYSTEM"));
		
	    //selector = and(eq("boname", "LEAD"),regex("contractsigneddate","IST"));
		String statusarray = "31,32,34";
		String [] status = statusarray.split(",");
//		selector = and(eq("boname", "LEAD"),eq("opportunityowner","TIPTOND@uk.ibm.com"),in("status",status));
		selector = and(eq("boname", "LEAD"),eq("noticer","jillw@us.ibm.com"));



		String builder = new QueryBuilder(selector).build();// fields("_id","_rev","role","userEmail","country").build();
		System.out.println("builder::"+builder);
		QueryResult<Lead> result = leadDb.query(builder, Lead.class);
		
		List<Lead> leadList = result.getDocs();
		int i = 1;
		System.out.println("Total Lead documents: "+leadList.size());
		for (Lead lead : leadList) {
		
			if(i%200 == 0)
			{
			 try {
				Thread.sleep(1000);
			 } catch (InterruptedException e) {
				
			  }	
			}
			
			System.out.println("Lead Id:"+lead.get_id());
			//System.out.println("Status:"+ lead.getStatus());
			//System.out.println("Offering name:"+ lead.getOfferingname());
			

			
//			offering.setLevel20code("");
			//lead.setFlow("2");
			//lead.setStatus("32");
			//lead.setNoticer("tereza.borecka@cz.ibm.com");
			//lead.setOpportunityowner("SAMIRAA@eg.ibm.com");
			//lead.setFocalpoint("csodergr@us.ibm.com");
			//System.out.println("UPDATED status:"+lead.getOpportunityowner());
			//lead.setNoticer_country("Germany");
//			lead.setNoticer_department("814001");
//			lead.setNoticer_div("48");
//			lead.setNoticer_org_id("119964");
//			lead.setFocalpoint("Timi.Balogun@ibm.com");
			
//			System.out.println("UPDATED status:"+lead.getFocalpoint());
//			System.out.println("Country code:"+lead.getCountry());

//			lead.setFocalpoint("jcastro@ibm.com");
//			lead.setFocalpoint("TIPTOND@uk.ibm.com");
//			System.out.println("Country code:"+lead.getOpportunityowner());


			//offering.setUtlevel30des("");
			//offering.setSkipLeadFromMassload("true");
//			com.cloudant.client.api.model.Response res = leadDb.update(lead);
			
			System.out.println("i :"+ i);
				i++;
			
	 	}
	}
public Database getCloudantDatabase(String databaseName) {
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
