package org.example.HomeWork6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Fashion6 extends AbstractTest{

    @Test
    @DisplayName("Fashion - 6")
    void checkMyAddresses() throws InterruptedException {
        new TopMenu(getWebDriver()).clickMyAccount();
        new MyAccountPage(getWebDriver()).clickMyAddresses();
        new MyAddresses(getWebDriver()).validateResult();
        Thread.sleep(3000); // исключительно, чтобы заметить результат
    }
}
