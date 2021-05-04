package com.sumerge.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BlousesPage {
    public static int delay = 7;
    @FindBy(xpath="//*[@class=\"product_img_link\"]")
    WebElement Item;
    @FindBy(xpath="//*[@class=\"right-block\"]//h5//a")
    WebElement ItemName;

    Actions actions;
    public  BlousesPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }
    public void addItemToCart(WebDriver driver){
        actions.moveToElement(Item);
        WebElement AddtoCartButton = driver.findElement(By.xpath("//*[@class=\"button ajax_add_to_cart_button btn btn-default\"]"));
        actions.moveToElement(AddtoCartButton);
        actions.click().build().perform();
        WebDriverWait wait = new WebDriverWait(driver, delay);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"btn btn-default button button-medium\"]")));
        WebElement proceedtoCheckOutButton = driver.findElement(By.xpath("//*[@class=\"btn btn-default button button-medium\"]"));
        proceedtoCheckOutButton.click();

    }
    public String getItemName(){
        return ItemName.getText();
    }
}
