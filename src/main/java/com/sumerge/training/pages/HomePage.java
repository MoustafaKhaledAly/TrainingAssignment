package com.sumerge.training.pages;

import com.sumerge.training.utilities.MyBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath="//div[@class='header_user_info']")
    WebElement signInButton;
    public static String homePagePath = "http://automationpractice.com/index.php";
    public static int delay = 7;

    public HomePage(MyBrowser browser){
        browser.driverInt(homePagePath);
        PageFactory.initElements(browser.driver, this);
    }

    public void clickSignInButton(MyBrowser browser) {
            signInButton.click();
        String CreateButtonPath="//*[@id=\"SubmitCreate\"]/span";
        browser.delayExecution(delay,CreateButtonPath);


    }
}