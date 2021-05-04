package com.sumerge.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountIntPage {
    @FindBy(xpath="//*[@id=\"email_create\"]")
  WebElement EmailAddress;
    @FindBy(xpath="//*[@id=\"SubmitCreate\"]/span")
    WebElement CreateAccountButton;

    public CreateAccountIntPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }


    public void EnterEmailAddress(String Email){
        try{
            EmailAddress.sendKeys(Email);
        }catch(Exception e){

        }


    }


    public void clickCreateAccountButton(){
        try{
            CreateAccountButton.click();
        }catch(Exception e){
        }


    }

}
