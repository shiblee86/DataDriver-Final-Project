package com.grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridConnectionScript {
	
	
	public static void main(String[] args) throws MalformedURLException {
		
		
		DesiredCapabilities capabilites = new DesiredCapabilities(); 
		capabilites.setPlatform(Platform.WIN10);
		ChromeOptions options = new ChromeOptions();
		options.merge(capabilites);
		String hubURL ="http://localhost:4444/wd/hub"; 
		WebDriver driver = new RemoteWebDriver(new URL(hubURL), options);
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		driver.quit();
		
	}

}
