package com.example.offeringupdate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.Selector;
import com.example.model.Offering;
import com.cloudant.client.api.query.QueryResult;
import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Operation.and;
import static com.cloudant.client.api.query.Expression.in;
import java.util.List;




public class OfferingDataUpdate_UtLevel {
	
	
	//DNSO dev credentials --------------------------
//	private static final String CLOUDANT_URL = "https://e2994c2f-ed81-4b92-9d36-4c24a77c9dc2-bluemix:142ea6cf02f00857b7809b0b13ad3454aaaba608a3aadd315aa6a54250b08d8c@e2994c2f-ed81-4b92-9d36-4c24a77c9dc2-bluemix.cloudantnosqldb.appdomain.cloud";
//	private static final String CLOUDANT_USERNAME = "e2994c2f-ed81-4b92-9d36-4c24a77c9dc2-bluemix";
//	private static final String CLOUDANT_PASSWORD = "142ea6cf02f00857b7809b0b13ad3454aaaba608a3aadd315aa6a54250b08d8c";
//	
	//DNSO test credentials :::::::::::::::::::
	
	//private static final String CLOUDANT_URL = "https://4956aa17-e14f-4f6e-86f8-c377ae59ce11-bluemix:af779f766ed2f7fcf0e33c87bb39d1ce85dffe3f7155297d38ea2c8b639aabf6@4956aa17-e14f-4f6e-86f8-c377ae59ce11-bluemix.cloudantnosqldb.appdomain.cloud";
//	private static final String CLOUDANT_USERNAME = "4956aa17-e14f-4f6e-86f8-c377ae59ce11-bluemix";
//	private static final String CLOUDANT_PASSWORD = "af779f766ed2f7fcf0e33c87bb39d1ce85dffe3f7155297d38ea2c8b639aabf6";
//
	
//// DNSO  prod credentials ::::::::::
//	
	private static final String CLOUDANT_URL = "https://590388f0-bf43-4c98-a3eb-d83920dd4873-bluemix:75eaab4907209a4e9bc8040dbef3271ce87bf73c87aee9518a5d382e52a4eea4@590388f0-bf43-4c98-a3eb-d83920dd4873-bluemix.cloudantnosqldb.appdomain.cloud";
	private static final String CLOUDANT_USERNAME = "590388f0-bf43-4c98-a3eb-d83920dd4873-bluemix";
	private static final String CLOUDANT_PASSWORD = "75eaab4907209a4e9bc8040dbef3271ce87bf73c87aee9518a5d382e52a4eea4";

	
	
	public static void main(String[] args) throws IOException {
		
		
		OfferingDataUpdate_UtLevel offeringDataUpdate_UtLevel = new OfferingDataUpdate_UtLevel();
		
		Database offeringDb = offeringDataUpdate_UtLevel.getCloudantDatabase("offering");
		Selector selector = null;
		selector = and(eq("boname", "OFFERING"),eq("level30code","30GKT"),eq("brand","TSS"));
		String builder = new QueryBuilder(selector).build();// fields("_id","_rev","role","userEmail","country").build();
		QueryResult<Offering> result = offeringDb.query(builder, Offering.class);
		
		List<Offering> offeringList = result.getDocs();
		int i = 1;
		System.out.println("Total offering documents: "+offeringList.size());
		for (Offering offering : offeringList) {
		
			if(i%200 == 0)
			{
			 try {
				Thread.sleep(1000);
			 } catch (InterruptedException e) {
				
			  }	
			}
			
			System.out.println("Offering:"+offering.get_id());
			System.out.println("Level30code:"+ offering.getLevel30code());
			System.out.println("Offering name:"+ offering.getOfferingname());
			
			//offering.setLevel20code("");
			//offering.setLevel30code("30GLJ");
			System.out.println("UPDATED LEVEL30:"+offering.getLevel30code());
			//offering.setUtlevel30des("");
			//offering.setSkipLeadFromMassload("true");
//			com.cloudant.client.api.model.Response res = offeringDb.update(offering);
			
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
