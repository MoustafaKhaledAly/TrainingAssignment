package com.sumerge.training.pages;

import com.sumerge.training.utilities.MyBrowser;
import org.openqa.selenium.By;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebElement;
 import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
 import org.openqa.selenium.support.ui.WebDriverWait;

 public class SignInPage {
     public static int delay = 7;
     @FindBy(xpath="//*[@id=\"email\"]")
      WebElement EmailAddress;
     @FindBy(xpath="//*[@id=\"passwd\"]")
       WebElement Password;

     @FindBy(xpath="//*[@id=\"SubmitLogin\"]/span")
       WebElement SignInButton = null;

  public  SignInPage(WebDriver driver){
      PageFactory.initElements(driver, this);

     }


     public void EnterEmailAddress(String Email){

             EmailAddress.sendKeys(Email);



     }
     public void enterPassword(String Pass){
             Password.sendKeys(Pass);


     }

     public void clicksSignInButton(MyBrowser browser){
             SignInButton.click();
             String TextMessagePath="//*[@id=\"center_column\"]/p";
         browser.delayExecution(delay,TextMessagePath);


     }
     public String getSuccessMessage(MyBrowser browser){
         try {
             return browser.driver.findElement(By.xpath("//*[@id=\"center_column\"]/p")).getText();
         }catch (Exception E){
             return E.getMessage();
         }

     }
     public String getErrorMessage(MyBrowser browser){
         try {
             return browser.driver.findElement(By.xpath("//*[@class=\"alert alert-danger\"]//ol//li")).getText();
         }catch (Exception E){
             return E.getMessage();
         }
     }

 }
