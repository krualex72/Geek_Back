package org.example.HomeWork7;

import io.qameta.allure.*;
import org.example.HomeWork6.MyAccountPage;
import org.example.HomeWork6.OrderHistory;
import org.example.HomeWork6.TopMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class Fashion4 extends AbstractTest {

    @Test
    @DisplayName("Check Order")
    @Description("Checking data previously placed order")
    //@Link("http://automationpractice.com/index.php")
    @Issue("https://github.com/krualex72/HomeWork_9/tree/master/HomeWork_2/src/test/java/org/example/HomeWork7")
    @Severity(SeverityLevel.NORMAL)
    @Step("Проверка данных уже размещенного заказа")
    void checkOrder() throws InterruptedException {
        new TopMenu(getWebDriver()).clickMyAccount();
        new MyAccountPage(getWebDriver()).clickMyOrders();
        new OrderHistory(getWebDriver()).scrollScreenDown();
        new OrderHistory(getWebDriver()).clickMyOrder()
                .scrollUpDown(1200);
        new OrderHistory(getWebDriver()).validateResult();
        Thread.sleep(2000); // исключительно, чтобы заметить результат

    }


}
