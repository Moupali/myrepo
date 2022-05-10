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
