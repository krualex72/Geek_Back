package org.example.HomeWork7;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import junit.framework.AssertionFailedError;
import org.apache.commons.io.FileUtils;
import org.example.HomeWork6.LoginPage;
import org.example.HomeWork6.TopMenu;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractTest {

    static EventFiringWebDriver eventDriver;

    @BeforeAll
    static void setDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        options.setPageLoadTimeout(Duration.ofSeconds(30));

        eventDriver = new EventFiringWebDriver(new ChromeDriver(options));
        eventDriver.register(new MyWebDriverEventListener());

        eventDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeEach
    @Step("Первичные проверки и авторизация")
    void initMainPageAndLogIn(){
        //new TopMenu(getWebDriver()).clickMainPageLink(); // переходим на главную страницу
        //getWebDriver().get("http://automationpractice.com/"); // переходим на главную страницу
        Assertions.assertDoesNotThrow( ()-> getWebDriver().navigate().to("http://automationpractice.com/"),"Страница не доступна");
        Assertions.assertTrue(getWebDriver().getTitle().contains("My Store"), "Главная страница недоступна!");

        new TopMenu(getWebDriver()).clickLogIn(); // переход к авторизации в верхнем меню
        Assertions.assertTrue(getWebDriver().getTitle().equals("Login - My Store"), "Страница входа в аккаунт не доступна");
        new LoginPage(getWebDriver()).toLoginIn(); // вход по заданным логину и паролю
        Assertions.assertTrue(getWebDriver().getTitle().equals("My account - My Store"), "Страница аккаунта не доступна");
        new TopMenu(getWebDriver()).clickMainPageLink(); // переходим на главную страницу

    }

    @AfterEach
    @Step("Запись логов браузера и выход из ЛК")
    void logOut() throws InterruptedException, IOException {
        String logFileName = UsefulUtils.getLogs(getWebDriver());
        getBytes(logFileName);
        new TopMenu(getWebDriver()).clickSignOut();
    }

    @Attachment
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("", resourceName));
    }

    @AfterAll
    public static void exit(){

       // if(eventDriver !=null) eventDriver.quit();

    }

    public WebDriver getWebDriver(){
        return this.eventDriver;
    }

}
