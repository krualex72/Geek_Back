package org.example.HomeWork6;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    private WebDriver webDriver;

    public AbstractPage (WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void scrollUpDown(int y){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver; // getDriver();?
        jsExecutor.executeScript("window.scrollBy(0," + y + ")");
    }

    public void scrollScreenDown(){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver; // getDriver();?
        jsExecutor.executeScript("window.scrollBy(0, window.innerHeight");
    }

    protected WebDriver getWebDriver(){
        return this.webDriver;
    }

}
