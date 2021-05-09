package com.sumerge.training.tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sumerge.training.pages.CreateAccountIntPage;
import com.sumerge.training.pages.CreateAccountPage;
import com.sumerge.training.pages.HomePage;
import com.sumerge.training.utilities.BaseReport;
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

public class ValidSignUpTest extends BaseReport {



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
		String ActualMessage=createAccount.getMessage(browser);

		browser.driver.quit();
		test = extent.createTest(TestID);

		Assert.assertEquals(ActualMessage,ExpectedMessage);



	}






}
