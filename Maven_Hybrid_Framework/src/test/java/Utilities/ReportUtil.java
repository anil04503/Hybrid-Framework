package Utilities;



import java.io.*;
import java.util.ArrayList;

import Test_Scripts.Hybrid_Framework;


import Utilities.TestUtil;

public class ReportUtil extends Hybrid_Framework{
	public static int scriptNumber=1;
	public static String indexResultFilename;
	public static String currentDir;
	public static String currentSuiteName;
	public static int datarepeat;
	//public static String currentSuitePath;
	
	public static double passNumber;
	public static double failNumber;
	public static boolean newTest=true;
	public static ArrayList<String> stepid=new ArrayList<String>();;
	public static ArrayList<String> usecaseid=new ArrayList<String>();;
	public static ArrayList<String> maflowid=new ArrayList<String>();;
	public static ArrayList<String> description=new ArrayList<String>();;
	public static ArrayList<String> keyword=new ArrayList<String>();;
	public static ArrayList<String> teststatus=new ArrayList<String>();;
	public static ArrayList<String> screenShotPath=new ArrayList<String>();;


	public static void startTesting(String filename,String testStartTime,String env,String rel,String suiteName)
	  {
		indexResultFilename = filename;
		currentDir = indexResultFilename.substring(0,indexResultFilename.lastIndexOf("//"));
		
		FileWriter fstream =null;
		 BufferedWriter out =null;
	      try{
	    // Create file 
	   
	     fstream = new FileWriter(filename);
	     out = new BufferedWriter(fstream);

        String RUN_DATE = TestUtil.now(CONFIG.getProperty("dateformat")).toString();
	    String ENVIRONMENT = env;//SeleniumServerTest.ConfigurationMap.getProperty("environment");
	    String RELEASE = rel;//SeleniumServerTest.ConfigurationMap.getProperty("release");
	    
	    out.newLine();
	  
	    out.write("<html>\n");
	    out.write("<HEAD>\n");
	    out.write(" <TITLE>Automation Test Results</TITLE>\n");
	     out.write("</HEAD>\n<script type='text/javascript'>function myFunction()" +
	     		"{var divResult=document.getElementById('resultSummary');" +
	    		"var divResultSum=document.getElementById('resultSumData');" +
	     		"divResult.appendChild(divResultSum); " +
	     		"var divBrowser=document.getElementById('clientSummary');" +
	     		"var divBrowserDet=document.getElementById('browserDiv');" +
	     		"divBrowser.appendChild(divBrowserDet); }</script>");
	     out.write("<body onload='myFunction()'>\n");
	     out.write("<h4 align=center><FONT COLOR=660066 FACE=AriaL SIZE=6><b><u> Automation Test Results</u></b></FONT></h4>\n");
	     //out.write("<marquee behavior=scroll direction=right scrollamount=3><span title='Anil Yadav Avula'><FONT COLOR=000000 FACE=AriaL SIZE=4><b>Developed By MI-ops QA Team</b></FONT></span></marquee>");
	     out.write("<table  border=1 cellspacing=1 cellpadding=1 style='float:down;margin-top:-5px;'>\n");
	     out.write("<tr>\n");

	           out.write("<h4> <FONT COLOR=660000 FACE=Arial SIZE=4.5><b><u>"+suiteName+" Test Details :</u></b></FONT></h4>\n");
	           out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run Date</b></FONT></td>\n");
	           out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+RUN_DATE+"</b></FONT></td>\n");
	     out.write("</tr>\n");
         
	     out.write("<tr>\n");
	           
	           out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run Start Time</b></FONT></td>\n");

	           out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+testStartTime+"</b></FONT></td>\n");
	     out.write("</tr>\n");
	     out.write("<tr>\n");
	    // out.newLine();   
	           out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Run End Time</b></FONT></td>\n");
	           out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>END_TIME</b></FONT></td>\n");
	     out.write("</tr>\n");
	     out.write("<tr>\n");
	   //  out.newLine();
	           
	           out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Environment</b></FONT></td>\n");
	           out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"+ENVIRONMENT+"</b></FONT></td>\n");
	     out.write("</tr>\n");
	     out.write("<tr>\n");
	           
