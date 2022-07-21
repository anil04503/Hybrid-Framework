package Test_Scripts;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Keywords extends Hybrid_Framework{
	public static int getxpathcount;
	public static String browser;
	public static String gettext;
	public static String gettitle;
	public static String getvalue;
	public static String getalert;
	public static String getconfirmation;
	public static String currentdate;
	public static String futuredate;
	public static String previousdate;
	public static Select select=null;
	public static Alert alert=null;
	public static String winHandleBefore;
	public static WebElement webe;
	public static WebDriverWait wait;
	public static Wait<WebDriver> waits;
	public static Actions action;
	public static String defaultoptionselected;
	public static List<WebElement> list;
	
	public static String openbrowser(){
	APPLICATION_LOGS.debug("Executing the openbrowser keyword");
	String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
	browser=data;
	try{
		if(data.equalsIgnoreCase("firefox"))
		{
			APPLICATION_LOGS.debug("The firefox browser is opening");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+CONFIG.getProperty("firefoxdriverpath"));
			dr= new FirefoxDriver();
		}
		if(data.equalsIgnoreCase("ie"))
		{
			APPLICATION_LOGS.debug("The internet explorer browser is opening");
			//DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
	        //caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+CONFIG.getProperty("iedriverpath"));
			dr= new InternetExplorerDriver();
		}
		if(data.equalsIgnoreCase("edge"))
		{
			APPLICATION_LOGS.debug("The edge browser is opening");
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+CONFIG.getProperty("edgedriverpath"));
			dr= new EdgeDriver();
		}
		if(data.equalsIgnoreCase("chrome"))
		{
			APPLICATION_LOGS.debug("The google chrome browser is opening");
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("user-data-dir="+CONFIG.getProperty("chromearguments")+"");
			//options.addArguments("--test-type");
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+CONFIG.getProperty("chromedriverpath"));
			dr = new ChromeDriver(options);
		}
		if(data.equalsIgnoreCase("safari"))
		{
			APPLICATION_LOGS.debug("The safari browser is opening");
			dr=new SafariDriver();
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while opening the "+data+" browser "+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";	
		}
	
	public static String openurl(){
	APPLICATION_LOGS.debug("Executing the openurl Keyword");
	String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
	try{
			dr.get(data);
			APPLICATION_LOGS.debug("The URL " +data+ " has been opened");
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while opening the URL "+data+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String refresh(){
	APPLICATION_LOGS.debug("Executing the refresh Keyword");
	try{
			dr.navigate().refresh();
			APPLICATION_LOGS.debug("The page has been refreshed");
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while refreshing the page"+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String windowmax(){
	APPLICATION_LOGS.debug("Executing the windowmax Keyword");
	try{
			dr.manage().window().maximize();
			APPLICATION_LOGS.debug("The window has been maximized");
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while maximizing the window "+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String implicitwait(){
	APPLICATION_LOGS.debug("Executing the implicitwait Keyword");
	String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
	try{
			int tm=Integer.parseInt(data);
			//dr.manage().timeouts().implicitlyWait(tm, TimeUnit.SECONDS);
			dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(tm));
			APPLICATION_LOGS.debug("The " +data+ " seconds has been added for the implicit wait");
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while implicitly waiting the window "+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String explicitwait(){
	APPLICATION_LOGS.debug("Executing the explicitwait Keyword");
	String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
	try{
			int tm=Integer.parseInt(data);
			//wait = new WebDriverWait(dr,tm);
			wait = new WebDriverWait(dr,Duration.ofSeconds(tm));
			APPLICATION_LOGS.debug("The " +data+ " seconds has been added for the explicit wait");
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while getting the explicit wait time "+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String fluentwait(){
	APPLICATION_LOGS.debug("Executing the fluentwait Keyword");
	String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
	try{
			int tm=Integer.parseInt(data);
			waits = new FluentWait<WebDriver>(dr).withTimeout(Duration.ofSeconds(tm)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
			APPLICATION_LOGS.debug("The " +data+ " seconds has been added for the fluent wait");
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while getting the fluent wait time "+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String explicitwaitvisibile(){
	APPLICATION_LOGS.debug("Executing the explicitwaitvisibile Keyword");
	try{
			if(locatortype.equalsIgnoreCase("id"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
				APPLICATION_LOGS.debug("explicitly waited for the element "+ OR.getProperty(object));
			}
			if(locatortype.equalsIgnoreCase("class"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
				APPLICATION_LOGS.debug("explicitly waited for the element "+ OR.getProperty(object));
			}
			if(locatortype.equalsIgnoreCase("name"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
				APPLICATION_LOGS.debug("explicitly waited for the element "+ OR.getProperty(object));
			}
			if(locatortype.equalsIgnoreCase("css"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
				APPLICATION_LOGS.debug("explicitly waited for the element "+ OR.getProperty(object));
			}
			if(locatortype.equalsIgnoreCase("xpath"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
				APPLICATION_LOGS.debug("explicitly waited for the element "+ OR.getProperty(object));
			}
			if(locatortype.equalsIgnoreCase("link"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
				APPLICATION_LOGS.debug("explicitly waited for the element "+ OR.getProperty(object));
			}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while explicitly waiting for the element " +OR.getProperty(object)+ " to visible "+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String explicitwaitclickable(){
		APPLICATION_LOGS.debug("Executing the explicitwaitclickable Keyword");
		try{
				if(locatortype.equalsIgnoreCase("id"))
				{
					wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty(object))));
					APPLICATION_LOGS.debug("explicitly waited for the element "+ OR.getProperty(object));
				}
				if(locatortype.equalsIgnoreCase("class"))
				{
					wait.until(ExpectedConditions.elementToBeClickable(By.className(OR.getProperty(object))));
					APPLICATION_LOGS.debug("explicitly waited for the element "+ OR.getProperty(object));
				}
				if(locatortype.equalsIgnoreCase("name"))
				{
					wait.until(ExpectedConditions.elementToBeClickable(By.name(OR.getProperty(object))));
					APPLICATION_LOGS.debug("explicitly waited for the element "+ OR.getProperty(object));
				}
				if(locatortype.equalsIgnoreCase("css"))
				{
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(OR.getProperty(object))));
					APPLICATION_LOGS.debug("explicitly waited for the element "+ OR.getProperty(object));
				}
				if(locatortype.equalsIgnoreCase("xpath"))
				{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
					APPLICATION_LOGS.debug("explicitly waited for the element "+ OR.getProperty(object));
				}
				if(locatortype.equalsIgnoreCase("link"))
				{
					wait.until(ExpectedConditions.elementToBeClickable(By.linkText(OR.getProperty(object))));
					APPLICATION_LOGS.debug("explicitly waited for the element "+ OR.getProperty(object));
				}
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while explicitly waiting for the element " +OR.getProperty(object)+ " to clickable "+ t.getMessage());
				return "Fail - "+t.getMessage();
			}
			return "Pass";
			}
	
	public static String explicitwaitalert(){
		APPLICATION_LOGS.debug("Executing the explicitwaitalert Keyword");
		try{
			wait.until(ExpectedConditions.alertIsPresent());
			getalert();
			APPLICATION_LOGS.debug("Explicitly waited for the alert "+getalert);
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while explicitly waiting for the alert to present "+ t.getMessage());
				return "Fail - "+t.getMessage();
			}
			return "Pass";
			}
	
	public static String enterdata(){
	APPLICATION_LOGS.debug("Executing the enterdata Keyword");
	String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			dr.findElement(By.id(OR.getProperty(object))).sendKeys(data);
			APPLICATION_LOGS.debug("The " +data+ " has been entered in the input field of the element "+ OR.getProperty(object));
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			dr.findElement(By.className(OR.getProperty(object))).sendKeys(data);
			APPLICATION_LOGS.debug("The " +data+ " has been entered in the input field of the element "+ OR.getProperty(object));
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			dr.findElement(By.name(OR.getProperty(object))).sendKeys(data);
			APPLICATION_LOGS.debug("The " +data+ " has been entered in the input field of the element "+ OR.getProperty(object));
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			dr.findElement(By.cssSelector(OR.getProperty(object))).sendKeys(data);
			APPLICATION_LOGS.debug("The " +data+ " has been entered in the input field of the element "+ OR.getProperty(object));
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			dr.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
			APPLICATION_LOGS.debug("The " +data+ " has been entered in the input field of the element "+ OR.getProperty(object));
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while entering the " +data+ " in the input field of the element " +OR.getProperty(object)+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String click(){
	APPLICATION_LOGS.debug("Executing the click Keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			dr.findElement(By.id(OR.getProperty(object))).click();
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been clicked");
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.elementToBeClickable(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			dr.findElement(By.className(OR.getProperty(object))).click();
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been clicked");
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.elementToBeClickable(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			dr.findElement(By.name(OR.getProperty(object))).click();
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been clicked");
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.elementToBeClickable(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			dr.findElement(By.cssSelector(OR.getProperty(object))).click();
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been clicked");
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			dr.findElement(By.xpath(OR.getProperty(object))).click();
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been clicked");
		}
		if(locatortype.equalsIgnoreCase("link"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(OR.getProperty(object))));
			waits.until(ExpectedConditions.elementToBeClickable(By.linkText(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.linkText(OR.getProperty(object))));
			dr.findElement(By.linkText(OR.getProperty(object))).click();
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been clicked");
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while clicking the element "+OR.getProperty(object)+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String Highlight(){
	APPLICATION_LOGS.debug("Executing the Highlight Keyword");
	JavascriptExecutor jsExecutor = (JavascriptExecutor) dr;
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{ 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')",dr.findElement(By.id(OR.getProperty(object))));  
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been highlighted");
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')",dr.findElement(By.className(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been highlighted");
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')",dr.findElement(By.name(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been highlighted");
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')",dr.findElement(By.cssSelector(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been highlighted");
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')",dr.findElement(By.xpath(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been highlighted");
		}
		if(locatortype.equalsIgnoreCase("link"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')",dr.findElement(By.linkText(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been highlighted");
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while highlighteds the element "+OR.getProperty(object)+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String unHighlight(){
	APPLICATION_LOGS.debug("Executing the unHighlight Keyword");
	JavascriptExecutor jsExecutor = (JavascriptExecutor) dr;
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{ 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border: ; background:')",dr.findElement(By.id(OR.getProperty(object))));  
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been unHighlighted");
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border: ; background:')",dr.findElement(By.className(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been unHighlighted");
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border: ; background:')",dr.findElement(By.name(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been unHighlighted");
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border: ; background:')",dr.findElement(By.cssSelector(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been unHighlighted");
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border: ; background:')",dr.findElement(By.xpath(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been unHighlighted");
		}
		if(locatortype.equalsIgnoreCase("link"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border: ; background:')",dr.findElement(By.linkText(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been unHighlighted");
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while unHighlighted the element "+OR.getProperty(object)+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String closebrowser(){
	APPLICATION_LOGS.debug("Executing the closebrowser Keyword");
	String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
	try{
		if(data.equalsIgnoreCase(CONFIG.getProperty("browserclose_yes")))
		{
			dr.close();
			APPLICATION_LOGS.debug("The browser has been closed");
		}
		else
		{
			APPLICATION_LOGS.debug("The browser has not been closed because the mode is set to " +data);
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while closing the browser "+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String closewindow(){
	APPLICATION_LOGS.debug("Executing the closewindow Keyword");
	try{
			dr.close();
			APPLICATION_LOGS.debug("The window has been closed");
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while closing the window "+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String closeallbrowsers(){
	APPLICATION_LOGS.debug("Executing the closeallwindows Keyword");
	try{
			dr.quit();
			APPLICATION_LOGS.debug("All the opened browsers has been closed");
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while closing all the windows "+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String clear(){
	APPLICATION_LOGS.debug("Executing the clear Keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			dr.findElement(By.id(OR.getProperty(object))).clear();
			APPLICATION_LOGS.debug("The input field of the element " +OR.getProperty(object)+ " has been cleared");
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			dr.findElement(By.className(OR.getProperty(object))).clear();
			APPLICATION_LOGS.debug("The input field of the element " +OR.getProperty(object)+ " has been cleared");
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			dr.findElement(By.name(OR.getProperty(object))).clear();
			APPLICATION_LOGS.debug("The input field of the element " +OR.getProperty(object)+ " has been cleared");
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			dr.findElement(By.cssSelector(OR.getProperty(object))).clear();
			APPLICATION_LOGS.debug("The input field of the element " +OR.getProperty(object)+ " has been cleared");
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			dr.findElement(By.xpath(OR.getProperty(object))).clear();
			APPLICATION_LOGS.debug("The input field of the element " +OR.getProperty(object)+ " has been cleared");
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while clearing the input field of the element " +OR.getProperty(object)+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String gettext(){
	APPLICATION_LOGS.debug("Executing the gettext keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			gettext=dr.findElement(By.id(OR.getProperty(object))).getText();
			APPLICATION_LOGS.debug("The text of the element " +OR.getProperty(object)+ " is "+gettext);
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			gettext=dr.findElement(By.className(OR.getProperty(object))).getText();
			APPLICATION_LOGS.debug("The text of the element " +OR.getProperty(object)+ " is "+gettext);
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			gettext=dr.findElement(By.name(OR.getProperty(object))).getText();
			APPLICATION_LOGS.debug("The text of the element " +OR.getProperty(object)+ " is "+gettext);
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			gettext=dr.findElement(By.cssSelector(OR.getProperty(object))).getText();
			APPLICATION_LOGS.debug("The text of the element " +OR.getProperty(object)+ " is "+gettext);
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			gettext=dr.findElement(By.xpath(OR.getProperty(object))).getText();
			APPLICATION_LOGS.debug("The text of the element " +OR.getProperty(object)+ " is "+gettext);
		}
		if(locatortype.equalsIgnoreCase("link"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.linkText(OR.getProperty(object))));
			gettext=dr.findElement(By.linkText(OR.getProperty(object))).getText();
			APPLICATION_LOGS.debug("The text of the element " +OR.getProperty(object)+ " is "+gettext);
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while getting the text of the element "+OR.getProperty(object)+ t.getMessage());
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String gettitle(){
	APPLICATION_LOGS.debug("Executing the gettitle keyword");
	try{
			gettitle=dr.getTitle();
			APPLICATION_LOGS.debug("The title is " +gettitle);
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while getting the title "+ t.getMessage());
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String getvalue(){
	APPLICATION_LOGS.debug("Executing the getvalue keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			getvalue=dr.findElement(By.id(OR.getProperty(object))).getAttribute("value");
			APPLICATION_LOGS.debug("The value of the element " +OR.getProperty(object)+ " is " +getvalue);
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			getvalue=dr.findElement(By.className(OR.getProperty(object))).getAttribute("value");
			APPLICATION_LOGS.debug("The value of the element " +OR.getProperty(object)+ " is " +getvalue);
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			getvalue=dr.findElement(By.name(OR.getProperty(object))).getAttribute("value");
			APPLICATION_LOGS.debug("The value of the element " +OR.getProperty(object)+ " is " +getvalue);
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			getvalue=dr.findElement(By.cssSelector(OR.getProperty(object))).getAttribute("value");
			APPLICATION_LOGS.debug("The value of the element " +OR.getProperty(object)+ " is " +getvalue);
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			getvalue=dr.findElement(By.xpath(OR.getProperty(object))).getAttribute("value");
			APPLICATION_LOGS.debug("The value of the element " +OR.getProperty(object)+ " is " +getvalue);
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while getting the value of the element "+OR.getProperty(object)+ t.getMessage());
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String verifytext(){
	APPLICATION_LOGS.debug("Executing the verifytext keyword");
	String actual=gettext;
	String expected=Validation.getProperty(object);
	APPLICATION_LOGS.debug("The actual text is - "+actual);
	APPLICATION_LOGS.debug("The expected text is - "+expected);
	try{
			Assert.assertEquals(actual , expected);
			APPLICATION_LOGS.debug("The actual text " +actual+ " is same as " +expected+ " expected text");
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while verifying the text "+Validation.getProperty(object)+ t.getMessage());
			APPLICATION_LOGS.debug("The actual text is - "+actual);
			APPLICATION_LOGS.debug("The expected text is - "+expected);
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String verifytextnotequals(){
	APPLICATION_LOGS.debug("Executing the verifytextnotequals keyword");
	String actual=gettext;
	String expected=Validation.getProperty(object);
	APPLICATION_LOGS.debug("The actual text is - "+actual);
	APPLICATION_LOGS.debug("The expected text is - "+expected);
	try{
			Assert.assertNotEquals(actual , expected);
			APPLICATION_LOGS.debug("The actual text " +actual+ " is not same as " +expected+ " expected text");
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while verifying the text "+Validation.getProperty(object)+ " The actual text is same as expected text");
			APPLICATION_LOGS.debug("The actual text is - "+actual);
			APPLICATION_LOGS.debug("The expected text is - "+expected);
			return "Fail - The actual text is same as expected text"; 	
		}
		return "Pass";
		}
	
	public static String verifytitle(){
	APPLICATION_LOGS.debug("Executing the verifytitle keyword");
	String actual=gettitle;
    String expected=Validation.getProperty(object);
    APPLICATION_LOGS.debug("The actual title is - "+actual);
	APPLICATION_LOGS.debug("The expected title is - "+expected);
	try{
			Assert.assertEquals(actual , expected);
			APPLICATION_LOGS.debug("The actual title " +actual+ " is same as " +expected+ " expected title");
		}catch(Throwable t){
		APPLICATION_LOGS.debug("Error while verifying the title "+Validation.getProperty(object)+ t.getMessage());
		APPLICATION_LOGS.debug("The actual title is - "+actual);
		APPLICATION_LOGS.debug("The expected title is - "+expected);
		return "Fail - "+ t.getMessage(); 		
		}	
		return "Pass";
		}
	
	public static String verifytitlenotequals(){
		APPLICATION_LOGS.debug("Executing the verifytitlenotequals keyword");
		String actual=gettitle;
	    String expected=Validation.getProperty(object);
	    APPLICATION_LOGS.debug("The actual title is - "+actual);
		APPLICATION_LOGS.debug("The expected title is - "+expected);
		try{
				Assert.assertNotEquals(actual , expected);
				APPLICATION_LOGS.debug("The actual title " +actual+ " is not same as " +expected+ " expected title");
			}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while verifying the title "+Validation.getProperty(object)+ " The actual title is same as expected title");
			APPLICATION_LOGS.debug("The actual title is - "+actual);
			APPLICATION_LOGS.debug("The expected title is - "+expected);
			return "Fail - The actual title is same as expected title"; 		
			}	
			return "Pass";
			}
	
	public static String verifyvalue(){
	APPLICATION_LOGS.debug("Executing the verifyvalue keyword");
	String actual=getvalue;
    String expected=Validation.getProperty(object);
    APPLICATION_LOGS.debug("The actual value is - "+actual);
   	APPLICATION_LOGS.debug("The expected value is - "+expected);
	try{
			Assert.assertEquals(actual , expected);
			APPLICATION_LOGS.debug("The actual value " +actual+ " is same as " +expected+ " expected value");
		}catch(Throwable t){
		APPLICATION_LOGS.debug("Error while verifying the value "+Validation.getProperty(object)+ t.getMessage());
		APPLICATION_LOGS.debug("The actual value is - "+actual);
		APPLICATION_LOGS.debug("The expected value is - "+expected);
		return "Fail - "+ t.getMessage(); 		
		}	
		return "Pass";
		}
	
	public static String verifyvaluenotequals(){
	APPLICATION_LOGS.debug("Executing the verifyvaluenotequals keyword");
	String actual=getvalue;
    String expected=Validation.getProperty(object);
    APPLICATION_LOGS.debug("The actual value is - "+actual);
   	APPLICATION_LOGS.debug("The expected value is - "+expected);
	try{
			Assert.assertNotEquals(actual , expected);
			APPLICATION_LOGS.debug("The actual value " +actual+ " is not same as " +expected+ " expected value");
		}catch(Throwable t){
		APPLICATION_LOGS.debug("Error while verifying the value "+Validation.getProperty(object)+ " The actual value is same as expected value");
		APPLICATION_LOGS.debug("The actual value is - "+actual);
		APPLICATION_LOGS.debug("The expected value is - "+expected);
		return "Fail - The actual value is same as expected value"; 		
		}	
		return "Pass";
		}
	
	public static String select(){
	APPLICATION_LOGS.debug("Executing the select Keyword");
	try{
		if(locatortype.contains("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			select = new Select(dr.findElement(By.id(OR.getProperty(object))));
			APPLICATION_LOGS.debug("Selected the element " +OR.getProperty(object));
		}
		if(locatortype.contains("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			select = new Select(dr.findElement(By.className(OR.getProperty(object))));
			APPLICATION_LOGS.debug("Selected the element " +OR.getProperty(object));
		}
		if(locatortype.contains("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			select = new Select(dr.findElement(By.name(OR.getProperty(object))));
			APPLICATION_LOGS.debug("Selected the element " +OR.getProperty(object));
		}
		if(locatortype.contains("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			select = new Select(dr.findElement(By.cssSelector(OR.getProperty(object))));
			APPLICATION_LOGS.debug("Selected the element " +OR.getProperty(object));
		}
		if(locatortype.contains("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			select = new Select(dr.findElement(By.xpath(OR.getProperty(object))));
			APPLICATION_LOGS.debug("Selected the element " +OR.getProperty(object));
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while Selecting the element " +OR.getProperty(object)+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String selectelement(){
	APPLICATION_LOGS.debug("Executing the selectelement Keyword");
	String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
	try{
		if(locatortype.equalsIgnoreCase("byvisibletext"))
		{
			select.selectByVisibleText(data);
			APPLICATION_LOGS.debug("The label " +data+ " has been selected");
		}
		if(locatortype.equalsIgnoreCase("byvalue"))
		{
			select.selectByValue(data);
			APPLICATION_LOGS.debug("The label " +data+ " has been selected");
		}
		if(locatortype.equalsIgnoreCase("byindex"))
		{
			int index=Integer.parseInt(data);
			select.selectByIndex(index);
			APPLICATION_LOGS.debug("The label option " +index+ " has been selected");
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while Selecting the label " +data+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String deselectelement(){
	APPLICATION_LOGS.debug("Executing the deselectelement Keyword");
	String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
	try{
		if(locatortype.equalsIgnoreCase("byvisibletext"))
		{
			select.deselectByVisibleText(data);
			APPLICATION_LOGS.debug("The label " +data+ " has been deselected");
		}
		if(locatortype.equalsIgnoreCase("byvalue"))
		{
			select.deselectByValue(data);
			APPLICATION_LOGS.debug("The label " +data+ " has been deselected");
		}
		if(locatortype.equalsIgnoreCase("byindex"))
		{
			int index=Integer.parseInt(data);
			select.deselectByIndex(index);
			APPLICATION_LOGS.debug("The label option " +index+ " has been deselected");
		}
		if(locatortype.equalsIgnoreCase("all"))
		{
			select.deselectAll();
			APPLICATION_LOGS.debug("The label options has been deselected");
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while deselecting the label " +data+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String getalert(){
	APPLICATION_LOGS.debug("Executing the getalert keyword");
	try{
			wait.until(ExpectedConditions.alertIsPresent());
			waits.until(ExpectedConditions.alertIsPresent());
			alert=dr.switchTo().alert();
			getalert=alert.getText();
			APPLICATION_LOGS.debug("The alert present is " +getalert);
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while getting the alert "+ t.getMessage());
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String alertaccept(){
	APPLICATION_LOGS.debug("Executing the alertaccept keyword");
	try{
			alert.accept();
			APPLICATION_LOGS.debug("The " +"OK"+ " button of the alert " +getalert+ " has been clicked");
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while accepting the alert " +getalert+ t.getMessage());
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String alertdismiss(){
	APPLICATION_LOGS.debug("Executing the alertdismiss keyword");
	try{
			alert.dismiss();
			APPLICATION_LOGS.debug("The " +"CANCEL"+ " button of the alert " +getalert+ " has been clicked");
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while dismissing the alert " +getalert+ t.getMessage());
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String getcurrentwindow(){
	APPLICATION_LOGS.debug("Executing the getcurrentwindow keyword");
	try{
	    	winHandleBefore = dr.getWindowHandle();
	    	APPLICATION_LOGS.debug("The current window handler is "+winHandleBefore);
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while getting the current window handler "+ t.getMessage());
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String switchtochildwindow(){
	APPLICATION_LOGS.debug("Executing the switchtochildwindow keyword");
	try{
		if(browser.equalsIgnoreCase("ie")||browser.equalsIgnoreCase("Chrome"))
		{
			Thread.sleep(5000);
		}
			Set<String> s = dr.getWindowHandles();
			Iterator<String> ite = s.iterator();
			while(ite.hasNext())
			{
				String popupHandle=ite.next().toString();
				APPLICATION_LOGS.debug("The child window handler is "+popupHandle);
				if(!popupHandle.contains(winHandleBefore))
				{
					dr.switchTo().window(popupHandle);
					APPLICATION_LOGS.debug("The control has been moved to the child window handler "+popupHandle);
				}
			}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while switching to the child window "+ t.getMessage());
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String switchtoparentwindow(){
	APPLICATION_LOGS.debug("Executing the switchtoparentwindow keyword");
	try{
		if(browser.equalsIgnoreCase("ie")||browser.equalsIgnoreCase("chrome"))
		{
			Thread.sleep(5000);
		}
			dr.switchTo().window(winHandleBefore);
			APPLICATION_LOGS.debug("The control has been moved to the parent window handler "+winHandleBefore);
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while switching to the parent window "+winHandleBefore+ t.getMessage());
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String verifyalert(){
	APPLICATION_LOGS.debug("Executing the verifyalert keyword");
	String actual=getalert;
    String expected=Validation.getProperty(object);
    APPLICATION_LOGS.debug("The actual alert is - "+actual);
	APPLICATION_LOGS.debug("The expected alert is - "+expected);
	try{
			Assert.assertEquals(actual , expected);
			APPLICATION_LOGS.debug("The actual alert " +actual+ " is same as " +expected+ " expected alert");
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while verifying the alert "+Validation.getProperty(object)+ t.getMessage());
			APPLICATION_LOGS.debug("The actual alert is - "+actual);
			APPLICATION_LOGS.debug("The expected alert is - "+expected);
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String verifyalertnotequals(){
		APPLICATION_LOGS.debug("Executing the verifyalertnotequals keyword");
		String actual=getalert;
	    String expected=Validation.getProperty(object);
	    APPLICATION_LOGS.debug("The actual alert is - "+actual);
		APPLICATION_LOGS.debug("The expected alert is - "+expected);
		try{
				Assert.assertNotEquals(actual , expected);
				APPLICATION_LOGS.debug("The actual alert " +actual+ " is not same as " +expected+ " expected alert");
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while verifying the alert "+Validation.getProperty(object)+ " The actual alert is same as expected alert");
				APPLICATION_LOGS.debug("The actual alert is - "+actual);
				APPLICATION_LOGS.debug("The expected alert is - "+expected);
				return "Fail - The actual alert is same as expected alert"; 	
			}
			return "Pass";
			}
	
	public static String textcontains(){
	APPLICATION_LOGS.debug("Executing the textcontains keyword");
	String actual=gettext;
	String expected=Validation.getProperty(object);
	APPLICATION_LOGS.debug("The actual text is - "+actual);
	APPLICATION_LOGS.debug("The expected text to contain is - "+expected);
	try{
		if(actual.contains(expected))
		{
			APPLICATION_LOGS.debug("The actual text " +actual+ " contains the " +expected+ " expected text");
		}
		else
		{
			APPLICATION_LOGS.debug("Error while matching the text "+Validation.getProperty(object));
			APPLICATION_LOGS.debug("The actual text is - "+actual);
			APPLICATION_LOGS.debug("The expected text to contain is - "+expected);
			return "Fail - expected text to contain is "+"["+expected+"]"+" but found "+"["+actual+"]"; 
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while matching the text "+Validation.getProperty(object)+ t.getMessage());
			APPLICATION_LOGS.debug("The actual text is - "+actual);
			APPLICATION_LOGS.debug("The expected text to contain is - "+expected);
			return "Fail - "+ t.getMessage(); 
		}
		return "Pass";	
		}
	
	public static String textnotcontains(){
		APPLICATION_LOGS.debug("Executing the textnotcontains keyword");
		String actual=gettext;
		String expected=Validation.getProperty(object);
		APPLICATION_LOGS.debug("The actual text is - "+actual);
		APPLICATION_LOGS.debug("The expected text to not contain is - "+expected);
		try{
			if(actual.contains(expected))
			{
				APPLICATION_LOGS.debug("Error while matching the text "+Validation.getProperty(object));
				APPLICATION_LOGS.debug("The actual text is - "+actual);
				APPLICATION_LOGS.debug("The expected text to not contain is - "+expected);
				return "Fail - expected text not to contain is "+"["+expected+"]"+" but found "+"["+actual+"]";
			}
			else
			{
				APPLICATION_LOGS.debug("The actual text " +actual+ " not contains the " +expected+ " expected text");	 
			}
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while matching the text "+Validation.getProperty(object)+ t.getMessage());
				APPLICATION_LOGS.debug("The actual text is - "+actual);
				APPLICATION_LOGS.debug("The expected text to contain is - "+expected);
				return "Fail - "+ t.getMessage(); 
			}
			return "Pass";	
			}
	
	public static String alertcontains(){
	APPLICATION_LOGS.debug("Executing the alertcontains keyword");
	String actual=getalert;
	String expected=Validation.getProperty(object);
	APPLICATION_LOGS.debug("The actual alert is - "+actual);
	APPLICATION_LOGS.debug("The expected alert to contain is - "+expected);
	try{
		if(actual.contains(expected))
		{
			APPLICATION_LOGS.debug("The actual alert " +actual+ " contains the " +expected+ " expected alert");
		}
		else
		{
			APPLICATION_LOGS.debug("Error while matching the alert "+Validation.getProperty(object));
			APPLICATION_LOGS.debug("The actual alert is - "+actual);
			APPLICATION_LOGS.debug("The expected alert to contain is - "+expected);
			return "Fail - expected alert to contain is "+"["+expected+"]"+" but found "+"["+actual+"]"; 
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while matching the alert "+Validation.getProperty(object)+ t.getMessage());
			APPLICATION_LOGS.debug("The actual alert is - "+actual);
			APPLICATION_LOGS.debug("The expected alert to contain is - "+expected);
			return "Fail - "+ t.getMessage(); 
		}
		return "Pass";	
		}
	
	public static String alertnotcontains(){
	APPLICATION_LOGS.debug("Executing the alertnotcontains keyword");
	String actual=getalert;
	String expected=Validation.getProperty(object);
	APPLICATION_LOGS.debug("The actual alert is - "+actual);
	APPLICATION_LOGS.debug("The expected alert not to contain is - "+expected);
	try{
		if(actual.contains(expected))
		{
			APPLICATION_LOGS.debug("Error while matching the alert "+Validation.getProperty(object));
			APPLICATION_LOGS.debug("The actual alert is - "+actual);
			APPLICATION_LOGS.debug("The expected alert not to contain is - "+expected);
			return "Fail - expected alert not to contain is "+"["+expected+"]"+" but found "+"["+actual+"]"; 
		}
		else
		{
			APPLICATION_LOGS.debug("The actual alert " +actual+ " not contains the " +expected+ " expected alert");
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while matching the alert "+Validation.getProperty(object)+ t.getMessage());
			APPLICATION_LOGS.debug("The actual alert is - "+actual);
			APPLICATION_LOGS.debug("The expected alert not to contain is - "+expected);
			return "Fail - "+ t.getMessage(); 
		}
		return "Pass";	
		}
	
	public static String valuecontains(){
	APPLICATION_LOGS.debug("Executing the valuecontains keyword");
	String actual=getvalue;
	String expected=Validation.getProperty(object);
	APPLICATION_LOGS.debug("The actual value is - "+actual);
	APPLICATION_LOGS.debug("The expected value to contain is - "+expected);
	try{
		if(actual.contains(expected))
		{
			APPLICATION_LOGS.debug("The actual value " +actual+ " contains the " +expected+ " expected value");
		}
		else
		{
			APPLICATION_LOGS.debug("Error while matching the value "+Validation.getProperty(object));
			APPLICATION_LOGS.debug("The actual value is - "+actual);
			APPLICATION_LOGS.debug("The expected value to contain is - "+expected);
			return "Fail - expected value to contain is "+"["+expected+"]"+" but found "+"["+actual+"]"; 
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while matching the value "+Validation.getProperty(object)+ t.getMessage());
			APPLICATION_LOGS.debug("The actual value is - "+actual);
			APPLICATION_LOGS.debug("The expected value to contain is - "+expected);
			return "Fail - "+ t.getMessage(); 
		}
		return "Pass";	
		}
	
	public static String valuenotcontains(){
	APPLICATION_LOGS.debug("Executing the valuenotcontains keyword");
	String actual=getvalue;
	String expected=Validation.getProperty(object);
	APPLICATION_LOGS.debug("The actual value is - "+actual);
	APPLICATION_LOGS.debug("The expected value not to contain is - "+expected);
	try{
		if(actual.contains(expected))
		{
			APPLICATION_LOGS.debug("Error while matching the value "+Validation.getProperty(object));
			APPLICATION_LOGS.debug("The actual value is - "+actual);
			APPLICATION_LOGS.debug("The expected value not to contain is - "+expected);
			return "Fail - expected value not to contain is "+"["+expected+"]"+" but found "+"["+actual+"]"; 
		}
		else
		{
			APPLICATION_LOGS.debug("The actual value " +actual+ " not contains the " +expected+ " expected value");
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while matching the value "+Validation.getProperty(object)+ t.getMessage());
			APPLICATION_LOGS.debug("The actual value is - "+actual);
			APPLICATION_LOGS.debug("The expected value not to contain is - "+expected);
			return "Fail - "+ t.getMessage(); 
		}
		return "Pass";	
		}
	
	public static String titlecontains(){
	APPLICATION_LOGS.debug("Executing the titlecontains keyword");
	String actual=gettitle;
	String expected=Validation.getProperty(object);
	APPLICATION_LOGS.debug("The actual title is - "+actual);
	APPLICATION_LOGS.debug("The expected title to contain is - "+expected);
	try{
		if(actual.contains(expected))
		{
			APPLICATION_LOGS.debug("The actual title " +actual+ " contains the " +expected+ " expected title");
		}
		else
		{
			APPLICATION_LOGS.debug("Error while matching the title "+Validation.getProperty(object));
			APPLICATION_LOGS.debug("The actual title is - "+actual);
			APPLICATION_LOGS.debug("The expected title to contain is - "+expected);
			return "Fail - expected title is "+"["+expected+"]"+" but found "+"["+actual+"]"; 
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while matching the title "+Validation.getProperty(object)+ t.getMessage());
			APPLICATION_LOGS.debug("The actual title is - "+actual);
			APPLICATION_LOGS.debug("The expected title to contain is - "+expected);
			return "Fail - "+ t.getMessage(); 
		}
		return "Pass";	
		}
	
	public static String titlenotcontains(){
		APPLICATION_LOGS.debug("Executing the titlenotcontains keyword");
		String actual=gettitle;
		String expected=Validation.getProperty(object);
		APPLICATION_LOGS.debug("The actual title is - "+actual);
		APPLICATION_LOGS.debug("The expected title not to contain is - "+expected);
		try{
			if(actual.contains(expected))
			{
				APPLICATION_LOGS.debug("Error while matching the title "+Validation.getProperty(object));
				APPLICATION_LOGS.debug("The actual title is - "+actual);
				APPLICATION_LOGS.debug("The expected title not to contain is - "+expected);
				return "Fail - expected title not to contain is "+"["+expected+"]"+" but found "+"["+actual+"]"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The actual title " +actual+ " not contains the " +expected+ " expected title");
			}
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while matching the title "+Validation.getProperty(object)+ t.getMessage());
				APPLICATION_LOGS.debug("The actual title is - "+actual);
				APPLICATION_LOGS.debug("The expected title not to contain is - "+expected);
				return "Fail - "+ t.getMessage(); 
			}
			return "Pass";	
			}
	
	public static String getcurrentdate(){
	APPLICATION_LOGS.debug("Executing the getcurrentdate keyword");
	try{
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			currentdate=dateFormat.format(date);
			APPLICATION_LOGS.debug(currentdate);
			testscenariosheet.setCellData(testcase,"Currentdate",testrepeat,currentdate);
			APPLICATION_LOGS.debug("The current date saved in the excel sheet is   "+testscenariosheet.getCellData(testcase, "Currentdate", testrepeat));
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while getting the current date "+ t.getMessage());
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String getfuturedate(){
	APPLICATION_LOGS.debug("Executing the getfuturedate keyword");
	try{
			 String dateParts[] = currentdate.split("/");
			 String month  = dateParts[0];
			 String day  = dateParts[1];
			 String year = dateParts[2];
			 APPLICATION_LOGS.debug("\n"+month+"\n"+day+"\n"+year+"\n");
			 int m=Integer.parseInt(month);
			 int d=Integer.parseInt(day);
			 int y=Integer.parseInt(year);
			 APPLICATION_LOGS.debug("\n"+m+"\n"+d+"\n"+y+"\n");
			 if(m==12&&d>=30)
			 {
				d=01;
				m=01;
				y=y+1;
				APPLICATION_LOGS.debug("\n"+m+"\n"+d+"\n"+y+"\n");
			 }
			 if(m<12&&d>=30)
			 {
				d=01;
				m=m+1;
				APPLICATION_LOGS.debug("\n"+m+"\n"+d+"\n"+y+"\n");
			 }
			 if(d<=29)
			 {
				d=d+1;
				APPLICATION_LOGS.debug("\n"+m+"\n"+d+"\n"+y+"\n");
			 }
			 String mo=Integer.toString(m);
			 String da=Integer.toString(d);
			 String ye=Integer.toString(y);
			 if(m<10)
			 {
				mo=0+mo;
			 }
			 if(d<10)
			 {
				da=0+da;
			 }
			 APPLICATION_LOGS.debug("\n"+mo+"\n"+da+"\n"+ye+"\n");
			 futuredate=mo+"/"+da+"/"+ye;
			 APPLICATION_LOGS.debug(futuredate);
			 testscenariosheet.setCellData(testcase,"Futuredate",testrepeat,futuredate);
			 APPLICATION_LOGS.debug("The future date saved in the excel sheet is   "+testscenariosheet.getCellData(testcase, "Futuredate", testrepeat));
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while getting the future date "+ t.getMessage());
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String getpreviousdate(){
	APPLICATION_LOGS.debug("Executing the getpreviousdate keyword");
	try{
			 String dateParts[] = currentdate.split("/");
			 String month  = dateParts[0];
			 String day  = dateParts[1];
			 String year = dateParts[2];
			 APPLICATION_LOGS.debug("\n"+month+"\n"+day+"\n"+year+"\n");
			 int m=Integer.parseInt(month);
			 int d=Integer.parseInt(day);
			 int y=Integer.parseInt(year);
			 APPLICATION_LOGS.debug("\n"+m+"\n"+d+"\n"+y+"\n");
			 if(d<=31)
			 {
				d=d-01;
				APPLICATION_LOGS.debug("\n"+m+"\n"+d+"\n"+y+"\n");
			 }
			 if(m==01&&d==01)
			 {
				d=29;
				m=12;
				y=y-1;
				APPLICATION_LOGS.debug("\n"+m+"\n"+d+"\n"+y+"\n");
			 }
			 if(m>01&&d==01)
			 {
				d=29;
				m=m-01;
				APPLICATION_LOGS.debug("\n"+m+"\n"+d+"\n"+y+"\n");
			 }
			 String mo=Integer.toString(m);
			 String da=Integer.toString(d);
			 String ye=Integer.toString(y);
			 if(m<10)
			 {
				mo=0+mo;
			 }
			 if(d<10)
			 {
				da=0+da;
			 }
			 APPLICATION_LOGS.debug("\n"+mo+"\n"+da+"\n"+ye+"\n");
			 previousdate=mo+"/"+da+"/"+ye;
			 APPLICATION_LOGS.debug(previousdate);
			 testscenariosheet.setCellData(testcase,"Previousdate",testrepeat,previousdate);
			 APPLICATION_LOGS.debug("The previous date saved in the excel sheet is   "+testscenariosheet.getCellData(testcase, "Previousdate", testrepeat));
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while getting the previous date "+ t.getMessage());
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String isenabled(){
	APPLICATION_LOGS.debug("Executing the isenabled Keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			if(dr.findElement(By.id(OR.getProperty(object))).isEnabled())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is enabled");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not enabled");
				return "Fail - element is not enabled"; 
			}
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			if(dr.findElement(By.className(OR.getProperty(object))).isEnabled())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is enabled");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not enabled");
				return "Fail - element is not enabled"; 
			}
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			if(dr.findElement(By.name(OR.getProperty(object))).isEnabled())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is enabled");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not enabled");
				return "Fail - element is not enabled"; 
			}
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			if(dr.findElement(By.cssSelector(OR.getProperty(object))).isEnabled())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is enabled");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not enabled");
				return "Fail - element is not enabled"; 
			}
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			if(dr.findElement(By.xpath(OR.getProperty(object))).isEnabled())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is enabled");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not enabled");
				return "Fail - element is not enabled"; 
			}
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while checking the element " +OR.getProperty(object)+ " is enabled"+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String isnotenabled(){
	APPLICATION_LOGS.debug("Executing the isnotenabled Keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			if(dr.findElement(By.id(OR.getProperty(object))).isEnabled())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is enabled");
				return "Fail - element is enabled"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not enabled");
			}
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			if(dr.findElement(By.className(OR.getProperty(object))).isEnabled())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is enabled");
				return "Fail - element is enabled"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not enabled");
			}
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			if(dr.findElement(By.name(OR.getProperty(object))).isEnabled())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is enabled");
				return "Fail - element is enabled"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not enabled");
			}
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			if(dr.findElement(By.cssSelector(OR.getProperty(object))).isEnabled())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is enabled");
				return "Fail - element is enabled"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not enabled");
			}
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			if(dr.findElement(By.xpath(OR.getProperty(object))).isEnabled())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is enabled");
				return "Fail - element is enabled"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not enabled");
			}
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while checking the element " +OR.getProperty(object)+ " is not enabled"+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String isdisplayed(){
	APPLICATION_LOGS.debug("Executing the isdisplayed Keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			if(dr.findElement(By.id(OR.getProperty(object))).isDisplayed())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is displayed");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not displayed");
				return "Fail - element is not displayed"; 
			}
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			if(dr.findElement(By.className(OR.getProperty(object))).isDisplayed())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is displayed");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not displayed");
				return "Fail - element is not displayed"; 
			}
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			if(dr.findElement(By.name(OR.getProperty(object))).isDisplayed())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is displayed");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not displayed");
				return "Fail - element is not displayed"; 
			}
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			if(dr.findElement(By.cssSelector(OR.getProperty(object))).isDisplayed())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is displayed");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not displayed");
				return "Fail - element is not displayed"; 
			}
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			if(dr.findElement(By.xpath(OR.getProperty(object))).isDisplayed())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is displayed");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not displayed");
				return "Fail - element is not displayed"; 
			}
		}
		if(locatortype.equalsIgnoreCase("link"))
		{
			if(dr.findElement(By.linkText(OR.getProperty(object))).isDisplayed())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is displayed");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not displayed");
				return "Fail - element is not displayed"; 
			}
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while checking the element " +OR.getProperty(object)+ " is displayed"+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String isnotdisplayed(){
	APPLICATION_LOGS.debug("Executing the isnotdisplayed Keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			if(dr.findElement(By.id(OR.getProperty(object))).isDisplayed())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is displayed");
				return "Fail - element is displayed"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not displayed");
			}
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			if(dr.findElement(By.className(OR.getProperty(object))).isDisplayed())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is displayed");
				return "Fail - element is displayed"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not displayed");
			}
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			if(dr.findElement(By.name(OR.getProperty(object))).isDisplayed())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is displayed");
				return "Fail - element is displayed"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not displayed");
			}
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			if(dr.findElement(By.cssSelector(OR.getProperty(object))).isDisplayed())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is displayed");
				return "Fail - element is displayed"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not displayed");
			}
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			if(dr.findElement(By.xpath(OR.getProperty(object))).isDisplayed())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is displayed");
				return "Fail - element is displayed"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not displayed");
			}
		}
		if(locatortype.equalsIgnoreCase("link"))
		{
			if(dr.findElement(By.linkText(OR.getProperty(object))).isDisplayed())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is displayed");
				return "Fail - element is displayed";
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not displayed");
			}
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while checking the element " +OR.getProperty(object)+ " is not displayed"+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String isselected(){
	APPLICATION_LOGS.debug("Executing the isselected Keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			if(dr.findElement(By.id(OR.getProperty(object))).isSelected())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is selected");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not selected");
				return "Fail - element is not selected"; 
			}
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			if(dr.findElement(By.className(OR.getProperty(object))).isSelected())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is selected");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not selected");
				return "Fail - element is not selected"; 
			}
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			if(dr.findElement(By.name(OR.getProperty(object))).isSelected())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is selected");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not selected");
				return "Fail - element is not selected"; 
			}
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			if(dr.findElement(By.cssSelector(OR.getProperty(object))).isSelected())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is selected");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not selected");
				return "Fail - element is not selected"; 
			}
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			if(dr.findElement(By.xpath(OR.getProperty(object))).isSelected())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is selected");
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not selected");
				return "Fail - element is not selected"; 
			}
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while checking the element " +OR.getProperty(object)+ " is selected"+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String isnotselected(){
	APPLICATION_LOGS.debug("Executing the isnotselected Keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			if(dr.findElement(By.id(OR.getProperty(object))).isSelected())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is selected");
				return "Fail - element is selected"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not selected");
			}
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			if(dr.findElement(By.className(OR.getProperty(object))).isSelected())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is selected");
				return "Fail - element is selected"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not selected");
			}
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			if(dr.findElement(By.name(OR.getProperty(object))).isSelected())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is selected");
				return "Fail - element is selected"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not selected");
			}
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			if(dr.findElement(By.cssSelector(OR.getProperty(object))).isSelected())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is selected");
				return "Fail - element is selected"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not selected");
			}
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			if(dr.findElement(By.xpath(OR.getProperty(object))).isSelected())
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is selected");
				return "Fail - element is selected"; 
			}
			else
			{
				APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " is not selected");
			}
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while checking the element " +OR.getProperty(object)+ " is not selected"+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String elementfocus(){
	APPLICATION_LOGS.debug("Executing the elementfocus Keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been focused");
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been focused");
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been focused");
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been focused");
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been focused");
		}
		if(locatortype.equalsIgnoreCase("link"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.linkText(OR.getProperty(object))));
			APPLICATION_LOGS.debug("The element " +OR.getProperty(object)+ " has been focused");
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while focusing the element " +OR.getProperty(object)+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String scrolldown(){
	APPLICATION_LOGS.debug("Executing the scrolldown Keyword");
	String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
	try{
			JavascriptExecutor jse = (JavascriptExecutor)dr;
			jse.executeScript("scroll("+data+")");
			APPLICATION_LOGS.debug("The window has been scrolled down with the dimensions " +data);
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while scrolling down the window with the dimensions " +data+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String scrollup(){
		APPLICATION_LOGS.debug("Executing the scrollup Keyword");
		String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
		try{
				JavascriptExecutor jse = (JavascriptExecutor)dr;
				jse.executeScript("scroll("+data+")");
				APPLICATION_LOGS.debug("The window has been scrolled up with the dimensions " +data);
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while scrolling up the window with the dimensions " +data+ t.getMessage());
				return "Fail - "+t.getMessage();
			}
			return "Pass";
			}
	
	public static String getoptions(){
		APPLICATION_LOGS.debug("Executing the getoptions Keyword");
		try{
			list=select.getOptions();
			for(int i=0;i<=list.size()-1;i++)
			{
				APPLICATION_LOGS.debug("The option present is "+list.get(i).getText());
			}
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while getting the options from the dropdown list "+ t.getMessage());
				return "Fail - "+t.getMessage();
			}
			return "Pass";
			}
	
	public static String verifyoptionssize(){
		APPLICATION_LOGS.debug("Executing the verifyoptionssize Keyword");
		String data =Validation.getProperty(object);
		int j=Integer.parseInt(data);
		int actual = list.size();
		int expected = j;
		APPLICATION_LOGS.debug("The actual size of options is - "+actual);
		APPLICATION_LOGS.debug("The expected size of options is - "+expected);
		try{
			Assert.assertEquals(actual , expected);
			APPLICATION_LOGS.debug("The actual size " +actual+ " is same as " +expected+ " expected size");
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while verifying the options size from the dropdown list "+ t.getMessage());
				APPLICATION_LOGS.debug("The actual size of options is - "+actual);
				APPLICATION_LOGS.debug("The expected size of options is - "+expected);
				return "Fail - "+t.getMessage();
			}
			return "Pass";
			}
	
	public static String verifyoptionssizenotequals(){
		APPLICATION_LOGS.debug("Executing the verifyoptionssizenotequals Keyword");
		String data =Validation.getProperty(object);
		int j=Integer.parseInt(data);
		int actual = list.size();
		int expected = j;
		APPLICATION_LOGS.debug("The actual size of options is - "+actual);
		APPLICATION_LOGS.debug("The expected size of options is - "+expected);
		try{
			Assert.assertNotEquals(actual , expected);
			APPLICATION_LOGS.debug("The actual size " +actual+ " is not same as " +expected+ " expected size");
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while verifying the options size from the dropdown list ");
				APPLICATION_LOGS.debug("The actual size of options is - "+actual);
				APPLICATION_LOGS.debug("The expected size of options is - "+expected);
				return "Fail - The actual size of option is same as expected size of option";
			}
			return "Pass";
			}
	
	public static String verifyoption(){
		APPLICATION_LOGS.debug("Executing the verifyoption Keyword");
		int i;
		try{
			for(i=0;i<=list.size()-1;i++)
			{
				APPLICATION_LOGS.debug("The option present is "+list.get(i).getText());
				if(list.get(i).getText().equals(Validation.getProperty(object)))
				{
					APPLICATION_LOGS.debug("The option " +Validation.getProperty(object)+ " is present in the dropdown list");
					break;
				}
			}
			if(i==list.size())
			{
				if(list.get(i-1).getText().equals(Validation.getProperty(object)))
				{
					APPLICATION_LOGS.debug("The option " +Validation.getProperty(object)+ " is present in the dropdown list");
				}
				else
				{
					APPLICATION_LOGS.debug("The option " +Validation.getProperty(object)+ " is not present in the dropdown list");
					return "Fail - The option " +Validation.getProperty(object)+ " is not present";
				}
			}
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while checking the option " +Validation.getProperty(object)+ " from the dropdown list "+ t.getMessage());
				return "Fail - "+t.getMessage();
			}
			return "Pass";
			}
	
	public static String verifyoptionnotequals(){
		APPLICATION_LOGS.debug("Executing the verifyoptionnotequals Keyword");
		int i;
		try{
			for(i=0;i<=list.size()-1;i++)
			{
				APPLICATION_LOGS.debug("The option present is "+list.get(i).getText());
				if(list.get(i).getText().equals(Validation.getProperty(object)))
				{
					APPLICATION_LOGS.debug("The option " +Validation.getProperty(object)+ " is present in the dropdown list");
					return "Fail - The option " +Validation.getProperty(object)+ " is present";
				}
				else
				{
					APPLICATION_LOGS.debug("The option " +Validation.getProperty(object)+ " is not present in the "+i+" label of the dropdown list");
				}
			}
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while checking the option " +Validation.getProperty(object)+ " from the dropdown list "+ t.getMessage());
				return "Fail - "+t.getMessage();
			}
			return "Pass";
			}
	
	public static String getdefaultselected(){
		APPLICATION_LOGS.debug("Executing the getdefaultselected Keyword");
		try{
				webe=select.getFirstSelectedOption();
				defaultoptionselected=webe.getText();
				APPLICATION_LOGS.debug("The default selected option is "+defaultoptionselected);
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while getting the default selected option from the dropdown list "+ t.getMessage());
				return "Fail - "+t.getMessage();
			}
			return "Pass";
			}
	
	public static String verifydefaultselected(){
		APPLICATION_LOGS.debug("Executing the verifydefaultselected keyword");
		String actual=defaultoptionselected;
		String expected=Validation.getProperty(object);
		APPLICATION_LOGS.debug("The actual default option selected is - "+actual);
		APPLICATION_LOGS.debug("The expected default option selected is - "+expected);
		try{
				Assert.assertEquals(actual , expected);
				APPLICATION_LOGS.debug("The actual default option " +actual+ " is same as " +expected+ " expected default option");
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while verifying the text "+Validation.getProperty(object)+ t.getMessage());
				APPLICATION_LOGS.debug("The actual default option selected is - "+actual);
				APPLICATION_LOGS.debug("The expected default option selected is - "+expected);
				return "Fail - "+ t.getMessage(); 	
			}
			return "Pass";
			}
	
	public static String verifydefaultnotselected(){
		APPLICATION_LOGS.debug("Executing the verifydefaultnotselected keyword");
		String actual=defaultoptionselected;
		String expected=Validation.getProperty(object);
		APPLICATION_LOGS.debug("The actual default option selected is - "+actual);
		APPLICATION_LOGS.debug("The expected default option not selected is - "+expected);
		try{
				Assert.assertNotEquals(actual , expected);
				APPLICATION_LOGS.debug("The actual default option " +actual+ " is not same as " +expected+ " expected default option");
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while verifying the text "+Validation.getProperty(object)+ t.getMessage());
				APPLICATION_LOGS.debug("The actual default option selected is - "+actual);
				APPLICATION_LOGS.debug("The expected default option not selected is - "+expected);
				return "Fail - "+ t.getMessage(); 	
			}
			return "Pass";
			}
	
	public static String radiobuttons(){
		APPLICATION_LOGS.debug("Executing the radiobuttons Keyword");
		try{
			if(locatortype.contains("id"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
				waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
				((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
				list=dr.findElements(By.id(OR.getProperty(object)));
				APPLICATION_LOGS.debug("The radio button of the element " +OR.getProperty(object)+ " has been selected");
			}
			if(locatortype.contains("class"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
				waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
				((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
				list=dr.findElements(By.className(OR.getProperty(object)));
				APPLICATION_LOGS.debug("The radio button of the element " +OR.getProperty(object)+ " has been selected");
			}
			if(locatortype.contains("name"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
				waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
				((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
				list=dr.findElements(By.name(OR.getProperty(object)));
				APPLICATION_LOGS.debug("The radio button of the element " +OR.getProperty(object)+ " has been selected");
			}
			if(locatortype.contains("css"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
				waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
				((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
				list=dr.findElements(By.cssSelector(OR.getProperty(object)));
				APPLICATION_LOGS.debug("The radio button of the element " +OR.getProperty(object)+ " has been selected");
			}
			if(locatortype.contains("xpath"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
				waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
				((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
				list=dr.findElements(By.xpath(OR.getProperty(object)));
				APPLICATION_LOGS.debug("The radio button of the element " +OR.getProperty(object)+ " has been selected");
			}
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while selecting the radio button of the element " +OR.getProperty(object)+ t.getMessage());
				return "Fail - "+t.getMessage();
			}
			return "Pass";
			}
	
	public static String getradiobuttons(){
		APPLICATION_LOGS.debug("Executing the getradiobuttons Keyword");
		try{
			for(int i=0;i<=list.size()-1;i++)
			{
				APPLICATION_LOGS.debug("The radio button present is "+list.get(i).getAttribute("value"));
			}
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while getting the radio buttons "+ t.getMessage());
				return "Fail - "+t.getMessage();
			}
			return "Pass";
			}
	
	public static String verifyradiobuttonssize(){
		APPLICATION_LOGS.debug("Executing the verifyradiobuttonsize Keyword");
		String data =Validation.getProperty(object);
		int j=Integer.parseInt(data);
		int actual = list.size();
		int expected = j;
		APPLICATION_LOGS.debug("The actula radio button size is - "+actual);
		APPLICATION_LOGS.debug("The expected radio button size is - "+expected);
		try{
			Assert.assertEquals(actual , expected);
			APPLICATION_LOGS.debug("The actual size " +actual+ " is same as " +expected+ " expected size");
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while verifying the radiobuttons size "+ t.getMessage());
				APPLICATION_LOGS.debug("The actula radio button size is - "+actual);
				APPLICATION_LOGS.debug("The expected radio button size is - "+expected);
				return "Fail - "+t.getMessage();
			}
			return "Pass";
			}
	
	public static String verifyradiobuttonssizenotequals(){
		APPLICATION_LOGS.debug("Executing the verifyradiobuttonssizenotequals Keyword");
		String data =Validation.getProperty(object);
		int j=Integer.parseInt(data);
		int actual = list.size();
		int expected = j;
		APPLICATION_LOGS.debug("The actula radio button size is - "+actual);
		APPLICATION_LOGS.debug("The expected radio button size is - "+expected);
		try{
			Assert.assertNotEquals(actual , expected);
			APPLICATION_LOGS.debug("The actual size " +actual+ " is not same as " +expected+ " expected size");
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while verifying the radiobuttons size ");
				APPLICATION_LOGS.debug("The actula radio button size is - "+actual);
				APPLICATION_LOGS.debug("The expected radio button size is - "+expected);
				return "Fail - The actual size of radio button is same as expected size of radio button";
			}
			return "Pass";
			}
	
	public static String verifyradiobutton(){
		APPLICATION_LOGS.debug("Executing the verifyradiobutton Keyword");
		int i;
		try{
			for(i=0;i<=list.size()-1;i++)
			{
				APPLICATION_LOGS.debug("The radio button present is "+list.get(i).getAttribute("value"));
				if(list.get(i).getAttribute("value").equals(Validation.getProperty(object)))
				{
					APPLICATION_LOGS.debug("The radio button " +Validation.getProperty(object)+" is present in the radiobuttons list");
					break;
				}
			}
			if(i==list.size())
			{
				if(list.get(i-1).getAttribute("value").equals(Validation.getProperty(object)))
				{
					APPLICATION_LOGS.debug("The radio button " +Validation.getProperty(object)+" is present in the radiobuttons list");
				}
				else
				{
					APPLICATION_LOGS.debug("The radio button " +Validation.getProperty(object)+" is not present in the radiobuttons list");
					return "Fail - The radio button " +Validation.getProperty(object)+ " is not present";
				}
			}
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while verifying the radiobuttons "+ t.getMessage());
				return "Fail - "+t.getMessage();
			}
			return "Pass";
			}
	
	public static String verifyradiobuttonnotequals(){
		APPLICATION_LOGS.debug("Executing the verifyradiobuttonnotequals Keyword");
		int i;
		try{
			for(i=0;i<=list.size()-1;i++)
			{
				APPLICATION_LOGS.debug("The radio button present is "+list.get(i).getAttribute("value"));
				if(list.get(i).getAttribute("value").equals(Validation.getProperty(object)))
				{
					APPLICATION_LOGS.debug("The radio button " +Validation.getProperty(object)+" is present in the radiobuttons list");
					return "Fail - The radio button " +Validation.getProperty(object)+ " is present";
				}
				else
				{
					APPLICATION_LOGS.debug("The radio button " +Validation.getProperty(object)+" is not present in the radiobuttons list");
				}
			}
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while verifying the radiobuttons "+ t.getMessage());
				return "Fail - "+t.getMessage();
			}
			return "Pass";
			}
	
	public static String mouseover(){
	APPLICATION_LOGS.debug("Executing the mouseover Keyword");
	action = new Actions(dr);
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			webe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			webe = waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			action.moveToElement(webe).perform();
			APPLICATION_LOGS.debug("The mouse has been placed over the element " +OR.getProperty(object));
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			webe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			webe = waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			action.moveToElement(webe).perform();
			APPLICATION_LOGS.debug("The mouse has been placed over the element " +OR.getProperty(object));
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			webe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			webe = waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			action.moveToElement(webe).perform();
			APPLICATION_LOGS.debug("The mouse has been placed over the element " +OR.getProperty(object));
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			webe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			webe = waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			action.moveToElement(webe).perform();
			APPLICATION_LOGS.debug("The mouse has been placed over the element " +OR.getProperty(object));
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			webe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			webe = waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			action.moveToElement(webe).perform();
			APPLICATION_LOGS.debug("The mouse has been placed over the element " +OR.getProperty(object));
		}
		if(locatortype.equalsIgnoreCase("link"))
		{
			webe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			webe = waits.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.linkText(OR.getProperty(object))));
			action.moveToElement(webe).perform();
			APPLICATION_LOGS.debug("The mouse has been placed over the element " +OR.getProperty(object));
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while placing the mouse over the element " +OR.getProperty(object)+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String getxpathcount(){
	APPLICATION_LOGS.debug("Executing the getxpathcount Keyword");
	try{
		webe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
		webe = waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
		((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
		getxpathcount=dr.findElements(By.xpath(OR.getProperty(object))).size();
		APPLICATION_LOGS.debug("The xpath count of the element " +OR.getProperty(object)+" is "+getxpathcount);
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while getting the xpath count of the element " +OR.getProperty(object)+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String verifyxpathcount(){
		APPLICATION_LOGS.debug("Executing the verifyxpathcount Keyword");
		String data =Validation.getProperty(object);
		int j=Integer.parseInt(data);
		int actual = getxpathcount; 
		int expected = j;
		APPLICATION_LOGS.debug("The actula xpath count is - "+actual);
		APPLICATION_LOGS.debug("The expected xpath count is - "+expected);
		try{
			Assert.assertEquals(actual , expected);
			APPLICATION_LOGS.debug("The actual xpath count " +actual+ " is same as " +expected+ " xpath count");
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while verifying the xpath count "+ t.getMessage());
				APPLICATION_LOGS.debug("The actula xpath count is - "+actual);
				APPLICATION_LOGS.debug("The expected xpath count is - "+expected);
				return "Fail - "+t.getMessage();
			}
			return "Pass";
			}
	
	public static String verifyxpathcountnotequals(){
		APPLICATION_LOGS.debug("Executing the verifyxpathcountnotequals Keyword");
		String data =Validation.getProperty(object);
		int j=Integer.parseInt(data);
		int actual = getxpathcount; 
		int expected = j;
		APPLICATION_LOGS.debug("The actula xpath count is - "+actual);
		APPLICATION_LOGS.debug("The expected xpath count is - "+expected);
		try{
			Assert.assertNotEquals(actual , expected);
			APPLICATION_LOGS.debug("The actual xpath count " +actual+ " is not same as " +expected+ " xpath count");
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while verifying the xpath count ");
				APPLICATION_LOGS.debug("The actula xpath count is - "+actual);
				APPLICATION_LOGS.debug("The expected xpath count is - "+expected);
				return "Fail - The actual xpath count is same as expected xapth count";
			}
			return "Pass";
			}
	
	public static String verifyxpathcounttext(){
	APPLICATION_LOGS.debug("Executing the verifyxpathcounttext Keyword");
	String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
	int a=Integer.parseInt(data);
	int count=0;
	if(a==0)
	{
		count=getxpathcount-1;
	}
	if(a==2)
	{
		count=getxpathcount+1;
	}
	String str1=null;
	String actual=null;
	String expected=null;
	try{
		for(int i=a;i<=count;i++)
		{
			str1=OR.getProperty(object);
			str1=str1.replaceAll("fhdn:hidden", i+"");
			actual=dr.findElement(By.xpath(str1)).getText();
			expected=Validation.getProperty(object);
			APPLICATION_LOGS.debug("The text of the element " +str1+ " is "+actual);
			APPLICATION_LOGS.debug("The actual text is - "+actual);
			APPLICATION_LOGS.debug("The expected text is - "+expected);
			if(actual.equals(expected))
			{
				Assert.assertEquals(actual , expected);
				APPLICATION_LOGS.debug("The actual text " +actual+ " is same as " +expected+ " expected text");
				break;
			}
			if(i==count&&!actual.equals(expected))
			{
				APPLICATION_LOGS.debug("The expected text "+expected+" is not present in the list");
				return "Fail - "+expected+ " text is not present in the list"; 
			}
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while verifying the text "+str1+ t.getMessage());
			APPLICATION_LOGS.debug("The actual text is - "+actual);
			APPLICATION_LOGS.debug("The expected text is - "+expected);
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String verifyxpathcounttextnotequals(){
		APPLICATION_LOGS.debug("Executing the verifyxpathcounttextnotequals Keyword");
		String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
		int a=Integer.parseInt(data);
		int count=0;
		if(a==0)
		{
			count=getxpathcount-1;
		}
		if(a==2)
		{
			count=getxpathcount+1;
		}
		String str1=null;
		String actual=null;
		String expected=null;
		try{
			for(int i=a;i<=count;i++)
			{
				str1=OR.getProperty(object);
				str1=str1.replaceAll("fhdn:hidden", i+"");
				actual=dr.findElement(By.xpath(str1)).getText();
				expected=Validation.getProperty(object);
				APPLICATION_LOGS.debug("The text of the element " +str1+ " is "+actual);
				APPLICATION_LOGS.debug("The actual text is - "+actual);
				APPLICATION_LOGS.debug("The expected text is - "+expected);
				if(actual.equals(expected))
				{
					Assert.assertEquals(actual , expected);
					APPLICATION_LOGS.debug("The actual text " +actual+ " is same as " +expected+ " expected text");
					return "Fail - "+expected+ " text is present in the list"; 
				}
				else
				{
					APPLICATION_LOGS.debug("The actual text " +actual+ " is not same as " +expected+ " expected text");
				}
			}
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while verifying the text "+str1+ t.getMessage());
				APPLICATION_LOGS.debug("The actual text is - "+actual);
				APPLICATION_LOGS.debug("The expected text is - "+expected);
				return "Fail - "+ t.getMessage(); 	
			}
			return "Pass";
			}
	
	public static String verifyxpathcountvalue(){
	APPLICATION_LOGS.debug("Executing the verifyxpathcountvalue Keyword");
	String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
	int a=Integer.parseInt(data);
	int count=0;
	if(a==0)
	{
		count=getxpathcount-1;
	}
	if(a==2)
	{
		count=getxpathcount+1;
	}
	String str1=null;
	String actual=null;
	String expected=null;
	try{
		for(int i=a;i<=count;i++)
		{
			str1=OR.getProperty(object);
			str1=str1.replaceAll("fhdn:hidden", i+"");
			expected=Validation.getProperty(object);
			actual=dr.findElement(By.xpath(str1)).getAttribute("value");
			APPLICATION_LOGS.debug("The value of the element " +str1+ " is "+actual);
			APPLICATION_LOGS.debug("The actual value is - "+actual);
			APPLICATION_LOGS.debug("The expected value is - "+expected);
			if(actual.equals(expected))
			{
				Assert.assertEquals(actual , expected);
				APPLICATION_LOGS.debug("The actual value " +actual+ " is same as " +expected+ " expected text");
				break;
			}
			if(i==count&&!actual.equals(expected))
			{
				APPLICATION_LOGS.debug("The expected value "+expected+" is not present in the list");
				return "Fail - "+expected+ " value is not present in the list"; 
			}
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while verifying the value "+str1+ t.getMessage());
			APPLICATION_LOGS.debug("The actual value is - "+actual);
			APPLICATION_LOGS.debug("The expected value is - "+expected);
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String verifyxpathcountvaluenotequals(){
	APPLICATION_LOGS.debug("Executing the verifyxpathcountvaluenotequals Keyword");
	String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
	int a=Integer.parseInt(data);
	int count=0;
	if(a==0)
	{
		count=getxpathcount-1;
	}
	if(a==2)
	{
		count=getxpathcount+1;
	}
	String str1=null;
	String actual=null;
	String expected=null;
	try{
		for(int i=a;i<=count;i++)
		{
			str1=OR.getProperty(object);
			str1=str1.replaceAll("fhdn:hidden", i+"");
			expected=Validation.getProperty(object);
			actual=dr.findElement(By.xpath(str1)).getAttribute("value");
			APPLICATION_LOGS.debug("The value of the element " +str1+ " is "+actual);
			APPLICATION_LOGS.debug("The actual value is - "+actual);
			APPLICATION_LOGS.debug("The expected value is - "+expected);
			if(actual.equals(expected))
			{
				Assert.assertEquals(actual , expected);
				APPLICATION_LOGS.debug("The actual value " +actual+ " is same as " +expected+ " expected text");
				return "Fail - "+expected+ " value is not present in the list";
			}
			else
			{
				APPLICATION_LOGS.debug("The actual value " +actual+ " is not same as " +expected+ " expected text");
			}
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while verifying the value "+str1+ t.getMessage());
			APPLICATION_LOGS.debug("The actual value is - "+actual);
			APPLICATION_LOGS.debug("The expected value is - "+expected);
			return "Fail - "+ t.getMessage(); 	
		}
		return "Pass";
		}
	
	public static String clickxpathcounttext(){
		APPLICATION_LOGS.debug("Executing the clickxpathcounttext Keyword");
		String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
		int a=Integer.parseInt(data);
		int count=0;
		if(a==0)
		{
			count=getxpathcount-1;
		}
		if(a==2)
		{
			count=getxpathcount+1;
		}
		String objectparts[] = object.split(",");
		String object1  = objectparts[0];
		String object2  = objectparts[1];
		String str1=null;
		String str2=null;
		String str3=null;
		try{
			for(int i=a;i<=count;i++)
			{
				str1=OR.getProperty(object1);
				str1=str1.replaceAll("fhdn:hidden", i+"");
				str2=OR.getProperty(object2);
				str2=str2.replaceAll("fhdn:hidden", i+"");
				str3=dr.findElement(By.xpath(str1)).getText();
				APPLICATION_LOGS.debug("The text of the element " +str1+ " is "+str3);
				if(str3.equals(Validation.getProperty(object1)))
				{
					dr.findElement(By.xpath(str2)).click();
					APPLICATION_LOGS.debug("The element " +str2+ " has been clicked");
					break;
				}
				if(i==count&&!str3.equals(Validation.getProperty(object)))
				{
					APPLICATION_LOGS.debug("The expected text "+Validation.getProperty(object1)+" is not present in the list");
					return "Fail - "+Validation.getProperty(object1)+ " text is not present in the list"; 
				}
			}
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while clicking the element "+str2+ t.getMessage());
				return "Fail - "+ t.getMessage(); 	
			}
			return "Pass";
			}
	
	public static String clickxpathcountvalue(){
		APPLICATION_LOGS.debug("Executing the clickxpathcountvalue Keyword");
		String data =testscenariosheet.getCellData(testcase, data_column_name , testrepeat);
		int a=Integer.parseInt(data);
		int count=0;
		if(a==0)
		{
			count=getxpathcount-1;
		}
		if(a==2)
		{
			count=getxpathcount+1;
		}
		String objectparts[] = object.split(",");
		String object1  = objectparts[0];
		String object2  = objectparts[1];
		String str1=null;
		String str2=null;
		String str3=null;
		try{
			for(int i=a;i<=count;i++)
			{
				str1=OR.getProperty(object1);
				str1=str1.replaceAll("fhdn:hidden", i+"");
				str2=OR.getProperty(object2);
				str2=str2.replaceAll("fhdn:hidden", i+"");
				str3=dr.findElement(By.xpath(str1)).getAttribute("value");
				APPLICATION_LOGS.debug("The value of the element " +str1+ " is "+str3);
				if(str3.equals(Validation.getProperty(object1)))
				{
					dr.findElement(By.xpath(str2)).click();
					APPLICATION_LOGS.debug("The element " +str2+ " has been clicked");
					break;
				}
				if(i==count&&!str3.equals(Validation.getProperty(object)))
				{
					APPLICATION_LOGS.debug("The expected value "+Validation.getProperty(object1)+" is not present in the list");
					return "Fail - "+Validation.getProperty(object1)+ " value is not present in the list"; 
				}
			}
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while clicking the element "+str2+ t.getMessage());
				return "Fail - "+ t.getMessage(); 	
			}
			return "Pass";
			}
	
	public static String istextpresent(){
	APPLICATION_LOGS.debug("Executing the istextpresent Keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			if(dr.findElement(By.id(OR.getProperty(object))).getText().contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is present");
			}
			else
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is not present");
				return "Fail - The text "+Validation.getProperty(object)+" is not present";
			}
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			if(dr.findElement(By.className(OR.getProperty(object))).getText().contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is present");
			}
			else
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is not present");
				return "Fail - The text "+Validation.getProperty(object)+" is not present";
			}
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			if(dr.findElement(By.name(OR.getProperty(object))).getText().contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is present");
			}
			else
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is not present");
				return "Fail - The text "+Validation.getProperty(object)+" is not present";
			}
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			if(dr.findElement(By.cssSelector(OR.getProperty(object))).getText().contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is present");
			}
			else
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is not present");
				return "Fail - The text "+Validation.getProperty(object)+" is not present";
			}
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			if(dr.findElement(By.xpath(OR.getProperty(object))).getText().contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is present");
			}
			else
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is not present");
				return "Fail - The text "+Validation.getProperty(object)+" is not present";
			}
		}
		if(locatortype.equalsIgnoreCase("link"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.linkText(OR.getProperty(object))));
			if(dr.findElement(By.linkText(OR.getProperty(object))).getText().contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is present");
			}
			else
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is not present");
				return "Fail - The text "+Validation.getProperty(object)+" is not present";
			}
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while finding the text " +Validation.getProperty(object)+" in "+OR.getProperty(object)+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String istextnotpresent(){
	APPLICATION_LOGS.debug("Executing the istextnotpresent Keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			if(dr.findElement(By.id(OR.getProperty(object))).getText().contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is present");
				return "Fail - The text "+Validation.getProperty(object)+" is present";
			}
			else
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is not present");
			}
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			if(dr.findElement(By.className(OR.getProperty(object))).getText().contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is present");
				return "Fail - The text "+Validation.getProperty(object)+" is present";
			}
			else
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is not present");
			}
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			if(dr.findElement(By.name(OR.getProperty(object))).getText().contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is present");
				return "Fail - The text "+Validation.getProperty(object)+" is present";
			}
			else
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is not present");
			}
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			if(dr.findElement(By.cssSelector(OR.getProperty(object))).getText().contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is present");
				return "Fail - The text "+Validation.getProperty(object)+" is present";
			}
			else
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is not present");
			}
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			if(dr.findElement(By.xpath(OR.getProperty(object))).getText().contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is present");
				return "Fail - The text "+Validation.getProperty(object)+" is present";
			}
			else
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is not present");
			}
		}
		if(locatortype.equalsIgnoreCase("link"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.linkText(OR.getProperty(object))));
			if(dr.findElement(By.linkText(OR.getProperty(object))).getText().contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is present");
				return "Fail - The text "+Validation.getProperty(object)+" is present";
			}
			else
			{
				APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is not present");
			}
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while finding the text " +Validation.getProperty(object)+" in "+OR.getProperty(object)+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String isvaluepresent(){
	APPLICATION_LOGS.debug("Executing the isvaluepresent Keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			if(dr.findElement(By.id(OR.getProperty(object))).getAttribute("value").contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is present");
			}
			else
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is not present");
				return "Fail - The value "+Validation.getProperty(object)+" is not present";
			}
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			if(dr.findElement(By.className(OR.getProperty(object))).getAttribute("value").contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is present");
			}
			else
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is not present");
				return "Fail - The value "+Validation.getProperty(object)+" is not present";
			}
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			if(dr.findElement(By.name(OR.getProperty(object))).getAttribute("value").contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is present");
			}
			else
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is not present");
				return "Fail - The value "+Validation.getProperty(object)+" is not present";
			}
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			if(dr.findElement(By.cssSelector(OR.getProperty(object))).getAttribute("value").contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is present");
			}
			else
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is not present");
				return "Fail - The value "+Validation.getProperty(object)+" is not present";
			}
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			if(dr.findElement(By.xpath(OR.getProperty(object))).getAttribute("value").contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is present");
			}
			else
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is not present");
				return "Fail - The value "+Validation.getProperty(object)+" is not present";
			}
		}
		if(locatortype.equalsIgnoreCase("link"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.linkText(OR.getProperty(object))));
			if(dr.findElement(By.linkText(OR.getProperty(object))).getAttribute("value").contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is present");
			}
			else
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is not present");
				return "Fail - The value "+Validation.getProperty(object)+" is not present";
			}
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while finding the value " +Validation.getProperty(object)+" in "+OR.getProperty(object)+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String isvaluenotpresent(){
	APPLICATION_LOGS.debug("Executing the isvaluenotpresent Keyword");
	try{
		if(locatortype.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.id(OR.getProperty(object))));
			if(dr.findElement(By.id(OR.getProperty(object))).getAttribute("value").contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is present");
				return "Fail - The value "+Validation.getProperty(object)+" is present";
			}
			else
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is not present");
			}
		}
		if(locatortype.equalsIgnoreCase("class"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className(OR.getProperty(object))));
			if(dr.findElement(By.className(OR.getProperty(object))).getAttribute("value").contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is present");
				return "Fail - The value "+Validation.getProperty(object)+" is present";
			}
			else
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is not present");
			}
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.name(OR.getProperty(object))));
			if(dr.findElement(By.name(OR.getProperty(object))).getAttribute("value").contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is present");
				return "Fail - The value "+Validation.getProperty(object)+" is present";
			}
			else
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is not present");
			}
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.cssSelector(OR.getProperty(object))));
			if(dr.findElement(By.cssSelector(OR.getProperty(object))).getAttribute("value").contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is present");
				return "Fail - The value "+Validation.getProperty(object)+" is present";
			}
			else
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is not present");
			}
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.xpath(OR.getProperty(object))));
			if(dr.findElement(By.xpath(OR.getProperty(object))).getAttribute("value").contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is present");
				return "Fail - The value "+Validation.getProperty(object)+" is present";
			}
			else
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is not present");
			}
		}
		if(locatortype.equalsIgnoreCase("link"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty(object))));
			((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.linkText(OR.getProperty(object))));
			if(dr.findElement(By.linkText(OR.getProperty(object))).getAttribute("value").contains(Validation.getProperty(object)))
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is present");
				return "Fail - The value "+Validation.getProperty(object)+" is present";
			}
			else
			{
				APPLICATION_LOGS.debug("The value "+Validation.getProperty(object)+" is not present");
			}
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while finding the value " +Validation.getProperty(object)+" in "+OR.getProperty(object)+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String ispagetextpresent(){
	APPLICATION_LOGS.debug("Executing the ispagetextpresent Keyword");
	try{
		if(dr.getPageSource().contains(Validation.getProperty(object)))
		{
			APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is present");
		}
		else
		{
			APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is not present");
			return "Fail - The text "+Validation.getProperty(object)+" is not present";
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while finding the text " +Validation.getProperty(object)+" in the page "+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}
	
	public static String ispagetextnotpresent(){
	APPLICATION_LOGS.debug("Executing the ispagetextnotpresent Keyword");
	try{
		if(dr.getPageSource().contains(Validation.getProperty(object)))
		{
			APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is present");
			return "Fail - The text "+Validation.getProperty(object)+" is present";
		}
		else
		{
			APPLICATION_LOGS.debug("The text "+Validation.getProperty(object)+" is not present");
		}
		}catch(Throwable t){
			APPLICATION_LOGS.debug("Error while finding the text " +Validation.getProperty(object)+" in the page "+ t.getMessage());
			return "Fail - "+t.getMessage();
		}
		return "Pass";
		}

	


	
	
	
	
/**********************************Application specific commands***********************************************/
	
	
}

