package com.sumerge.training.pages;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CartPage {

    @FindBy(xpath="//*[@id=\"product_2_7_0_491885\"]/td[2]/p")

    WebElement FirstItemName;


    public  CartPage(WebDriver driver){
        PageFactory.initElements(driver, this);

    }

    public String getFirstItemName(){
        try {
            return FirstItemName.getText();
        }catch (Exception E){
            return E.getMessage();
        }
    }
}