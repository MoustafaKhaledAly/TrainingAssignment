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
import org.testng.annotations.*;


import java.io.IOException;
public class ValidLoginTest {


    static ExtentTest test;
    static ExtentReports report;


    @BeforeTest
    public void startTest() {
        report = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\ValidSignInReportResults.html");
        report.addSystemInfo("Test Description", "Testing Valid Sign In Credentials")
                .addSystemInfo("User Name", "Moustafa Aly");

    }

    @Test(dataProvider = "LogInSheetValid", dataProviderClass = StaticProvider.class)
    public void signIn(String TestID,String TestDescription,String Email, String Password,String ExpectedMessage ) throws IOException {

        MyBrowser browser = new MyBrowser("Chrome");
        HomePage homePage = new HomePage(browser);
        homePage.clickSignInButton(browser);
        SignInPage SignInPage = new SignInPage(browser.driver);
        SignInPage.EnterEmailAddress(Email);
        SignInPage.enterPassword(Password);
        SignInPage.clicksSignInButton(browser);

        String ActualMessage;
        try {
            ActualMessage=browser.driver.findElement(By.xpath("//*[@id=\"center_column\"]/p")).getText();
        }catch (Exception E){
            ActualMessage="Failed to Log In";
        }
        browser.driver.quit();
        test = report.startTest(TestID);
        if(ExpectedMessage.equals(ActualMessage)){
            test.log(LogStatus.PASS, "Sign In Successfully");
        }else{

            test.log(LogStatus.FAIL, "Sign In Not Successfully");
        }
        test.log(LogStatus.INFO,"Test Data:");

        test.log(LogStatus.INFO,"Email:  "+Email+" 	,Password: "+Password);
        Assert.assertEquals(ActualMessage,ExpectedMessage);



    }
    @AfterTest
    public void endReport() {
        report.endTest(test);
        report.flush();
    }
}
