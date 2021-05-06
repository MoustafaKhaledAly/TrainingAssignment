package com.sumerge.training.pages;

import com.sumerge.training.utilities.MyBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BlousesPage {

    @FindBy(xpath="//*[@class=\"product_img_link\"]")
    WebElement Item;
    @FindBy(xpath="//*[@class=\"right-block\"]//h5//a")
    WebElement ItemName;
    @FindBy(xpath="//*[@class=\"button ajax_add_to_cart_button btn btn-default\"]")
    WebElement AddtoCartButton;

    @FindBy(xpath="//*[@class=\"btn btn-default button button-medium\"]")
    WebElement proceedtoCheckOutButton;
    public static int delay = 7;
    public static String popupImage="//img[@class='layer_cart_img img-responsive']";
    public static String trashbutton="//i[@class='icon-trash']";




    Actions actions;
    public  BlousesPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }
    public void addItemToCart(MyBrowser browser){
        actions.moveToElement(Item);
        actions.moveToElement(AddtoCartButton);
        actions.click().build().perform();

        browser.delayExecution(delay,popupImage);
        proceedtoCheckOutButton.click();
        browser.delayExecution(delay,trashbutton);
    }
    public String getItemName(){
        return ItemName.getText();
    }
}
