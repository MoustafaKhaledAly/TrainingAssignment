package com.sumerge.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountHomePage {
    @FindBy(xpath="//*[@class=\"sf-menu clearfix menu-content sf-js-enabled sf-arrows\"]/li[1]/a")
    WebElement WomenMenu;
    Actions actions;
    public  AccountHomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }
    public void selectWomenBlouses(WebDriver driver){
        actions.moveToElement(WomenMenu);
        WebElement Blouses = driver.findElement(By.xpath("//*[@class=\"submenu-container clearfix first-in-line-xs\"]/li[1]/ul[1]/li[2]/a"));
        actions.moveToElement(Blouses);
        actions.click().build().perform();
    }


}