	           out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Release</b></FONT></td>\n");
	           out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"+RELEASE+"</b></FONT></td>\n");
	     out.write("</tr>\n");
	 
	     out.write("</table><div id='clientSummary' style='margin-left:520px;margin-top:-167px;'></div><div id='resultSummary' style='float:right;margin-top:-106px;'></div>\n");
	     
	    //Close the output stream
	    out.close();
	    }catch (Exception e){//Catch exception if any
	      System.err.println("Error: " + e.getMessage());
	    }finally{
	    	
		    fstream=null;
		    out=null;
	    }
	  }
	public static void summaryreport(){
		  FileWriter fstream =null;
			BufferedWriter out =null;
		    try {
				fstream = new FileWriter(indexResultFilename,true);
		  	out = new BufferedWriter(fstream);
		  	out.write("<div id='resultSumData'> <h4 > <FONT COLOR=660000 FACE=Arial SIZE=4.5><b><u>Result Summary :</u></b></FONT></h4>\n");
		     out.write("<table  border=1 cellspacing=1 style='margin-top:-5px;' cellpadding=1 position:fixed;width:99.15%;height:15%;top:0;right:0;bottom:auto;left:0;border:Solid;>\n");
		     
		     out.write("<tr>\n");
		           out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Total No.Of Testcases</b></FONT></td>\n");
		           out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+testcasecount+"</b></FONT></td>\n");
		     out.write("</tr>\n");
		     
		     out.write("<tr>\n");
		           out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Total No.Of Testcases Passed</b></FONT></td>\n");
		           out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+passcount+"</b></FONT></td>\n");
		     out.write("</tr>\n");
		     
		     out.write("<tr>\n");
	               out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Total No.Of Testcases Failed</b></FONT></td>\n");
	               out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+failcount+"</b></FONT></td>\n");
	         out.write("</tr>\n");
	         
	         out.write("<tr>\n");
	               out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Total No.Of Testcases Skipped</b></FONT></td>\n");
	               out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+skipcount+"</b></FONT></td>\n");
	         out.write("</tr>\n");
	         
