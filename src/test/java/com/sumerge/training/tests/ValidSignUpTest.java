package com.sumerge.training.tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sumerge.training.pages.CreateAccountIntPage;
import com.sumerge.training.pages.CreateAccountPage;
import com.sumerge.training.pages.HomePage;
import com.sumerge.training.utilities.ExcelFile;
import com.sumerge.training.utilities.MyBrowser;
import com.sumerge.training.utilities.StaticProvider;
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



	static ExtentTest test;
	static ExtentReports report;






	@BeforeTest
	public void startTest() {
		report = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\ValidSignUpReportResults.html");
		report.addSystemInfo("Test Description", "Testing Valid Sign Up Credentials")
				.addSystemInfo("User Name", "Moustafa Aly");

	}


	@Test(dataProvider = "SignUpSheetValid", dataProviderClass = StaticProvider.class)
	public void signUpRegister(String TestID,String TestDescription,String FirstName,
			String LastName, String Email, String Password,String Address, String City,
							   String State, String ZipCode, String Country,
							   String MobilePhone,String AliasEmail,String ExpectedMessage) throws IOException {

		MyBrowser browser = new MyBrowser("Chrome");

		HomePage homePage = new HomePage(browser);
		homePage.clickSignInButton(browser);
		CreateAccountIntPage enterEmailPage = new CreateAccountIntPage(browser.driver);
		enterEmailPage.EnterEmailAddress(Email);
		enterEmailPage.clickCreateAccountButton(browser);
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
		createAccount.clickRegisterButton(browser);


		String ActualMessage;
		try {
			 ActualMessage=browser.driver.findElement(By.xpath("\t//*[@id=\"center_column\"]/p")).getText();
		}catch (Exception E){
			ActualMessage=E.getMessage();
		}
		browser.driver.quit();
		test = report.startTest(TestID);
		if(ExpectedMessage.equals(ActualMessage)){
			test.log(LogStatus.PASS, "Account is Created");
		}else{

			test.log(LogStatus.FAIL, "Account is not Created");
		}
		test.log(LogStatus.INFO,"Test Data:");

		test.log(LogStatus.INFO,"TestID"+TestID+"Test description"+TestDescription+"First Name: "+FirstName+" 	,Last Name: "+LastName+" 	,Email: "+Email+" 	,Password"+Password+" 	,Address"+Address+"		,City: "+City+" 	,State: "+State+" 	,Zip Code: "+ZipCode+" 	,Country: "+Country+" 	,Mobile Num: "+MobilePhone+" 	,Alias Email"+ AliasEmail+"Expected"+ExpectedMessage);
		Assert.assertEquals(ActualMessage,ExpectedMessage);


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
