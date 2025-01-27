package Loginpage;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import lib.ExcelDataConfig;
import lib.TakeScreenshot;

public class Loginpage1 {
	WebDriver d1;
	public static int rownum1;
	String testresult;
	ExcelDataConfig config1 = new ExcelDataConfig("C:\\Users\\user\\eclipse-workspace\\MavenPracticeProjectFinal\\TestData\\LoginGujPharmcoun.xlsx");
    ExtentHtmlReporter Reporter = new ExtentHtmlReporter("./Report/learnautomation123.html");
	ExtentReports extent = new ExtentReports();	
    ExtentTest logger;
  
    
	@BeforeMethod
	public void OpenBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\IT DEPARTMENT\\selenium Traininf\\Drivers\\Drivers\\chromedriver.exe");
		d1 = new ChromeDriver();
		d1.manage().window().maximize();
		d1.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		d1.get("https://ui.freecrm.com/");
	}
	
	@Test(dataProvider = "Login", priority = 1)
	public void ReadExcel(int rownum, String username, String password)throws InterruptedException, IOException
	{
		TakeScreenshot ts = new TakeScreenshot();
		extent.attachReporter(Reporter);
		
		d1.findElement(By.name("email")).sendKeys(username);
		d1.findElement(By.name("password")).sendKeys(password);
		d1.findElement(By.xpath("//*[@id=\"ui\"]/div/div/form/div/div[3]")).click();	
		Thread.sleep(2000);
		if(rownum == 0)
		{
			rownum1 = 0;
			ts.CreateScreenShot(d1, "ValidCredential");	
			logger = extent.createTest("Login with valid Credential");
		//	logger.log(Status.INFO,"Login with valid credential");
		    logger.addScreenCaptureFromPath("../screenshot/ValidCredential.png");
			assertEquals("Cogmento CRM", d1.getTitle());
			
		}
		
		else if(rownum == 1)
		{
			rownum1 = 1;	
			ts.CreateScreenShot(d1, "InvalidCredential");
			logger = extent.createTest("Login with invalid credential"
					+ "");
		   // logger.log(Status.INFO,"Login with invalid credential");
		    logger.addScreenCaptureFromPath("../screenshot/InvalidCredential.png");
			assertEquals("Cogmento CRM", d1.getTitle());
    	}
			
	}
	
	@AfterMethod()
	public void getstatus(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			testresult ="PASS";	
			logger.log(Status.PASS, "Login Test case");
		}
		else if(result.getStatus() == ITestResult.FAILURE)
		{
			testresult ="FAIL";	
			logger.log(Status.FAIL, "Login Test case");
		}
	   config1.createresultcell(testresult, rownum1, 2);	 	
	
	 
	   extent.flush();
	   d1.quit();
	}
	
	
	@DataProvider(name="Login")
	public Object[][] passData() throws IOException
	{
		int rows = config1.getrowcount(0);
		Object[][] data = new Object[rows][3]; //create array for 3
		int i;
		for(i = 0; i<rows;i++)
		{
			data[i][0] = i;
			data[i][1] = config1.getData(0, i, 0);
			data[i][2] = config1.getData(0, i, 1);// read data from excel col1		   			
		}
		return data;			
	}
}
