package com.testdatabase;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.database.Database;


public class DataBaseLoginForStudent {
	static WebDriver driver;
	static JavascriptExecutor js;
	static Actions ac;

	public static void main(String[] args) throws Throwable {

		String SQLqyeries = "select * from employees";
		
		ArrayList<String> FirstName =new ArrayList<String> ();
		ArrayList<String> Email =new ArrayList<String> ();
		ArrayList<String> Telephone =new ArrayList<String> ();
		ArrayList<String> faxNumber =new ArrayList<String> ();
		
		FirstName=Database.getDataTableColumn(SQLqyeries, 2);
		System.out.println(FirstName);
		Email=Database.getDataTableColumn(SQLqyeries, 3);
		System.out.println(Email);	
		Telephone=Database.getDataTableColumn(SQLqyeries, 5);
		System.out.println(Telephone);
		faxNumber=Database.getDataTableColumn(SQLqyeries, 6);
		System.out.println(faxNumber);
		
		
		for(int i=0;i<FirstName.size();i++) {
		
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://www.qavalidation.com/demo");
		js = (JavascriptExecutor) driver;
		ac = new Actions(driver);

		WebElement Name = driver.findElement(By.id("username"));
		ac.moveToElement(Name).build().perform();
		js.executeScript("arguments[0].style.border='3px solid red'", Name);
		Name.sendKeys(FirstName.get(i));
		WebElement EmailAdd = driver.findElement(By.id("email"));
		ac.moveToElement(EmailAdd).build().perform();
		js.executeScript("arguments[0].style.border='3px solid green'", EmailAdd);

		EmailAdd.sendKeys(Email.get(i));
		WebElement CellPh = driver.findElement(By.id("tel"));
		ac.moveToElement(CellPh).build().perform();
		js.executeScript("arguments[0].style.border='3px solid yellow'", CellPh);

		CellPh.sendKeys(Telephone.get(i));

		WebElement fexNo = driver.findElement(By.xpath("//*[@id='fax']"));
		ac.moveToElement(fexNo).build().perform();
		js.executeScript("arguments[0].style.border='3px solid blue'", fexNo);

		js.executeScript("arguments[0].removeAttribute('disabled')", fexNo);

		fexNo.sendKeys(faxNumber.get(i));

		// screen shot
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Date date = new Date();
		String dateTime = dateFormat.format(date.getTime());
		// String
		// destination=".//RCO_Regression/Screenshot"+ScreenShotName+"-"+dateTime+".png";
		String destination = currentDir + "./Screen Shot/" + dateTime + "/" + "Login Page" + ".png";
		FileUtils.copyFile(source, new File(destination));

		Thread.sleep(3000);
		WebElement submitbtn = driver.findElement(By.xpath("//*[@class='commit']"));
		ac.moveToElement(submitbtn).build().perform();
		js.executeScript("arguments[0].style.border='3px solid red'", submitbtn);
		Thread.sleep(3000);
		submitbtn.click();
		// Handle Alert box
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		alert.accept();

		
		driver.quit();
		
		if(i==2) {
			break;
		}
		}
	
	}
}
