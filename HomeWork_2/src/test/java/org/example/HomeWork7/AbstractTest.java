package org.example.HomeWork7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.HomeWork6.LoginPage;
import org.example.HomeWork6.TopMenu;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class AbstractTest {

    static WebDriver webDriver;

    @BeforeAll
    static void setDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");
        options.setPageLoadTimeout(Duration.ofSeconds(30));

        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeEach
    void initMainPageAndLogIn(){
        //new TopMenu(getWebDriver()).clickMainPageLink(); // переходим на главную страницу
        //getWebDriver().get("http://automationpractice.com/"); // переходим на главную страницу
        Assertions.assertDoesNotThrow( ()-> getWebDriver().navigate().to("http://automationpractice.com/"),"Страница не доступна");
        Assertions.assertTrue(true);
        Assertions.assertTrue(getWebDriver().getTitle().contains("My Store"), "Главная страница недоступна!");

        new TopMenu(getWebDriver()).clickLogIn(); // переход к авторизации в верхнем меню
        Assertions.assertTrue(getWebDriver().getTitle().equals("Login - My Store"), "Страница входа в аккаунт не доступна");
        new LoginPage(getWebDriver()).toLoginIn(); // вход по заданным логину и паролю
        Assertions.assertTrue(getWebDriver().getTitle().equals("My account - My Store"), "Страница аккаунта не доступна");
        new TopMenu(getWebDriver()).clickMainPageLink(); // переходим на главную страницу

    }

    @AfterEach
    void logOut() throws InterruptedException {
        new TopMenu(getWebDriver()).clickSignOut();
    }

    @AfterAll
    public static void exit(){

       // if(webDriver !=null) webDriver.quit();

    }

    public WebDriver getWebDriver(){
        return this.webDriver;
    }

}
