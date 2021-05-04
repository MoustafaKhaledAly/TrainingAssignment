package com.sumerge.training.pages;

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

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void clickSignInButton() {
        try {
            signInButton.click();
        } catch (Exception e) {
        }


    }
}