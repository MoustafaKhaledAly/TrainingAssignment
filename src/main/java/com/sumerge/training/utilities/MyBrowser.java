package com.sumerge.training.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyBrowser {
  public WebDriver driver=null;
    public MyBrowser(String Browser){
        switch(Browser)
        {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();

                break;
                case "Firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();

                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();

                break;
            default:
                System.out.println("Browser is not Supported");
        }


    }
    public void driverInt(String IntialUrl){
        this.driver.manage().window().maximize();
        this.driver.navigate().to(IntialUrl);
    }
    public void delayExecution(int delay, String condition){
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, delay);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By
                    .xpath(condition)));
        } catch (Exception c) {
            System.out.println(c.getMessage());
        }
    }





}
