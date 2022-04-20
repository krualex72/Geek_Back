package org.example.HomeWork7;

import io.qameta.allure.*;
import org.example.HomeWork6.MyAccountPage;
import org.example.HomeWork6.OrderHistory;
import org.example.HomeWork6.TopMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class Fashion5 extends AbstractTest {

    @Test
    @DisplayName("Add a Message to the Order")
    @Description("Sending a message at a previously placed order")
    //@Link("http://automationpractice.com/index.php")
    @Issue("https://github.com/krualex72/HomeWork_9/tree/master/HomeWork_2/src/test/java/org/example/HomeWork7")
    @Severity(SeverityLevel.NORMAL)
    @Step("Отправка сообщения в предварительно размещенный заказ")
    void AddMessageToOrder() throws InterruptedException {
        new TopMenu(getWebDriver()).clickMyAccount();
        new MyAccountPage(getWebDriver()).clickMyOrders();
        new OrderHistory(getWebDriver()).scrollScreenDown();
        new OrderHistory(getWebDriver()).clickMyOrder()
                .scrollUpDown(1200);
        new OrderHistory(getWebDriver()).sendMessage("Some message");
        new OrderHistory(getWebDriver()).validateResult1();
        Thread.sleep(3000); // исключительно, чтобы заметить результат

    }

}