//	         out.write("<tr>\n");
//             		out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Operating System</b></FONT></td>\n");
//             		out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+os+"</b></FONT></td>\n");
//             out.write("</tr>\n");
//       
//             out.write("<tr>\n");
//             		out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Browser Name&Version</b></FONT></td>\n");
//             		out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+browsername+" "+browserversion+"</b></FONT></td>\n");
//             out.write("</tr>\n");

		     out.write("</table></div>\n");
		     //Close the output stream
			    out.close();
			    }catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			    }finally{
			    	
				    fstream=null;
				    out=null;
			    }
		
	}
    public static void startSuite(String suiteName){

	    FileWriter fstream =null;
		BufferedWriter out =null;
		currentSuiteName = suiteName.replaceAll(" ", "_");
	    try{
	    fstream = new FileWriter(indexResultFilename,true);
	  	out = new BufferedWriter(fstream);
	      
    	out.write("<div style='float:down;margin-top:100px;'><h4><FONT COLOR=660000 FACE= Arial  SIZE=4.5><b><u>"+suiteName+" Test Report :</u></b></FONT></h4>\n");
        out.write("<table  border=1 cellspacing=1 cellpadding=1 width=100% style='float:down;margin-top:-5px;'>\n");
    	out.write("<tr>\n");
        out.write("<td width=10%  align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>S.No</b></FONT></td>\n");
        out.write("<td width=20% align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Case ID</b></FONT></td>\n");
        out.write("<td width=30% align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Case Description</b></FONT></td>\n");
        out.write("<td width=10% align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Status</b></FONT></td>\n");
        out.write("<td width=20% align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Run Start Time</b></FONT></td>\n");
        out.write("<td width=20% align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Run End Time</b></FONT></td>\n");

        out.write("</tr>\n");
        out.close();
	    }catch(Exception e){
		      System.err.println("Error: " + e.getMessage());
	    }finally{
	    	
		    fstream=null;
		    out=null;
	    }
	}
    
    public static void endSuite(){
    	 FileWriter fstream =null;
 		BufferedWriter out =null;
 		
 	    try{
 	    fstream = new FileWriter(indexResultFilename,true);
 	  	out = new BufferedWriter(fstream);
        out.write("</table></div>\n");
        out.close();
 	    }catch(Exception e){
		      System.err.println("Error: " + e.getMessage());
	    }finally{
	    	
		    fstream=null;
		    out=null;
	    }

    }
	
	public static void addTestCase(String testcasename,String testCaseStartTime,String testCaseEndTime,String status,String testcasedes){
		newTest=true;
		FileWriter fstream=null;
		BufferedWriter out=null;
		
		String RUN_DATE = TestUtil.now(CONFIG.getProperty("dateformat")).toString();
		
		try {
			newTest=true;
			// build the keywords page
		   if(status.equalsIgnoreCase("Skipped") || status.equalsIgnoreCase("Skip")){
			   
		   }else{
			    datarepeat=testrepeat-1;
				File f = new File(currentDir+"//"+currentSuiteName+"_"+testscenario+"_"+testcasename.replaceAll(" ", "_")+"---Data-"+datarepeat+".html");
				f.createNewFile();
				fstream = new FileWriter(currentDir+"//"+currentSuiteName+"_"+testscenario+"_"+testcasename.replaceAll(" ", "_")+"---Data-"+datarepeat+".html");
				out = new BufferedWriter(fstream);
				out.write("<html>");
				out.write("<head>");
				out.write("<title>");
				out.write(testcasename + " Detailed Reports");
				out.write("</title>");
				out.write("</head><script type='text/javascript'>function myFunction(){var divResult=document.getElementById('gridReportPar');");
				out.write("var divResultSum=document.getElementById('gridReport');divResult.appendChild(divResultSum);}</script>");
				out.write("<body onload='myFunction()'>");
			
			out.write("<h4 align=center> <FONT COLOR=660000 FACE=Arial SIZE=4.5><b><u>"+testcasename+" Detailed Report :</u></b></FONT></h4>");
			//out.write("<marquee behavior=scroll direction=right scrollamount=3><span title='Anil Yadav Avula And Morarji Desai'><FONT COLOR=000000 FACE=AriaL SIZE=4><b>Developed By MI-ops QA Team</b></span></FONT></marquee>"); 
		 	out.write("<div id='gridReportPar' style='margin-top:15px;'></div>"); 
			out.write("<table  border=1 cellspacing=1    cellpadding=1 width=100% style='margin-top: 20px;'>");
		 	 out.write("<tr> ");
		 	    out.write("<td align=left width=5%  align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>S.No</b></FONT></td>");
		        out.write("<td align=left width=5%  align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Test Step ID</b></FONT></td>");
		        out.write("<td align=left width=5%  align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Use Case ID</b></FONT></td>");
		        out.write("<td align=left width=5%  align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>MF/AF ID</b></FONT></td>");
		        out.write("<td align=left width=30% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Description</b></FONT></td>");
		        out.write("<td align=left width=15% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Keyword</b></FONT></td>");
		        out.write("<td align=left width=10% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Result</b></FONT></td>");
		 		out.write("<td align=left width=5% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Screen Shot</b></FONT></td>");
		 	 out.write("</tr>");
		 	 if(description!=null){
		 		 for(int i=0;i<description.size();i++){
		 			 out.write("<tr> ");
		 			 out.write("<td align=left width=5%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>"+(i+1)+"</b></FONT></td>");
		 			// out.write("<td align=left width=5%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>TS"+(i+1)+"</b></FONT></td>");
		 			 out.write("<td align=left width=5%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>"+stepid.get(i)+"</b></FONT></td>");
		 			out.write("<td align=left width=5%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>"+usecaseid.get(i)+"</b></FONT></td>");
		 			out.write("<td align=left width=5%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>"+maflowid.get(i)+"</b></FONT></td>");
		 			 out.write("<td align=left width=5%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>"+description.get(i)+"</b></FONT></td>");
		 			 out.write("<td align=left width=5%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>"+keyword.get(i)+"</b></FONT></td>");
		 			if(teststatus.get(i).startsWith("Pass"))
		 			{
		 			     out.write("<td width=5% align= left  bgcolor=#BCE954><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+teststatus.get(i)+"</b></FONT></td>\n");
		 			     steppasscount=steppasscount+1;
		 			}
		 			else if(teststatus.get(i).startsWith("Fail"))
		 			{
		 			  	 out.write("<td width=5% align= left  bgcolor=Red><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+teststatus.get(i)+"</b></FONT></td>\n");
		 			  	 stepfailcount=stepfailcount+1;
		 			}
		 			else if(teststatus.get(i).startsWith("Skip"))
		 			{
		 			  	 out.write("<td width=5% align= left  bgcolor=yellow><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+teststatus.get(i)+"</b></FONT></td>\n");
		 			  	 stepskipcount=stepskipcount+1;
		 			}
		 			 
		 			if(screenShotPath.get(i)=="Not Applicable")
		 				out.write("<td align=left width=5%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>Not Applicable</b></FONT></td>");
		 			else if(screenShotPath.get(i)=="Mode is Off")
		 				out.write("<td align=left width=5%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>Mode is Off</b></FONT></td>");
		 			else if(screenShotPath.get(i)==null)
		 				out.write("<td align=left width=5%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>&nbsp;</b></FONT></td>");
		 			else
		 				out.write("<td align=left width=5%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b><a href="+CONFIG.getProperty("screenshotlink")+screenShotPath.get(i)+" target=_blank>Screen Shot</a></b></FONT></td>");
		 			 out.write("</tr>");
		 			
		 	 }
		 	 }
		 	out.write("</table>");
		 	     out.write("<div id='gridReport'><table  border=1 cellspacing=1 cellpadding=1 align='center'>\n");
		 	     
		 	    out.write("<tr>\n");
                out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Browser Name&Version</b></FONT></td>\n");
                out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+browsername+" "+browserversion+"</b></FONT></td>\n");
                out.write("</tr>\n");
                
                out.write("<tr>\n");
                out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run Date</b></FONT></td>\n");
                out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+RUN_DATE+"</b></FONT></td>\n");
                out.write("</tr>\n");
                
			     out.write("<tr>\n");
			           out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Total No.Of Steps</b></FONT></td>\n");
			           out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+teststepcount+"</b></FONT></td>\n");
			     out.write("</tr>\n");
		         
			     out.write("<tr>\n");
			           out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Total No.Of Steps Passed</b></FONT></td>\n");
			           out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+steppasscount+"</b></FONT></td>\n");
			           steppasscount=0;
			     out.write("</tr>\n");
			     
			     out.write("<tr>\n");
		           out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Total No.Of Steps Failed</b></FONT></td>\n");
		           out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+stepfailcount+"</b></FONT></td>\n");
		           stepfailcount=0;
		         out.write("</tr>\n");
		         
		         out.write("<tr>\n");
		           out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Total No.Of Steps Skipped</b></FONT></td>\n");
		           out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+stepskipcount+"</b></FONT></td>\n");
		           stepskipcount=0;
		         out.write("</tr>\n");
		     
			     out.write("</table></div>\n");
			
		 	 
		 	 
		 	 out.close();
			
			
		   }
			
			fstream = new FileWriter(indexResultFilename,true);
			out = new BufferedWriter(fstream);
			
			 fstream = new FileWriter(indexResultFilename,true);
			 out = new BufferedWriter(fstream);
			// out.newLine();
			
			 out.write("<tr>\n");
			 //System.out.println(currentSuitePath);
		     out.write("<td width=10% align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+scriptNumber+"</b></FONT></td>\n");
		     if(status.equalsIgnoreCase("Skipped") || status.equalsIgnoreCase("Skip"))
		     {
		    	 out.write("<td width=20% align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+testcasename+"</b></FONT></td>\n");
		     }
		     else
		     {
		    	 //out.write("<td width=20% align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b><a href=file:///"+currentDir+"//"+currentSuiteName+"_"+testscenario+"_"+testcasename.replaceAll(" ", "_")+"---Data-"+datarepeat+".html>"+testcasename+"</a></b></FONT></td>\n");
		    	 out.write("<td width=20% align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b><a href="+currentSuiteName+"_"+testscenario+"_"+testcasename.replaceAll(" ", "_")+"---Data-"+datarepeat+".html>"+testcasename+"</a></b></FONT></td>\n");
		    	 
		     }
		     out.write("<td width=20% align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+testcasedes+"</b></FONT></td>\n");
		     if(status.startsWith("Pass"))
		     {
		     out.write("<td width=10% align= left  bgcolor=#BCE954><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+status+"</b></FONT></td>\n");
		     }
		     else if(status.startsWith("Fail"))
		     {
		    	 out.write("<td width=10% align= left  bgcolor=Red><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+status+"</b></FONT></td>\n");
		     }
		     else if(status.equalsIgnoreCase("Skipped") || status.equalsIgnoreCase("Skip"))
		     {
			     out.write("<td width=10% align= left  bgcolor=yellow><FONT COLOR=153E7E FACE=Arial SIZE=2><b>"+status+"</b></FONT></td>\n");
		     }
		     out.write("<td width=20% align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+testCaseStartTime+"</b></FONT></td>\n");
		     out.write("<td width=20% align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+testCaseEndTime+"</b></FONT></td>\n");

		     out.write("</tr>\n");
		     
		     scriptNumber++;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		stepid=new ArrayList<String>();
		usecaseid=new ArrayList<String>();
		maflowid=new ArrayList<String>();
		description= new ArrayList<String>();
		keyword= new ArrayList<String>();
		teststatus= new ArrayList<String>();
		screenShotPath = new ArrayList<String>();
		newTest=false;
	}

	public static void addKeyword(String step,String ucid, String mafid,String desc,String key,String stat,String path){
		
		
			
		stepid.add(step);
		usecaseid.add(ucid);
		maflowid.add(mafid);
		description.add(desc);
		keyword.add(key);
		teststatus.add(stat);
		screenShotPath.add(path);
		
}

	public static void updateEndTime(String endTime) {
		StringBuffer buf = new StringBuffer();
		try{
		    // Open the file that is the first 
		    // command line parameter
		    FileInputStream fstream = new FileInputStream(indexResultFilename);
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		        BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strLine;
		    
		    
		    
		    //Read File Line By Line
		    
		    while ((strLine = br.readLine()) != null)   {
		    	
		     if(strLine.indexOf("END_TIME") !=-1){
		    	 strLine=strLine.replace("END_TIME", endTime);
		     }
		     buf.append(strLine);
		     
		     
		    }
		  //Close the input stream
		    in.close();
		    System.out.println(buf);
		    FileOutputStream fos=new FileOutputStream(indexResultFilename);
			 DataOutputStream   output = new DataOutputStream (fos);	 
	    	 output.writeBytes(buf.toString());
	    	 fos.close();
		    
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
	}
	
	public static void osdetails(){
		  FileWriter fstream =null;
			BufferedWriter out =null;
		    try {
				fstream = new FileWriter(indexResultFilename,true);
		  	out = new BufferedWriter(fstream);
		  	 out.write("<div id='browserDiv'> <h4> <FONT COLOR=660000 FACE=Arial SIZE=4.5><b><u>OS and Browser Details :</u></b></FONT></h4>\n");
		  	out.write("<table  border=1 cellspacing=1 cellpadding=1 style='float:down;margin-top:-5px;'>\n");
	         out.write("<tr>\n");
	            out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Operating System</b></FONT></td>\n");
	            out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+os+"</b></FONT></td>\n");
           out.write("</tr>\n");
     
           out.write("<tr>\n");
                out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Browser Name&Version</b></FONT></td>\n");
                out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+browsername+" "+browserversion+"</b></FONT></td>\n");
           out.write("</tr>\n");

		     out.write("</table></div>\n");
		     //Close the output stream
			    out.close();
			    }catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			    }finally{
			    	
				    fstream=null;
				    out=null;
			    }
		
	}
}