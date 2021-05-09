package com.sumerge.training.tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sumerge.training.pages.HomePage;
import com.sumerge.training.pages.SignInPage;
import com.sumerge.training.utilities.BaseReport;
import com.sumerge.training.utilities.ExcelFile;
import com.sumerge.training.utilities.MyBrowser;
import com.sumerge.training.utilities.StaticProvider;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;


import java.io.IOException;
public class ValidLoginTest extends BaseReport {


    @Test(dataProvider = "LogInSheetValid", dataProviderClass = StaticProvider.class)
    public void signIn(String TestID,String TestDescription,String Email, String Password,String ExpectedMessage ) throws IOException {

        MyBrowser browser = new MyBrowser("Chrome");
        HomePage homePage = new HomePage(browser);
        homePage.clickSignInButton(browser);
        SignInPage SignInPage = new SignInPage(browser.driver);
        SignInPage.EnterEmailAddress(Email);
        SignInPage.enterPassword(Password);
        SignInPage.clicksSignInButton(browser);

        String ActualMessage=SignInPage.getSuccessMessage(browser);

        browser.driver.quit();
        test = extent.createTest(TestID);

        Assert.assertEquals(ActualMessage,ExpectedMessage);



    }

}
