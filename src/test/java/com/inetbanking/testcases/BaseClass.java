package com.inetbanking.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.LogManager;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.utils.FileUtil;
import com.beust.jcommander.Parameter;
import com.inetbanking.utilities.ReadConfig;




public class BaseClass {

	/*	
    public String baseUrl="https://demo.guru99.com/v3/";
    public String username="mngr479924";
    public String password="ydazUqe";

	 */
	ReadConfig readConfig=new ReadConfig();

	public String baseUrl=readConfig.getApplicationURL();

	public String username=readConfig.getUsername();
	public String password=readConfig.getPassword();


	public static WebDriver driver;
	public static Logger logger;


	//    @BeforeClass
	//    public void setUp()
	//    {
	//    	//System.setProperty("webdriver.chrom.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
	//    	// use below line instead of hard code

	//    	System.setProperty("webdriver.chrom.driver", readConfig.getChromepath());
	//    	driver=new ChromeDriver();
	//    
	//    	
	//    	
	//    	logger=Logger.getLogger("inetbanking");
	//    	PropertyConfigurator.configure("Log4j.properties");  	
	//    }

	//-> see below code for dynamic browser

	@Parameters("browser")
	@BeforeClass
	public void setUp(String br)
	{
		logger=Logger.getLogger("inetbanking");
		PropertyConfigurator.configure("Log4j.properties");

		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.geco.driver", readConfig.getChromepath());
			driver=new ChromeDriver();

		}

		if(br.equals("firefox"))
		{
			System.setProperty("webdriver.geco.driver", readConfig.getFirefoxpath());
			driver=new FirefoxDriver();
		}

		if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", readConfig.getIEpath());
			driver=new InternetExplorerDriver(); 
		}
		System.out.println(baseUrl);

	}



	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

	@AfterMethod
	public void captureScreenShot(Method metname , ITestResult result) {
		if (result.isSuccess()) {
			System.out.println(" this method is passed " +metname.getName());

		}else {
			captureScreen(metname.getName());
		}
		
	}



	public void captureScreen(String tname)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File sourse=ts.getScreenshotAs(OutputType.FILE);
		File target= new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		try {
			FileUtils.copyFile(sourse,target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Screenshot taken");

	}


}
