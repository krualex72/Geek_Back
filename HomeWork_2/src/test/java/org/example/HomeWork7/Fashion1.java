package org.example.HomeWork7;

import io.qameta.allure.*;
import org.example.HomeWork6.MainPage;
import org.example.HomeWork6.ModelPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Story("Поведение Юзера на сайте")
public class Fashion1 extends AbstractTest {

    @Test
    @DisplayName("Search Model")
    @Description("Search a model by the find input")
    //@Link("http://automationpractice.com/index.php")
    @Issue("https://github.com/krualex72/HomeWork_9/tree/master/HomeWork_2/src/test/java/org/example/HomeWork7")
    @Severity(SeverityLevel.MINOR)
    @Step("Проверка поиска модели Blouse через поле поиска на сайте")
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
        Thread.sleep(2500); // исключительно, чтобы заметить результат
    }

}
