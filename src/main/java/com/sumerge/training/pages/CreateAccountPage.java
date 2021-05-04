package com.sumerge.training.pages;

import org.openqa.selenium.By;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

 public class CreateAccountPage {
     @FindBy(xpath="//*[@id=\"customer_firstname\"]")
       WebElement firstName ;
     @FindBy(xpath="//*[@id=\"customer_lastname\"]")
      WebElement lastName;
     @FindBy(xpath="//*[@id=\"email\"]")
      WebElement emailAddress;
     @FindBy(xpath="//*[@id=\"passwd\"]")
      WebElement password;
     @FindBy(xpath="//*[@id=\"address1\"]")
       WebElement address;
     @FindBy(xpath="//*[@id=\"city\"]")
       WebElement city;

       Select state;
     @FindBy(xpath="//*[@id=\"postcode\"]")
       WebElement zipCode;
     @FindBy(xpath="//*[@id=\"id_country\"]")
       WebElement country ;
     @FindBy(xpath="//*[@id=\"phone_mobile\"]")
     WebElement mobileNum;
     @FindBy(xpath="//*[@id=\"alias\"]")
       WebElement aliasEmailAddress;
     @FindBy(xpath="//*[@id=\"submitAccount\"]/span")
       WebElement registerButton ;

    public CreateAccountPage(WebDriver driver){
         try{
             PageFactory.initElements(driver, this);
             state = new Select(driver.findElement(By.name("id_state")));

         }catch(Exception e){

         }
     }
     public void enterFirstName(String Name){
         try{
             firstName.sendKeys(Name);
         }catch(Exception e){


         }

     }
     public void enterLastName(String Name){
         try{
             lastName.sendKeys(Name);
         }catch(Exception e){

         }

     }
     public void enterPassword(String Pass){
         try{
             password.sendKeys(Pass);
         }catch(Exception e){

         }

     }


     public void enterAddress(String addr){
         try{
             address.sendKeys(addr);
         }catch(Exception e){

         }

     }

     public void enterCity(String cityy){
         try{
             city.sendKeys(cityy);
         }catch(Exception e){

         }

     }

     public void selectState(String State){
         try{
             state.selectByVisibleText(State);
         }catch(Exception e){

         }

     }






     public void enterZipCode(String code){
         try{
             zipCode.sendKeys(code);
         }catch(Exception e){

         }

     }

     public void enterMobileNum(String Num){
         try{
             mobileNum.sendKeys(Num);
         }catch(Exception e){

         }

     }
     public void enterAliasEmailAddress(String Email){
         try{
             aliasEmailAddress.clear();
             aliasEmailAddress.sendKeys(Email);
         }catch(Exception e){

         }

     }


     public void clickRegisterButton(){
         try{
             registerButton.click();
         }catch(Exception e){

         }

     }

 }






























