package org.example.HomeWork7;

import io.qameta.allure.*;
import org.example.HomeWork6.MyAccountPage;
import org.example.HomeWork6.MyAddresses;
import org.example.HomeWork6.TopMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class Fashion6 extends AbstractTest {

    @Test
    @DisplayName("Check My Addresses")
    @Description("Checking data saved previously in the account")
    //@Link("http://automationpractice.com/index.php")
    @Issue("https://github.com/krualex72/HomeWork_9/tree/master/HomeWork_2/src/test/java/org/example/HomeWork7")
    @Severity(SeverityLevel.MINOR)
    @Step("Проверка данных адреса,сохраненного в ЛК")
    void checkMyAddresses() throws InterruptedException {
        new TopMenu(getWebDriver()).clickMyAccount();
        new MyAccountPage(getWebDriver()).clickMyAddresses();
        new MyAddresses(getWebDriver()).validateResult();
        Thread.sleep(2000); // исключительно, чтобы заметить результат
    }

}
