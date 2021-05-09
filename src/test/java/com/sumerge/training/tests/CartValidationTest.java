package com.sumerge.training.tests;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sumerge.training.pages.*;
import com.sumerge.training.utilities.BaseReport;
import com.sumerge.training.utilities.ExcelFile;
import com.sumerge.training.utilities.MyBrowser;
import com.sumerge.training.utilities.StaticProvider;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.thread.IThreadWorkerFactory;


import java.io.IOException;
public class CartValidationTest extends BaseReport {


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
        String ActualName=cartPage.getFirstItemName();

        browser.driver.quit();
        test = extent.createTest(TestID);

        Assert.assertEquals(ActualName,ExpectedName);
    }

}
