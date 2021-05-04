package com.sumerge.training.tests;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sumerge.training.pages.AccountHomePage;
import com.sumerge.training.pages.BlousesPage;
import com.sumerge.training.pages.HomePage;
import com.sumerge.training.pages.SignInPage;
import com.sumerge.training.utilities.ExcelFile;
import com.sumerge.training.utilities.MyBrowser;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;


import java.io.IOException;
public class CartValidationTest {
    public static int delay = 12;
    // //////////PreDefined Variables For LogInSheet\\\\\\\\\\\\\\\\\\\\
    public static int test_Cases_Start_Row_CartValidation = 1;
    public static int test_Cases_Start_Column_CartValidation = 2;
    public static int CartValidationSheet = 3;
    public static int numOfVariables_CartValidationSheet = 2;
    public static String SigninPageisLoaded="//*[@id=\"SubmitCreate\"]/span";
    public static String AccountHomePageisLoaded="//*[@id=\"center_column\"]/p";
    public static String BlousesPageisLoaded="//*[@class=\"content_scene_cat\"]";
    public static String ItemName="//*[@id=\"product_2_7_0_491885\"]/td[2]/p";
    public int globalCounter_CartValidationSheet = 0;
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
        report = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\CartExistenceReportResults.html");
        report.addSystemInfo("Test Description", "Testing Blouse is added to Cart")
                .addSystemInfo("User Name", "Moustafa Aly");

    }

    @DataProvider(name = "CartValidation")
    public Object[][] getExcel2() throws IOException {
        Object[][] TestData=  DataFromExcel.excelData(test_Cases_Start_Row_CartValidation,test_Cases_Start_Column_CartValidation,numOfVariables_CartValidationSheet,CartValidationSheet);
        return TestData;

    }
    @Test(dataProvider = "CartValidation")
    public void signIn(String Email, String Password) throws IOException {
        globalCounter_CartValidationSheet++;
        MyBrowser browser = new MyBrowser("Chrome");
        browser.driverInt(homePagePath);
        HomePage homePage = new HomePage(browser.driver);
        homePage.clickSignInButton();
        browser.delayExecution(delay,SigninPageisLoaded);
        SignInPage SignInPage = new SignInPage(browser.driver);
        SignInPage.EnterEmailAddress(Email);
        SignInPage.enterPassword(Password);
        SignInPage.clicksSignInButton();
        browser.delayExecution(delay,AccountHomePageisLoaded);
        AccountHomePage myAccount = new AccountHomePage(browser.driver);
        myAccount.selectWomenBlouses(browser.driver);
        browser.delayExecution(delay,BlousesPageisLoaded);
        BlousesPage myBlouse = new BlousesPage(browser.driver);
        String ExpectedName=myBlouse.getItemName();
        System.out.println(ExpectedName);
        myBlouse.addItemToCart(browser.driver);
        browser.delayExecution(delay,ItemName);
        String ActualName;
        try {
            ActualName=browser.driver.findElement(By.xpath(ItemName)).getText();
        }catch (Exception E){
            ActualName="Item Does Not Exist";
        }
        browser.driver.quit();
        test = report.startTest(DataFromExcel.getCellFromExcel(globalCounter_CartValidationSheet,TestIdColNum,CartValidationSheet));
        if(ExpectedName.equals(ActualName)){
            test.log(LogStatus.PASS, "Cart Updated Successfully");
        }else{

            test.log(LogStatus.FAIL, "Cart Is Not Updated Successfully");
        }
        test.log(LogStatus.INFO,"Test Data:");

        test.log(LogStatus.INFO,"Email:  "+Email+" 	,Password: "+Password);
        Assert.assertEquals(ExpectedName, ActualName);
    }
    @AfterTest
    public void endReport() {
        report.endTest(test);
        report.flush();
    }
}
