package com.sumerge.training.pages;

import com.sumerge.training.utilities.MyBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountHomePage {
    public static int delay = 10;
    @FindBy(xpath="//a[@title='Women']")
    WebElement WomenMenu;
    @FindBy(xpath="//a[@title='Blouses']")
    WebElement Blouses ;
    Actions actions;
    public  AccountHomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }
    public void selectWomenBlouses(MyBrowser browser){
        actions.moveToElement(WomenMenu);
        actions.moveToElement(Blouses);
        actions.click().build().perform();
       String BlousesPageisLoaded="//*[@class=\"content_scene_cat\"]";
        browser.delayExecution(delay,BlousesPageisLoaded);
    }


}
