package org.example.HomeWork6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Iterator;
import java.util.Set;

public class Fashion2 extends AbstractTest{

    @Test
    @DisplayName("Fashion - 2")
    void cartFilling() throws InterruptedException {
        new MainPage(getWebDriver()).scrollUpDown(750);
        new MainPage(getWebDriver()).clickPopular();
        new MainPage(getWebDriver()).hoverTShirts();
        new MainPage(getWebDriver()).clickButtonAddToCart();
        String parentWindowHandler = getWebDriver().getWindowHandle(); // сохраняем родительское окно
        String subWindowHandler = null;

        //buttonAddToCart.click();  // FASHION - 02 шаг 4
        Set<String> handles = getWebDriver().getWindowHandles(); // получаем набор десрипторов окон
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        getWebDriver().switchTo().window(subWindowHandler); // переключаемся к всплывающему окну
        Thread.sleep(2000); // исключительно, чтобы заметить результат
        new PopUp(getWebDriver()).clickButtonContinue();
        getWebDriver().switchTo().window(parentWindowHandler);  // переключаемся к родительскому окну
        new MainPage(getWebDriver()).hoverChiffonDress();
        new MainPage(getWebDriver()).clickButtonMoreChiffonDress();
        new ModelPage(getWebDriver()).scrollUpDown(350);

//        new MainPage(getWebDriver()).getMoreInfo();
//        new ModelPage(getWebDriver()).scrollUpDown(300);
//        new ModelPage(getWebDriver()).getSizeByIndex(1);
//        new ModelPage(getWebDriver()).getColorWhite();
//        Assertions.assertTrue(getWebDriver().findElement(By.xpath(".//select[@id='group_1']/option[2]")).getText().equals("M"),
//                "Размер выбран неправильно!!!");
       Thread.sleep(5000); // исключительно, чтобы заметить результат

       // Assertions.assertEquals("https://",getWebDriver().getCurrentUrl());



    }
}
