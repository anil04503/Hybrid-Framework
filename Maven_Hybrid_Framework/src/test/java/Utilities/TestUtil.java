package Utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;



import Test_Scripts.Hybrid_Framework;

public class TestUtil extends Hybrid_Framework{

	
	public static String now(String dateFormat) {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    return sdf.format(cal.getTime());

	}
	
	public static void osandbrowserdetails() {
		os = System.getProperty("os.name").toLowerCase();
		Capabilities cap=((RemoteWebDriver)dr).getCapabilities();
		browsername=cap.getBrowserName();
		//browserversion=cap.getVersion();
		browserversion=cap.getBrowserVersion();
	}
	
	public static void takeScreenShot(String filePath) {
		File scrFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		try {
			//FileUtils.copyFile(scrFile, new File(filePath));
			FileHandler.copy(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void zip(String filepath){
	 	try
	 	{
	 		File inFolder=new File(filepath);
	 		File outFolder=new File("Reports.zip");
	 		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
	 		BufferedInputStream in = null;
	 		byte[] data  = new byte[1000];
	 		String files[] = inFolder.list();
	 		for (int i=0; i<files.length; i++)
	 		{
	 			in = new BufferedInputStream(new FileInputStream
	 			(inFolder.getPath() + "/" + files[i]), 1000);  
	 			out.putNextEntry(new ZipEntry(files[i])); 
	 			int count;
	 			while((count = in.read(data,0,1000)) != -1)
	 			{
	 				out.write(data, 0, count);
	 			}
	 			out.closeEntry();
  }
  out.flush();
  out.close();
	 	
}
  catch(Exception e)
  {
	  e.printStackTrace();
  } 
 }	
}
