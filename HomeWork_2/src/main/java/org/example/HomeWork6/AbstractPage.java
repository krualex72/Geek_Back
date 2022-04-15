package org.example.HomeWork6;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.Set;

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
        jsExecutor.executeScript("window.scrollBy(0, window.innerHeight)");
    }

    public String switchToPopUp(){
        String parentWindowHandler = getWebDriver().getWindowHandle(); // сохраняем родительское окно
        String subWindowHandler = null;
        Set<String> handles = getWebDriver().getWindowHandles(); // получаем набор десрипторов окон
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){subWindowHandler = iterator.next();}
        getWebDriver().switchTo().window(subWindowHandler); // переключаемся к всплывающему окну
        return parentWindowHandler;
    }



    protected WebDriver getWebDriver(){
        return this.webDriver;
    }

}
