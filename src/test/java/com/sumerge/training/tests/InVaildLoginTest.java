package com.sumerge.training.tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sumerge.training.pages.HomePage;
import com.sumerge.training.pages.SignInPage;
import com.sumerge.training.utilities.ExcelFile;
import com.sumerge.training.utilities.MyBrowser;
import com.sumerge.training.utilities.StaticProvider;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class InVaildLoginTest {


    static ExtentTest test;
    static ExtentReports report;





    @BeforeTest
    public void startTest() {
        report = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\InValidSignInReportResults.html");
        report.addSystemInfo("Test Description", "Testing InValid Sign In Credentials")
                .addSystemInfo("User Name", "Moustafa Aly");

    }

    @Test(dataProvider = "LogInSheetInValid", dataProviderClass = StaticProvider.class)
    public void signIn(String TestID,String TestDescription,String Email, String Password,String Expected) throws IOException {

        MyBrowser browser = new MyBrowser("Chrome");
        HomePage homePage = new HomePage(browser);
        homePage.clickSignInButton(browser);
        SignInPage SignInPage = new SignInPage(browser.driver);
        SignInPage.EnterEmailAddress(Email);
        SignInPage.enterPassword(Password);
        SignInPage.clicksSignInButton(browser);
        String ActualMessage;
        try {
            ActualMessage=browser.driver.findElement(By.xpath("//*[@class=\"alert alert-danger\"]//ol//li")).getText();
        }catch (Exception E){
            ActualMessage="Failed to Log In";
        }
        browser.driver.quit();
        test = report.startTest(TestID);
        if(Expected.equals(ActualMessage)){
            test.log(LogStatus.PASS, "Error Message is Displayed Correctly");
        }else{

            test.log(LogStatus.FAIL, "Error Message is not Displayed");
        }
        test.log(LogStatus.INFO,"Test Data:");

        test.log(LogStatus.INFO,"Email:  "+Email+" 	,Password: "+Password+"Expected Message :"+Expected);
        Assert.assertEquals(ActualMessage,Expected);


    }
    @AfterTest
    public void endReport() {
        report.endTest(test);
        report.flush();
    }
}

