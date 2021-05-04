package com.sumerge.training.pages;

import org.openqa.selenium.By;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebElement;
 import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
 import org.openqa.selenium.support.ui.WebDriverWait;

 public class SignInPage {
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
         try{
             EmailAddress.sendKeys(Email);
         }catch(Exception e){

         }


     }
     public void enterPassword(String Pass){
         try{
             Password.sendKeys(Pass);
         }catch(Exception e){

         }

     }

     public void clicksSignInButton(){
         try{
             SignInButton.click();
         }catch(Exception e){
         }


     }

 }
