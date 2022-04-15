package org.example.HomeWork6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class Fashion3 extends AbstractTest{

    @Test
    @DisplayName("Fashion - 3")
    void placeOrder() throws InterruptedException {
        new MainPage(getWebDriver()).scrollUpDown(750);
        new MainPage(getWebDriver()).getTShirtToCart(); // используем как предусловие во многих тестах
        new ShoppingCart(getWebDriver()).viewShoppingCart();
        new ShoppingCart(getWebDriver()).checkOut();
        new ShoppingCart(getWebDriver()).scrollUpDown(550);
        new ShoppingCart(getWebDriver()).clickButtonProceed();
        new ShoppingCart(getWebDriver()).scrollUpDown(500);
        new ShoppingCart(getWebDriver()).validateResult1();// проверяем коррекность данных в корзине
        new ShoppingCart(getWebDriver())
                .proceedToAddress()
                .checkAgreeSelector()
                .proceedToCarrier()
                .clickPayByCheck()
                .clickIConfirm()
                .validateResult2();
        Thread.sleep(3000); // исключительно, чтобы заметить результат

    }
}
