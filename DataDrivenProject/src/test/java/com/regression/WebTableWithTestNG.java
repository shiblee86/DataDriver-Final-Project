package com.regression;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.excelFactory.ExcelColumn;
import com.extentreport.BaseTest;
import com.pagefactory.MasterPageFactory;


public class WebTableWithTestNG extends BaseTest{

	WebDriver driver;
	ArrayList<String> clubNames;
	MasterPageFactory pf;
	String excelPath = "./TestData/WebTable Test Data.xlsx";
	ArrayList<String> ColumnValue;
	


	@BeforeTest
	public void befortest() {
		
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		driver = new ChromeDriver();
		
	}
	//@Parameters("Client_Id")
	//String Client_Id
	@Test
	public void getWebTableData() {
			//	test.log(Status.INFO, "This is Test one");
			//	test.assignCategory( "Browser opened");
				driver.get("https://www.premierleague.com/tables/");
				pf = PageFactory.initElements(driver, MasterPageFactory.class);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				System.out.println("PAge Title::"+driver.getTitle());
				//test.assignCategory("Page Title::"+driver.getTitle());
				
		
				clubNames = new ArrayList<String>();
		
		List<WebElement> Clubrows = pf.getClubNames();
		

		for (WebElement club : Clubrows) {
			clubNames.add(club.getText().toString());
		}
		System.out.println("Club Name from Webtable::"+clubNames.toString());
		System.out.println("==========================================");
		
		
	//	test.assignCategory( "Club name from webTable::"+clubNames);
		for (String club : clubNames) {
			if (club.equalsIgnoreCase("Chelsea")) {
				System.out.println("==========================================");
				System.out.println("Test Passed:: Club name Found = " + club);
				//test.assignCategory("Test Passed:: Club name Found = " + club);
				System.out.println("----------------------------------------------");
				System.out.println("CLub position::" + (clubNames.indexOf(club) + 1));
			   // test.assignCategory("CLub position::" + (clubNames.indexOf(club) + 1));
				System.out.println("==========================================");
				break;

			}
		}
	}

	
	@Test
	   public void getExcelData() throws Throwable {
		//test.log(Status.INFO, "This is Test two");
		String excelPath="./TestData/WebTable Test Data.xlsx";
		 ColumnValue= new ArrayList<String>();
		
		ColumnValue=ExcelColumn.columnValue(excelPath,0);
		System.out.println("==========================================");
		System.out.println("Excel table club Name::"+ColumnValue);
		System.out.println("==========================================");
		//test.assignCategory("Excel club name::"+ColumnValue);
	}

	@Test
	   public void validation() throws Throwable {
		//test.log(Status.INFO, "This is test three");
		List<String> matchClub = new ArrayList<String>(ColumnValue);
		
		
		matchClub.retainAll(clubNames);
		System.out.println("==========================================");
		System.out.println("WebTable club count: " + clubNames.size());
		//test.assignCategory("WebTable club count: " + clubNames.size());
		System.out.println("Excel club count: " + ColumnValue.size());
		//test.assignCategory( "Excel club count: " + ColumnValue.size());
		System.out.println("Matched club count: " + matchClub.size());
		//test.assignCategory( "Matched club count: " + matchClub.size());
	    System.out.println("The common club name : " + matchClub);
	  //  test.assignCategory( "The common club name : " + matchClub);
	   
	  
	    
	    List<String> notMatchExcelClub = new ArrayList<> (ColumnValue);
	    notMatchExcelClub.removeAll(clubNames); 
	    System.out.println("Not matched Excel club name: " + notMatchExcelClub);
	    //test.assignCategory("Not matched Excel club name: " + notMatchExcelClub);
	    System.out.println("Not matched Excel club count: " + notMatchExcelClub.size());
	   // test.assignCategory( "Not matched Excel club count: " + notMatchExcelClub.size());
	    
	    List<String> notMatchClub = new ArrayList<> (clubNames);
	    notMatchClub.removeAll(ColumnValue); 
	    System.out.println("Not matched WebTable club name: " + notMatchClub);
	   // test.assignCategory("Not matched WebTable club name: " + notMatchClub);
	    System.out.println("Not matched WebTable club count: " + notMatchClub.size());
	  //  test.assignCategory( "Not matched WebTable club count: " + notMatchClub.size());
	    System.out.println("==========================================");
	}



	@AfterTest
	public void teardown() {
		driver.quit();
	}

	
}