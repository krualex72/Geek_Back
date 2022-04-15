package org.example.HomeWork6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;

public class Fashion2 extends AbstractTest{

    @Test
    @DisplayName("Fashion - 2")
    void cartFilling() throws InterruptedException {
        new MainPage(getWebDriver()).scrollUpDown(750);
        new MainPage(getWebDriver()).getTShirtToCart(); // используется потом как предусловие
        new MainPage(getWebDriver()).hoverChiffonDress();
        new MainPage(getWebDriver()).clickButtonMoreChiffonDress();
        new ModelPage(getWebDriver()).scrollUpDown(350);
        new ModelPage(getWebDriver()).getSelectMSize();
        new ModelPage(getWebDriver()).getColorGreen();
        new ModelPage(getWebDriver()).addToCart();
        String parentWindowHandler1 = new ModelPage(getWebDriver()).switchToPopUp(); // переключаемся к всплывающему окну
        new PopUp(getWebDriver()).clickCloseWindow();
        Thread.sleep(2000); // исключительно, чтобы заметить результат
        getWebDriver().switchTo().window(parentWindowHandler1);  // переключаемся к родительскому окну
        new ShoppingCart(getWebDriver()).viewShoppingCart();
        new ShoppingCart(getWebDriver()).checkOut();
        new ShoppingCart(getWebDriver()).scrollUpDown(550);
        new ShoppingCart(getWebDriver()).validateResult(); // проверяем результат
       Thread.sleep(5000); // исключительно, чтобы заметить результат

    }
}
