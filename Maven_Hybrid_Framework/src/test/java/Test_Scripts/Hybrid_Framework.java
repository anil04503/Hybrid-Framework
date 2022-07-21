package Test_Scripts;


import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Utilities.ReportUtil;
import Utilities.TestUtil;
import Utilities.Xls_Reader;


public class Hybrid_Framework {
	public static WebDriver dr=null;
	public static Properties CONFIG;
	public static Properties OR;
	public static Properties Validation;
	public static Xls_Reader suitefile;
	public static Xls_Reader testscenariosheet;
	public static String starttime=null;
	public static String testscenario;
	public static String testcase;
	public static String keyword;
	public static String object;
	public static String currentTSID;
	public static String testcase_description;
	public static String stepDescription;
	public static String screenshotmode;
	public static String locatortype;
	public static String proceedOnFail;
	public static String testStatus;
	public static String data_column_name;
	public static String decission_steps;
	public static String os;
	public static String browsername;
	public static String browserversion;
	public static String usecase;
	public static String MAflow;
	public static int columnrow;
	public static int totalsets;
	public static int  testrepeat;
	public static int testcasecount=0;
	public static int passcount=0;
	public static int failcount=0;
	public static int skipcount=0;
	public static int steppasscount=0;
	public static int stepfailcount=0;
	public static int stepskipcount=0;
	public static int teststepcount=0;
	public static String fileName=null;
	public static Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
	public static String timestamp = new SimpleDateFormat("dd_MM_yyyy__hh_mm_ss").format(new Date());

	
	
	
	@BeforeSuite
	public static void startTesting() throws Exception{
		CONFIG = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\Configuration\\Config.properties");
		CONFIG.load(fs);
		OR = new Properties();
		fs = new FileInputStream(System.getProperty("user.dir")+CONFIG.getProperty("or_file"));
		OR.load(fs);
		Validation = new Properties();
		fs = new FileInputStream(System.getProperty("user.dir")+CONFIG.getProperty("validation_file"));
		Validation.load(fs);
		PropertyConfigurator.configure(System.getProperty("user.dir")+CONFIG.getProperty("log4j_properties"));
		suitefile=new Xls_Reader(System.getProperty("user.dir")+CONFIG.getProperty("datapath")+CONFIG.getProperty("suitefile"));
		ReportUtil.startTesting(System.getProperty("user.dir")+CONFIG.getProperty("suitereportpath"), 
                TestUtil.now(CONFIG.getProperty("timeformat")),CONFIG.getProperty("environment"),
                CONFIG.getProperty("release"),CONFIG.getProperty("project"));	
	}
	
