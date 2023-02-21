package com.inetbanking.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObj.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
 
  @Test	
  public void loginTest()
  {
	  
	  
	  driver.get(baseUrl);
	  LoginPage lp=new LoginPage(driver);
	  
	  logger.info("URL is opened");
	  
	  
	  lp.setUsername(username);
	  logger.info("user name entered..");
	  lp.setPassword(password);
	  logger.info("Password entered...");
      lp.clickSubmit();
      
      String str=driver.getTitle();
      
     
  
      if(str.equalsIgnoreCase("Guru99 Bank Home Page"))
      {
    	 Assert.assertTrue(true);
      }
      
      else
      {
    	 
    	 Assert.assertTrue(false);  
    	 logger.info("Login test failed....");
    	  
      }
   
  }


}

