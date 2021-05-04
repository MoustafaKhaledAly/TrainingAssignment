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
import org.testng.annotations.*;


import java.io.IOException;
public class ValidLoginTest {
    public static int delay = 12;
    // //////////PreDefined Variables For LogInSheet\\\\\\\\\\\\\\\\\\\\
    public static int test_Cases_Start_Row_LogInSheet_Valid = 1;
    public static int test_Cases_Start_Column_LogInSheet_Valid = 2;
    public static int LogInSheet_Valid = 1;
    public static int numOfVariables_LogInSheet_Valid = 2;
    public int globalCounter_LogInSheet_Valid = 0;
    ExcelFile DataFromExcel= new ExcelFile(ExcelPath);
    static ExtentTest test;
    static ExtentReports report;
    public static int TestIdColNum=0;



    public static String ExcelPath = System.getProperty("user.dir")
            + "\\src\\main\\resources\\Worksheet.xlsx";
    public static String WebDriverPath = System.getProperty("user.dir")
            + "\\chromedriver.exe";

    public static String homePagePath = "http://automationpractice.com/index.php";



    @BeforeTest
    public void startTest() {
        report = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\ValidSignInReportResults.html");
        report.addSystemInfo("Test Description", "Testing Valid Sign In Credentials")
                .addSystemInfo("User Name", "Moustafa Aly");

    }

    @DataProvider(name = "LogInSheet")
    public Object[][] getExcel2() throws IOException {
        Object[][] TestData=  DataFromExcel.excelData(test_Cases_Start_Row_LogInSheet_Valid,test_Cases_Start_Column_LogInSheet_Valid,numOfVariables_LogInSheet_Valid,LogInSheet_Valid);
        return TestData;

    }
    @Test(dataProvider = "LogInSheet")
    public void signIn(String Email, String Password) throws IOException {
        globalCounter_LogInSheet_Valid++;
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
        String ExpectedMessage="Welcome to your account. Here you can manage all of your personal information and orders.";
        String ActualMessage;
        try {
            ActualMessage=browser.driver.findElement(By.xpath("//*[@id=\"center_column\"]/p")).getText();
        }catch (Exception E){
            ActualMessage="Failed to Log In";
        }
        browser.driver.quit();
        test = report.startTest(DataFromExcel.getCellFromExcel(globalCounter_LogInSheet_Valid,TestIdColNum,LogInSheet_Valid));
        if(ExpectedMessage.equals(ActualMessage)){
            test.log(LogStatus.PASS, "Sign In Successfully");
        }else{

            test.log(LogStatus.FAIL, "Sign In Not Successfully");
        }
        test.log(LogStatus.INFO,"Test Data:");

        test.log(LogStatus.INFO,"Email:  "+Email+" 	,Password: "+Password);
        Assert.assertEquals(ExpectedMessage, ActualMessage);



    }
    @AfterTest
    public void endReport() {
        report.endTest(test);
        report.flush();
    }
}
