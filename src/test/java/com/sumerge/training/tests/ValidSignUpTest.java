package com.sumerge.training.tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sumerge.training.pages.CreateAccountIntPage;
import com.sumerge.training.pages.CreateAccountPage;
import com.sumerge.training.pages.HomePage;
import com.sumerge.training.utilities.ExcelFile;
import com.sumerge.training.utilities.MyBrowser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class ValidSignUpTest {

	// //////////PreDefined Variables For SignUpSheet\\\\\\\\\\\\\\\\\\\\
	public static int test_Cases_Start_Row_SignUpSheet = 1;
	public static int test_Cases_Start_Column_SignUpSheet = 2;


	public static int signUpSheet = 0;
	public static int delay = 7;
	public static int numOfVariables = 11;
	public static int testIdColNum=0;

	///////////// VARIABLES TO BE USED IN CODE\\\\\\\\\\\\\\\\\
	public int globalCounter = 0;


	static ExtentTest test;
	static ExtentReports report;


	public static String ExcelPath = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\Worksheet.xlsx";
	public static String WebDriverPath = System.getProperty("user.dir")
			+ "\\chromedriver.exe";

	public static String homePagePath = "http://automationpractice.com/index.php";

	ExcelFile DataFromExcel= new ExcelFile(ExcelPath);
	MyBrowser browser;

	@BeforeTest
	public void startTest() {
		report = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\ValidSignUpReportResults.html");
		report.addSystemInfo("Test Description", "Testing Valid Sign Up Credentials")
				.addSystemInfo("User Name", "Moustafa Aly");

	}

	// ///////// DATA FROM EXCEL FOR SIGN UP SHEET/////////////////////
	@DataProvider(name = "SignUpSheet")
	public Object[][] getExcel1() throws IOException {

		Object[][] TestData=  DataFromExcel.excelData(test_Cases_Start_Row_SignUpSheet,test_Cases_Start_Column_SignUpSheet,numOfVariables,signUpSheet);
return TestData;

	}



	// //////// TEST USING DATA DRIVEN FROM EXCEL///////////////////////////

	@Test(dataProvider = "SignUpSheet")
	public void signUpRegister(String FirstName,
			String LastName, String Email, String Password,String Address, String City,
							   String State, String ZipCode, String Country,
							   String MobilePhone,String AliasEmail) throws IOException {

		globalCounter++;
		MyBrowser browser = new MyBrowser("Chrome");
		browser.driverInt(homePagePath);
		HomePage homePage = new HomePage(browser.driver);
		homePage.clickSignInButton();
		browser.delayExecution(delay,"//*[@id=\"SubmitCreate\"]/span");
		CreateAccountIntPage enterEmailPage = new CreateAccountIntPage(browser.driver);
		enterEmailPage.EnterEmailAddress(Email);
		enterEmailPage.clickCreateAccountButton();
		browser.delayExecution(delay,"//*[@id=\"customer_firstname\"]");

		CreateAccountPage createAccount = new CreateAccountPage(browser.driver);

		createAccount.enterFirstName(FirstName);
		createAccount.enterLastName(LastName);
		createAccount.enterPassword(Password);
		createAccount.enterAddress(Address);
		createAccount.enterCity(City);
		createAccount.selectState(State);
		createAccount.enterZipCode(ZipCode);
		createAccount.enterMobileNum(MobilePhone);
		createAccount.enterAliasEmailAddress(AliasEmail);
		createAccount.clickRegisterButton();
		browser.delayExecution(delay,"\t//*[@id=\"center_column\"]/p");
		String ExpectedMessage="Welcome to your account. Here you can manage all of your personal information and orders.";


		String ActualMessage;
		try {
			 ActualMessage=browser.driver.findElement(By.xpath("\t//*[@id=\"center_column\"]/p")).getText();
		}catch (Exception E){
			ActualMessage="Account is not Created";
		}
		browser.driver.quit();
		test = report.startTest(DataFromExcel.getCellFromExcel(globalCounter,testIdColNum,signUpSheet));
		if(ExpectedMessage.equals(ActualMessage)){
			test.log(LogStatus.PASS, "Account is Created");
		}else{

			test.log(LogStatus.FAIL, "Account is not Created");
		}
		test.log(LogStatus.INFO,"Test Data:");

		test.log(LogStatus.INFO,"First Name: "+FirstName+" 	,Last Name: "+LastName+" 	,Email: "+Email+" 	,Password"+Password+" 	,Address"+Address+"		,City: "+City+" 	,State: "+State+" 	,Zip Code: "+ZipCode+" 	,Country: "+Country+" 	,Mobile Num: "+MobilePhone+" 	,Alias Email"+ AliasEmail);
		Assert.assertEquals(ExpectedMessage, ActualMessage);


	}



	@AfterTest
	public void endReport() {
		report.endTest(test);
		report.flush();
	}

	public static String takeScreenShot(WebDriver driver, int testCaseCounter)
			throws IOException {
		File sourceFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		File Dest = new File("TestCase" + (testCaseCounter) + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(sourceFile, Dest);
		testCaseCounter++;
		return errflpath;
	}

}
