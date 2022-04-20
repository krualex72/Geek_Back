package org.example.HomeWork7;

import io.qameta.allure.*;
import org.example.HomeWork6.MainPage;
import org.example.HomeWork6.ModelPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Fashion1 extends AbstractTest {

    @Test
    @DisplayName("Search Model")
    @Description("Search a model by the find input")
    @Link("http://google.com")
    @Issue("https://bbc.com")
    @Severity(SeverityLevel.MINOR)
    void modelSearch() throws InterruptedException {
        new MainPage(getWebDriver()).toSearch("Blouse");
        Assertions.assertTrue(getWebDriver().getTitle().equals("Search - My Store"), "Страница поиска недоступна");
        new MainPage(getWebDriver()).scrollUpDown(450);
        new MainPage(getWebDriver()).getMoreInfo();
        new ModelPage(getWebDriver()).scrollUpDown(300);
        new ModelPage(getWebDriver()).getSizeByIndex(1);
        new ModelPage(getWebDriver()).getColorWhite();
        Assertions.assertTrue(new ModelPage(getWebDriver()).getSelectMSize().equals("M"),
                "Размер выбран неправильно!!!");
        Thread.sleep(5000); // исключительно, чтобы заметить результат
    }
}
