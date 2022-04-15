package org.example.HomeWork6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Fashion4 extends AbstractTest{

    @Test
    @DisplayName("Fashion - 4")
    void checkOrder() throws InterruptedException {
        new TopMenu(getWebDriver()).clickMyAccount();
        new MyAccountPage(getWebDriver()).clickMyOrders();
        new OrderHistory(getWebDriver()).scrollScreenDown();
        new OrderHistory(getWebDriver()).clickMyOrder()
                .scrollUpDown(1200);
        new OrderHistory(getWebDriver()).validateResult();
        Thread.sleep(3000); // исключительно, чтобы заметить результат

    }
}
