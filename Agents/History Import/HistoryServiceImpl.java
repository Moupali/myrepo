package com.example.service;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Operation.and;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.bhive.dnso.model.Lead;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import com.cloudant.client.api.query.Selector;
import com.cloudant.client.api.scheduler.SchedulerJobsResponse.History;
import com.cloudant.client.org.lightcouch.DocumentConflictException;
import com.google.gson.JsonObject;

public interface HistoryServiceImpl {
	
	// Import Lead History from Domino to DNSO Cloud

	@Override
	public JsonObject importLeadHistory(MultipartFile multipartFile) {
		
		JsonObject result = null;
		Selector selector = null;
		Workbook workbook;
		History history;
		Lead lead;
		String leadId="";
		Number creationDate=0;
		String creator= "";
		String oldStatus = "";
		String newStatus = "";
		String finaltable = "";
		

		
//		JsonArray leadResult = new 	JsonArray();
		Database leadDb = client.database("lead", false);
		
		JsonObject historyJson = new JsonObject();
		
		String historyTableStart = "<div class='ds-table-container'><table class='ds-table'><thead><tr><th>Field Name</th><th>Old Data</th><th>New Data</th></tr></thead><tbody>";
		String historyTableEnd = "</tbody></table></div>";
		String historyTablebody = "";
		 JsonObject finalResult = new JsonObject();
			Database historyDb = client.database("history", false);


		
		try { //Big try
			workbook = new XSSFWorkbook(multipartFile.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			int i=0,j=0;
			while (rowIterator.hasNext())
			{ //Big while 
				String statusData ="";
				Lead leadObj = null;

				Row row = rowIterator.next();
//			      
//				if (row.getRowNum() == 2) {
//					break;
//				}

				if (row.getRowNum() == 0) {
				}else {
					Iterator<Cell> cellIterator = row.cellIterator();
					
					while (cellIterator.hasNext())
					{
						   Cell cell = cellIterator.next();
                           int columnindex = cell.getColumnIndex();
										
						      if (columnindex == 0) {
						    	  
						    	  //checking lead present on cloudant or not
						     	     leadId= cell.getStringCellValue().trim();

						    	  try {
										leadObj = leadDb.find(Lead.class, leadId);

									} catch(Exception e) {
										leadObj=null;
										System.out.println("Lead not presdent :"+leadId);
									}
									 System.out.println("LEAD ID:"+ cell.getStringCellValue().trim());
							         i=i+1;
							         System.out.println("lead no:"+i);
						        
						       }
                              else if (columnindex == 1) {
                                	//history
                            	  if(leadObj==null) {
                            		  historyTablebody="";
									}else {
                        	     historyTablebody = cell.getStringCellValue().trim();
                        	     j++;
                        	     //System.out.println("historyTablebody 1::: "+historyTablebody+"value is:"+j);
                        	     
                               }
                              }
                             else if (columnindex == 2) {
                        	       //creator
                            	 if(leadObj==null) {
                            		 creator="";
									}else {
                        	    creator =cell.getStringCellValue().trim();
                              }
                             }
                            else if (columnindex == 3) {
                        	      //creation date
                        	
                        	     ///25-07-2017 23:37:08 ZE5B
                            	if(leadObj==null) {
                            		creationDate=0;
								}else {
                        	
                        	   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        	   Date value = cell.getDateCellValue();
                               String strDate = sdf.format(value);  

                       	       Date date = sdf.parse(strDate);
//                       	       System.out.println("date :::"+date);
//                       	       System.out.println("Given Time in milliseconds : "+date.getTime());
                   	           creationDate=date.getTime();
								}

                           }else if (columnindex == 4) {
                     	      //old status
                        	   if(leadObj==null) {
                        		   oldStatus="";
								}else {
                           	oldStatus =cell.getStringCellValue().trim();
                           	}
                        }else if(columnindex == 5) {
                        	//new status
                        	String data = "";
                        	String historyBody = historyTablebody;
                        	if(leadObj==null) {
                        		newStatus="";
							}else {
                        	newStatus =cell.getStringCellValue().trim();
                        	if(!(oldStatus.equals(newStatus))) {
//                        		 statusData ="<tr><td>Status </td><td></td><td>"+oldStatus+"</td></tr><tr><td>"+newStatus+"</td><td></td><td></td></tr></table>";
                        		 statusData = "<tr><td>Status : </td><td>"+oldStatus+"</td><td>"+newStatus+"</td></tr></table>";
                        	}else {
                        		statusData = "";
                        	}
                        	
                        	int length = historyBody.length();
                        	//System.out.println("length :::"+length );
                        	 data += historyBody.substring(7, historyBody.length() - 8);
                        	//System.out.println("data :::"+data );

                        	historyTablebody=data+statusData;
                        	statusData = "";
                        	//System.out.println("historyTablebody After****"+historyTablebody );

                        }
                        }
                            
				       }// while close
					
					
                      selector = and(eq("boname", "LEADHISTORY"),eq("leadId",leadId));
					
					String builder = new QueryBuilder(selector).build();// fields("_id","_rev","role","userEmail","country").build();
					QueryResult<History> dbresult = historyDb.query(builder, History.class);
					
					List<History> doc = dbresult.getDocs();
					
					    Integer size=doc.size();
                        System.out.println("size:::"+size);

                  if(leadObj!=null) {
					
					try {
//						if(historyTablebody == "" || historyTablebody == null) {
//							System.out.println("History body is blank::::");
//						}else {
//							System.out.println("History body is not blank::::");

						    finaltable = historyTableStart + historyTablebody + historyTableEnd;
							historyJson.addProperty("history", finaltable);
							historyJson.addProperty("creator", creator);
							historyJson.addProperty("leadId", leadId);
							historyJson.addProperty("boname", "LEADHISTORY");
							historyJson.addProperty("oldstatus",oldStatus);
							historyJson.addProperty("creationDate",creationDate);
							historyJson.addProperty("newstatus",newStatus);
//							System.out.println("historyJson 1::: "+historyJson);
							
							historyDb.save(historyJson);
				              finalResult.add("result", historyJson);
				              

						//}
					}catch(DocumentConflictException d) {
						result = new JsonObject();

						logger.info("Exception e:" + d);
						logger.info("Lead Already Exists!!");
						result.addProperty("status", "Lead Already Exists!!");

					}
                  }

						
			           } //else close
	                }//while close
                 workbook.close();
	
	               }catch(Exception e) {
	            	result = new JsonObject();
	               	logger.info(e.getMessage());
            		e.printStackTrace();
		             result.addProperty("status", "Something went wrong");
	                 }
	             
		               return finalResult;
	
	
	}

}
