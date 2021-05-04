package com.sumerge.training.tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sumerge.training.pages.HomePage;
import com.sumerge.training.pages.SignInPage;
import com.sumerge.training.utilities.ExcelFile;
import com.sumerge.training.utilities.MyBrowser;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class InVaildLoginTest {
    public static int delay = 12;
    // //////////PreDefined Variables For LogInSheet\\\\\\\\\\\\\\\\\\\\
    public static int test_Cases_Start_Row_LogInSheet_InValid = 1;
    public static int test_Cases_Start_Column_LogInSheet_InValid = 2;
    public static int LogInSheet_InValid = 2;
    public static int numOfVariables_LogInSheet_InValid = 3;
    public int globalCounter_LogInSheet_InValid = 0;
    ExcelFile DataFromExcel= new ExcelFile(ExcelPath);
    static ExtentTest test;
    static ExtentReports report;
    public static int TestIdCol=0;



    public static String ExcelPath = System.getProperty("user.dir")
            + "\\src\\main\\resources\\Worksheet.xlsx";
    public static String WebDriverPath = System.getProperty("user.dir")
            + "\\chromedriver.exe";

    public static String homePagePath = "http://automationpractice.com/index.php";



    @BeforeTest
    public void startTest() {
        report = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\InValidSignInReportResults.html");
        report.addSystemInfo("Test Description", "Testing InValid Sign In Credentials")
                .addSystemInfo("User Name", "Moustafa Aly");

    }

    @DataProvider(name = "LogInSheet")
    public Object[][] getExcel2() throws IOException {
        Object[][] TestData=  DataFromExcel.excelData(test_Cases_Start_Row_LogInSheet_InValid,test_Cases_Start_Column_LogInSheet_InValid,numOfVariables_LogInSheet_InValid,LogInSheet_InValid);
        return TestData;

    }
    @Test(dataProvider = "LogInSheet")
    public void signIn(String Email, String Password,String Expected) throws IOException {
        globalCounter_LogInSheet_InValid++;
        MyBrowser browser = new MyBrowser("Chrome");
        browser.driverInt(homePagePath);
        HomePage homePage = new HomePage(browser.driver);
        homePage.clickSignInButton();
        browser.delayExecution(delay,"//*[@id=\"SubmitCreate\"]/span");
        SignInPage SignInPage = new SignInPage(browser.driver);
        SignInPage.EnterEmailAddress(Email);
        SignInPage.enterPassword(Password);
        SignInPage.clicksSignInButton();
        browser.delayExecution(delay,"//*[@id=\"center_column\"]/p");
        String ActualMessage;
        try {
            ActualMessage=browser.driver.findElement(By.xpath("//*[@class=\"alert alert-danger\"]//ol//li")).getText();
        }catch (Exception E){
            ActualMessage="Failed to Log In";
        }
        browser.driver.quit();
        test = report.startTest(DataFromExcel.getCellFromExcel(globalCounter_LogInSheet_InValid,TestIdCol,LogInSheet_InValid));
        if(Expected.equals(ActualMessage)){
            test.log(LogStatus.PASS, "Error Message is Displayed Correctly");
        }else{

            test.log(LogStatus.FAIL, "Error Message is not Displayed");
        }
        test.log(LogStatus.INFO,"Test Data:");

        test.log(LogStatus.INFO,"Email:  "+Email+" 	,Password: "+Password+"Expected Message :"+Expected+"Actual Message: "+ActualMessage);
        Assert.assertEquals(Expected, ActualMessage);



    }
    @AfterTest
    public void endReport() {
        report.endTest(test);
        report.flush();
    }
}
