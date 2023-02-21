package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	
	Properties prop;

	public ReadConfig() {
		
		File src=new File("./Configuration/config.properties");
		
		try {
			FileInputStream fis=new FileInputStream(src);
			prop=new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getApplicationURL()
	{
	 String url=prop.getProperty("baseUrl");
	 return url;

	}
	
	public String getUsername()
	{
	 String userName=prop.getProperty("username");
	 return userName;

	}
	
	public String getPassword()
	{
	 String password=prop.getProperty("password");
	 return password;

	}
	
	public String getFirefoxpath()
	{
	 String firefoxpath=prop.getProperty("firefoxpath");
	 return firefoxpath;

	}
	
	public String getChromepath() {
		String chromepath=prop.getProperty("chromepath");
		return chromepath;
	}
	
	public String getIEpath()
	{
	 String iepath=prop.getProperty("iepath");
	 return iepath;

	}

}
