
package com.example.leadupdate;

import java.io.File;
import java.io.FileInputStream;
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

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class DataUpdateFromXcel {
	
	
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
		
		
		DataUpdateFromXcel leadDataUpdate= new DataUpdateFromXcel();
		
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
		
		try  
		{  
		File file = new File("C:\\Users\\MoupaliDutta\\Downloads\\Updated_georgi labservices.xlsx");   //creating a new file instance  
		FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
		//creating Workbook instance that refers to .xlsx file  
		XSSFWorkbook wb = new XSSFWorkbook(fis);   
		XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
		Iterator<Row> itr = sheet.iterator();    //iterating over excel
		
		while(itr.hasNext()) {
			Row row = itr.next();  
			Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
			while (cellIterator.hasNext())   
			{  
			Cell cell = cellIterator.next();  
//			String noticer_mail = cell.getStringCellValue().trim();
//			System.out.println("noticer_mail::"+noticer_mail);
//			
//			selector = and(eq("boname", "LEAD"),eq("noticer",noticer_mail));
			String builder = new QueryBuilder(selector).build();// fields("_id","_rev","role","userEmail","country").build();
//			System.out.println("builder::"+builder);
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
			    lead.setNoticer_delivery_center("Lab Services");
				System.out.println("Lead Id:"+lead.get_id()+":::"+lead.getNoticer_delivery_center());
//				com.cloudant.client.api.model.Response res = leadDb.update(lead);
				
				System.out.println("i :"+ i);
					i++;

		}
		}
	   
			
	 	}
		}catch(Exception e)  
	 	{  
	 		e.printStackTrace();  
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
