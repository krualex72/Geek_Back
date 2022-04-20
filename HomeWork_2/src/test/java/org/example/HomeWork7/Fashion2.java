package org.example.HomeWork7;

import org.example.HomeWork6.MainPage;
import org.example.HomeWork6.ModelPage;
import org.example.HomeWork6.PopUp;
import org.example.HomeWork6.ShoppingCart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class Fashion2 extends AbstractTest {

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
