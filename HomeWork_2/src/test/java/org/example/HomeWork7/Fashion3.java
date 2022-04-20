package org.example.HomeWork7;

import io.qameta.allure.*;
import org.example.HomeWork6.MainPage;
import org.example.HomeWork6.ShoppingCart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Fashion3 extends AbstractTest {

    @Test
    @DisplayName("Place Order")
    @Description("Checking a place order process")
    //@Link("http://automationpractice.com/index.php")
    @Issue("https://github.com/krualex72/HomeWork_9/tree/master/HomeWork_2/src/test/java/org/example/HomeWork7")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Проверка размещения заказа (оплата чеком)")
    void placeOrder() throws InterruptedException, IOException {
        new MainPage(getWebDriver()).scrollUpDown(750);
        new MainPage(getWebDriver()).getTShirtToCart(); // используем как предусловие во многих тестах
        new ShoppingCart(getWebDriver()).viewShoppingCart();
        new ShoppingCart(getWebDriver()).checkOut();
        new ShoppingCart(getWebDriver()).scrollUpDown(550);
        new ShoppingCart(getWebDriver()).clickButtonProceed();
        new ShoppingCart(getWebDriver()).scrollUpDown(500);
        new ShoppingCart(getWebDriver()).validateResult1();// проверяем коррекность данных в корзине
        String fileName = "fashion-3-org.example.com.automationpractice.test-" + System.currentTimeMillis() + ".txt";
        File file = UsefulUtils.makeScreenshot(getWebDriver(),fileName);
        saveScreenshot(Files.readAllBytes(file.toPath()));
        new ShoppingCart(getWebDriver())
                .proceedToAddress()
                .checkAgreeSelector()
                .proceedToCarrier()
                .clickPayByCheck()
                .clickIConfirm()
                .validateResult2();
        Thread.sleep(3000); // исключительно, чтобы заметить результат

    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}
