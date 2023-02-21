package com.inetbanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.checkerframework.checker.units.qual.h;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	
	
	public ExtentHtmlReporter htmlReporter;
	

	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{
		String timestamp=new SimpleDateFormat("yyy.MM.dd.ss").format(new Date());
		String repName="Test-Report-"+timestamp+".html";
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/Result/Extent.html");
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "harendra");		
	    htmlReporter.config().setDocumentTitle("Ineet bank test project");
		htmlReporter.config().setReportName("Functional test report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	
	
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(),ExtentColor.BLUE));
	}
	
	
	@Override
	public void onTestFailure(ITestResult tr) {
		
		logger=extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));	    
		String screenshotpath=System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";
	
		File f=new File(screenshotpath);
		
//		if(f.exists())
//		{
//			try {
//				logger.fail("screenshot is below:"+logger.addScreenCaptureFromPath(screenshotpath));
//			    }
//			catch(IOException e)
//			{
//				e.printStackTrace();
//			}
//		}
		extent.flush();
		
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		
		logger=extent.createTest(tr.getName());
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
		extent.flush();
	}

	@Override
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
	
	
	
}
	