	@Test
	public void Keywords() throws InterruptedException {
		ReportUtil.startSuite(CONFIG.getProperty("testsuite"));
		for(int tsid=2;tsid<=suitefile.getRowCount(CONFIG.getProperty("suitesheet"));tsid++)
		{
			testscenario=suitefile.getCellData(CONFIG.getProperty("suitesheet"),CONFIG.getProperty("testscenarioid"),tsid);
			if(suitefile.getCellData(CONFIG.getProperty("suitesheet"),CONFIG.getProperty("runmode"),tsid).equalsIgnoreCase(CONFIG.getProperty("runmode_yes")))
					{
					  APPLICATION_LOGS.debug("Executing the Test Scenario "+ testscenario);
				      testscenariosheet=new Xls_Reader(System.getProperty("user.dir")+CONFIG.getProperty("datapath")+testscenario+".xlsx");
				      testcasecount=testcasecount+testscenariosheet.getRowCount(CONFIG.getProperty("testcasesheet"))-1;
				      for(int testcaseid=2;testcaseid<=testscenariosheet.getRowCount(CONFIG.getProperty("testcasesheet"));testcaseid++)
				      {
				    	  testcase=testscenariosheet.getCellData(CONFIG.getProperty("testcasesheet"),CONFIG.getProperty("testcaseid"),testcaseid);
				    	  testcase_description=testscenariosheet.getCellData(CONFIG.getProperty("testcasesheet"),CONFIG.getProperty("testcase_description"),testcaseid);
				    	  if(testscenariosheet.getCellData(CONFIG.getProperty("testcasesheet"),CONFIG.getProperty("runmode"),testcaseid).equalsIgnoreCase(CONFIG.getProperty("runmode_yes")))
				    	  {
				    		  if(testscenariosheet.isSheetExist(testcase))
				    		  {
				    			totalsets=testscenariosheet.getRowCount(testcase);
				  				for( testrepeat=2; testrepeat<=totalsets;testrepeat++){
				  					if(testscenariosheet.getCellData(testcase,CONFIG.getProperty("runmode"),testrepeat).equalsIgnoreCase(CONFIG.getProperty("runmode_yes")))
				  					{
									starttime=TestUtil.now(CONFIG.getProperty("timeformat"));
									APPLICATION_LOGS.debug("Executing the Test Case "+ testcase);
									for(int tstepid=2;tstepid<=testscenariosheet.getRowCount(CONFIG.getProperty("teststepsheet"));tstepid++){
										if(testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("testcaseid"), tstepid).equals(testcase))
										{
											teststepcount=teststepcount+1;
											if(testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("runmode"), tstepid).equalsIgnoreCase(CONFIG.getProperty("runmode_yes"))||
													testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("runmode"), tstepid).equalsIgnoreCase(CONFIG.getProperty("runmode_blank"))||
													testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("runmode"), tstepid).equalsIgnoreCase(CONFIG.getProperty("runmode_decission")))
											{
										keyword=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("keyword"), tstepid);
										object=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("object"), tstepid);
										currentTSID=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("teststepid"), tstepid);
										stepDescription=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("teststep_description"), tstepid);
										screenshotmode=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("screenshotmode"), tstepid);
										locatortype=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("locatortype"), tstepid);
										proceedOnFail=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("proceedonfail"), tstepid);
										data_column_name=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("data"), tstepid);
										decission_steps=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("dsteps"), tstepid);
										usecase=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("usecase"), tstepid);
										MAflow=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("maflow"), tstepid);
										if(!testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("review/waitmode"), tstepid).equals(""))
										{
											String wt=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("review/waitmode"), tstepid);
											String waittime=wt+"000";
											int w=Integer.parseInt(waittime);
											Thread.sleep(w);
											APPLICATION_LOGS.debug("The review wait mode time has been set to "+wt+"sec");
										}
										try{
										Method method= Keywords.class.getMethod(keyword);
										String result = (String)method.invoke(method);
										APPLICATION_LOGS.debug("**************************** The Result Of Execution Of The Test Step "+currentTSID+" is --- "+result);
										if(screenshotmode.equalsIgnoreCase(CONFIG.getProperty("screenshotmode_off"))||screenshotmode.equalsIgnoreCase(CONFIG.getProperty("screenshotmode_blank")))
										{
											fileName="Mode is Off";
										}
										else
										{
											if(screenshotmode.equalsIgnoreCase(CONFIG.getProperty("screenshotmode_on")))
										{
										fileName=CONFIG.getProperty("project")+"-"+testscenario+"-"+testcase+"-"+currentTSID+"-"+timestamp+"-"+browsername+".jpeg";
										TestUtil.takeScreenShot(System.getProperty(("user.dir"))+CONFIG.getProperty("screenshotpath")+fileName);
										}
											else
											{
												fileName=null;
											}
										}
											if(result.startsWith(CONFIG.getProperty("result_fail")))
											{
												testStatus=result;
												fileName=CONFIG.getProperty("project")+"-"+testscenario+"-"+testcase+"-"+currentTSID+"-"+timestamp+"-"+browsername+".jpeg";
												TestUtil.takeScreenShot(System.getProperty(("user.dir"))+CONFIG.getProperty("screenshotpath")+fileName);
												if(proceedOnFail.equalsIgnoreCase(CONFIG.getProperty("proceedonfail_no"))){
													ReportUtil.addKeyword(currentTSID, usecase, MAflow, stepDescription, keyword, result, fileName);
													break;
												}
												else
												{
													if(testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("runmode"), tstepid).equalsIgnoreCase(CONFIG.getProperty("runmode_decission")))
													{
														ReportUtil.addKeyword(currentTSID, usecase, MAflow, stepDescription, keyword, result+" [D - Fail] ", fileName);
														int d=Integer.parseInt(decission_steps);
														int a=tstepid+d;
														teststepcount=teststepcount+d;
														for(int i=tstepid+1;i<=a;i++)
														{
															testscenariosheet.setCellData(CONFIG.getProperty("teststepsheet"), CONFIG.getProperty("testcaseid"), i, "D - Skip");
															String skipresult=CONFIG.getProperty("result_skip");
															keyword=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("keyword"), i);
															object=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("object"), i);
															currentTSID=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("teststepid"), i);
															stepDescription=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("teststep_description"), i);
															usecase=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("usecase"), tstepid);
															MAflow=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("maflow"), tstepid);
															APPLICATION_LOGS.debug("Skipping the Test Step "+ currentTSID);
															APPLICATION_LOGS.debug("**************************** The Result Of Execution Of The Test Step "+currentTSID+" is --- "+skipresult);
																fileName="Not Applicable";
																ReportUtil.addKeyword(currentTSID, usecase, MAflow, stepDescription, keyword, skipresult, fileName);
														}
														tstepid=a;
														int s=tstepid+1;
														APPLICATION_LOGS.debug("The control has been shifted to step " +s+ ". Since you declared the " +CONFIG.getProperty("dsteps")+ " as " +d);
													}
													else
													{
														ReportUtil.addKeyword(currentTSID, usecase, MAflow, stepDescription, keyword, result, fileName);
													}
												}
											
											}
											else
											{
												ReportUtil.addKeyword(currentTSID, usecase, MAflow, stepDescription, keyword, result, fileName);
											}
										TestUtil.osandbrowserdetails();
										
										}
										catch(Throwable t){
											APPLICATION_LOGS.debug("Error came");
										}
										}
											else
											{
												String skipresult=CONFIG.getProperty("result_skip");
												keyword=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("keyword"), tstepid);
												object=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("object"), tstepid);
												currentTSID=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("teststepid"), tstepid);
												stepDescription=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("teststep_description"), tstepid);
												usecase=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("usecase"), tstepid);
												MAflow=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("maflow"), tstepid);
												APPLICATION_LOGS.debug("Skipping the Test Step "+ currentTSID);
												APPLICATION_LOGS.debug("**************************** The Result Of Execution Of The Test Step "+currentTSID+" is --- "+skipresult);
													fileName="Not Applicable";
													ReportUtil.addKeyword(currentTSID, usecase, MAflow, stepDescription, keyword, skipresult, fileName);
											}
										}
									}
									if(testStatus == null){
										testStatus=CONFIG.getProperty("result_pass");
									}
									APPLICATION_LOGS.debug("**************************** The Result Of Execution Of The Test Case "+testcase+" is --- "+testStatus);
									ReportUtil.addTestCase(testcase, 
															starttime, 
															TestUtil.now(CONFIG.getProperty("timeformat")),
															testStatus, testcase_description);
									if(testStatus.startsWith(CONFIG.getProperty("result_fail")))
									{
										failcount=failcount+1;
									}
									if(testStatus.startsWith(CONFIG.getProperty("result_pass")))
									{
										passcount=passcount+1;
									}
									testStatus=null;
				  				}
				  					teststepcount=0;
				  				}
				    		  }
				    		  else
				    		  {
				    			  starttime=TestUtil.now(CONFIG.getProperty("timeformat"));
									APPLICATION_LOGS.debug("Executing the Test Case "+ testcase);
									testrepeat=1;
									for(int tstepid=2;tstepid<=testscenariosheet.getRowCount(CONFIG.getProperty("teststepsheet"));tstepid++){
										if(testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("testcaseid"), tstepid).equals(testcase))
										{
											teststepcount=teststepcount+1;
											if(testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("runmode"), tstepid).equalsIgnoreCase(CONFIG.getProperty("runmode_yes"))||
													testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("runmode"), tstepid).equalsIgnoreCase(CONFIG.getProperty("runmode_blank"))||
													testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("runmode"), tstepid).equalsIgnoreCase(CONFIG.getProperty("runmode_decission")))
											{
										keyword=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("keyword"), tstepid);
										object=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("object"), tstepid);
										currentTSID=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("teststepid"), tstepid);
										stepDescription=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("teststep_description"), tstepid);
										screenshotmode=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("screenshotmode"), tstepid);
										locatortype=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("locatortype"), tstepid);
										proceedOnFail=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("proceedonfail"), tstepid);
										data_column_name=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("data"), tstepid);
										decission_steps=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("dsteps"), tstepid);
										usecase=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("usecase"), tstepid);
										MAflow=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("maflow"), tstepid);
										if(!testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("review/waitmode"), tstepid).equals(""))
										{
											String wt=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("review/waitmode"), tstepid);
											String waittime=wt+"000";
											int w=Integer.parseInt(waittime);
											Thread.sleep(w);
											APPLICATION_LOGS.debug("The review wait mode time has been set to "+wt+"sec");
										}
										try{
											Method method= Keywords.class.getMethod(keyword);
											String result = (String)method.invoke(method);
											APPLICATION_LOGS.debug("**************************** The Result Of Execution Of The Test Step "+currentTSID+" is --- "+result);
											if(screenshotmode.equalsIgnoreCase(CONFIG.getProperty("screenshotmode_off"))||screenshotmode.equalsIgnoreCase(CONFIG.getProperty("screenshotmode_blank")))
											{
												fileName="Mode is Off";
											}
											else
											{
												if(screenshotmode.equalsIgnoreCase(CONFIG.getProperty("screenshotmode_on")))
											{
											fileName=CONFIG.getProperty("project")+"-"+testscenario+"-"+testcase+"-"+currentTSID+"-"+timestamp+"-"+browsername+".jpeg";
											TestUtil.takeScreenShot(System.getProperty(("user.dir"))+CONFIG.getProperty("screenshotpath")+fileName);
											}
												else
												{
													fileName=null;
												}
											}
												if(result.startsWith(CONFIG.getProperty("result_fail")))
												{
													testStatus=result;
													fileName=CONFIG.getProperty("project")+"-"+testscenario+"-"+testcase+"-"+currentTSID+"-"+timestamp+"-"+browsername+".jpeg";
													TestUtil.takeScreenShot(System.getProperty(("user.dir"))+CONFIG.getProperty("screenshotpath")+fileName);
													if(proceedOnFail.equalsIgnoreCase(CONFIG.getProperty("proceedonfail_no"))){
														ReportUtil.addKeyword(currentTSID, usecase, MAflow, stepDescription, keyword, result, fileName);
														break;
													}
													else
													{
														if(testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("runmode"), tstepid).equalsIgnoreCase(CONFIG.getProperty("runmode_decission")))
														{
															ReportUtil.addKeyword(currentTSID, usecase, MAflow, stepDescription, keyword, result+" [D - Fail] ", fileName);
															int d=Integer.parseInt(decission_steps);
															int a=tstepid+d;
															teststepcount=teststepcount+d;
															for(int i=tstepid+1;i<=a;i++)
															{
																testscenariosheet.setCellData(CONFIG.getProperty("teststepsheet"), CONFIG.getProperty("testcaseid"), i, "D - Skip");
																String skipresult=CONFIG.getProperty("result_skip");
																keyword=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("keyword"), i);
																object=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("object"), i);
																currentTSID=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("teststepid"), i);
																stepDescription=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("teststep_description"), i);
																usecase=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("usecase"), tstepid);
																MAflow=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("maflow"), tstepid);
																APPLICATION_LOGS.debug("Skipping the Test Step "+ currentTSID);
																APPLICATION_LOGS.debug("**************************** The Result Of Execution Of The Test Step "+currentTSID+" is --- "+skipresult);
																	fileName="Not Applicable";
																	ReportUtil.addKeyword(currentTSID, usecase, MAflow, stepDescription, keyword, skipresult, fileName);
															}
															tstepid=a;
															int s=tstepid+1;
															APPLICATION_LOGS.debug("The control has been shifted to step " +s+ ". Since you declared the " +CONFIG.getProperty("dsteps")+ " as " +d);
														}
														else
														{
															ReportUtil.addKeyword(currentTSID, usecase, MAflow, stepDescription, keyword, result, fileName);
														}
													}
												
												}
												else
												{
													ReportUtil.addKeyword(currentTSID, usecase, MAflow, stepDescription, keyword, result, fileName);
												}
											TestUtil.osandbrowserdetails();
											
											}
											catch(Throwable t){
												APPLICATION_LOGS.debug("Error came");
											}
											}
												else
												{
													String skipresult=CONFIG.getProperty("result_skip");
													keyword=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("keyword"), tstepid);
													object=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("object"), tstepid);
													currentTSID=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("teststepid"), tstepid);
													stepDescription=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("teststep_description"), tstepid);
													usecase=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("usecase"), tstepid);
													MAflow=testscenariosheet.getCellData(CONFIG.getProperty("teststepsheet"),CONFIG.getProperty("maflow"), tstepid);
													APPLICATION_LOGS.debug("Skipping the Test Step "+ currentTSID);
													APPLICATION_LOGS.debug("**************************** The Result Of Execution Of The Test Step "+currentTSID+" is --- "+skipresult);
														fileName="Not Applicable";
														ReportUtil.addKeyword(currentTSID, usecase, MAflow, stepDescription, keyword, skipresult, fileName);
												}
											}
										}
										if(testStatus == null){
											testStatus="Pass";
										}
										APPLICATION_LOGS.debug("**************************** The Result Of Execution Of The Test Case "+testcase+" is --- "+testStatus);
										ReportUtil.addTestCase(testcase, 
																starttime, 
																TestUtil.now(CONFIG.getProperty("timeformat")),
																testStatus, testcase_description);
										if(testStatus.startsWith(CONFIG.getProperty("result_fail")))
										{
											failcount=failcount+1;
										}
										if(testStatus.startsWith(CONFIG.getProperty("result_pass")))
										{
											passcount=passcount+1;
										}
										testStatus=null;
				    		  }
				      }
				    	  else{
								APPLICATION_LOGS.debug("Skipping the Test Case "+ testcase);
								testStatus=CONFIG.getProperty("result_skip");
								skipcount=skipcount+1;
								APPLICATION_LOGS.debug("**************************** The Result Of Execution Of The Test Case "+testcase+" is --- "+testStatus);
								ReportUtil.addTestCase(testcase, 
														TestUtil.now(CONFIG.getProperty("timeformat")), 
														TestUtil.now(CONFIG.getProperty("timeformat")),
														testStatus, testcase_description);
								testStatus=null;
							}
				    	  teststepcount=0;
					}
		}
			testStatus=null;
			
	}
		ReportUtil.endSuite();
	}
	@AfterSuite
	public static void endScript(){
		ReportUtil.updateEndTime(TestUtil.now(CONFIG.getProperty("timeformat")));
		ReportUtil.summaryreport();
		ReportUtil.osdetails();
		TestUtil.zip(System.getProperty("user.dir")+CONFIG.getProperty("zippath"));
	}
	

}
