package com.sumerge.training.tests;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sumerge.training.pages.*;
import com.sumerge.training.utilities.ExcelFile;
import com.sumerge.training.utilities.MyBrowser;
import com.sumerge.training.utilities.StaticProvider;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.thread.IThreadWorkerFactory;


import java.io.IOException;
public class CartValidationTest {





    static ExtentTest test;
    static ExtentReports report;




    @BeforeTest
    public void startTest() {
        report = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\CartExistenceReportResults.html");
        report.addSystemInfo("Test Description", "Testing Blouse is added to Cart")
                .addSystemInfo("User Name", "Moustafa Aly");

    }

    @Test(dataProvider = "CartValidation", dataProviderClass = StaticProvider.class)
    public void signIn(String TestID,String TestDescription,String Email, String Password) throws IOException {

        MyBrowser browser = new MyBrowser("Chrome");
        HomePage homePage = new HomePage(browser);
        homePage.clickSignInButton(browser);
        SignInPage SignInPage = new SignInPage(browser.driver);
        SignInPage.EnterEmailAddress(Email);
        SignInPage.enterPassword(Password);
        SignInPage.clicksSignInButton(browser);
        AccountHomePage myAccount = new AccountHomePage(browser.driver);
        myAccount.selectWomenBlouses(browser);
        BlousesPage myBlouse = new BlousesPage(browser.driver);
        String ExpectedName=myBlouse.getItemName();
        myBlouse.addItemToCart(browser);
        CartPage cartPage=new CartPage(browser.driver);
        String ActualName;
        try {
           ActualName=cartPage.getFirstItemName();
        }catch (Exception E){
            ActualName="Item Does Not Exist";
        }
        browser.driver.quit();
        test = report.startTest(TestID);
        if(ExpectedName.equals(ActualName)){
            test.log(LogStatus.PASS, "Cart Updated Successfully");
        }else{

            test.log(LogStatus.FAIL, "Cart Is Not Updated Successfully");
        }
        test.log(LogStatus.INFO,"Test Data:");

        test.log(LogStatus.INFO,"Email:  "+Email+" 	,Password: "+Password);
        Assert.assertEquals(ActualName,ExpectedName);
    }
    @AfterTest
    public void endReport() {
        report.endTest(test);
        report.flush();
    }
}
