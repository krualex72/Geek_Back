package org.example.HomeWork6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class Fashion1 extends AbstractTest{

    @Test
    @DisplayName("Fashion - 1")
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
