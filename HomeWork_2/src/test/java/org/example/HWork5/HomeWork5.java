package org.example.HWork5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class HomeWork5 {

    static WebDriver driver;

    @BeforeAll
    static void init(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @BeforeEach
    void getPageAndLogging() throws InterruptedException {
        getDriver().get("http://automationpractice.com/"); // переходим на главную страницу
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("http://automationpractice.com/"));
        Assertions.assertTrue(getDriver().getTitle().contains("My Store"), "Главная страница недоступна!");
        // Обеспечиваем вход в кабинет пользователя по известным логину и паролю
        WebElement accountLink = getDriver().findElement(By.xpath(".//a[@class='login']")); // Кнопка Sign In
        accountLink.click();
        Actions logIn = new Actions(getDriver());
        logIn.sendKeys(getDriver().findElement(By.id("email")),"krutikov-alex@ya.ru")
                .pause(1000l)
                .sendKeys(getDriver().findElement(By.id("passwd")),"TestPass")
                .pause(1000l)
                .click(getDriver().findElement(By.id("SubmitLogin")))
                .build()
                .perform();
        WebElement logoLink = getDriver().findElement(By.xpath(".//a[@title='My Store']")); // Переход на главную страницу по клику на логотип
        logoLink.click();
        Thread.sleep(1000);
    }

    @AfterEach
    void logOut() throws InterruptedException {
        Thread.sleep(2000);
        WebElement logOut = getDriver().findElement(By.xpath(".//a[@class='logout']")); // Кнопка Sign Out
        //Thread.sleep(1000l);
        logOut.click();
    }

    @AfterAll
    static void close(){
        // driver.quit();
    }

    @Test
    @DisplayName("Fashion - 1")
    void modelSearch() throws InterruptedException {

        WebElement searchField = getDriver().findElement(By.id("search_query_top")); // поле ввода запроса поиска
        WebElement buttonSearch = getDriver().findElement(By.name("submit_search"));
        searchField.sendKeys("Blouse"); // FASHION - 01 шаг 2
        buttonSearch.click(); // FASHION - 01 шаг 2 (продолжение)

        // добавлен скроллинг
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("window.scrollBy(0,450)");

        Actions builder = new Actions(getDriver()); // FASHION - 01 шаг 3
        builder.moveToElement(getDriver().findElement(By.xpath(".//a[@class='product_img_link']"))).build().perform();
        WebElement buttonMore = getDriver().findElement(By.xpath(".//a[@class='button lnk_view btn btn-default']")); // кнопка More
        buttonMore.click();  // FASHION - 01 шаг 4

        // добавлен скроллинг
        jsExecutor.executeScript("window.scrollBy(0,300)");

        Actions builder1 = new Actions(getDriver()); // FASHION - 01 шаг 5
        builder1.moveToElement(getDriver().findElement(By.xpath(".//div[@class='selector']"))).build().perform();
        WebElement selectSize = getDriver().findElement(By.tagName("select"));
        Select select = new Select(selectSize);
        select.selectByIndex(1);  // выбор размера М
        WebElement modelColorWhite = getDriver().findElement(By.xpath(".//a[@id='color_8']")); // выбор белого цвета
        modelColorWhite.click();  // FASHION - 01 шаг 6
        Thread.sleep(5000); // исключительно, чтобы заметить результат
    }

    @Test
    @DisplayName("Fashion - 2")
    void cartFilling() throws InterruptedException {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver(); // FASHION - 02 шаг 2 (шаг 1 выполнен в предусловии BeforeAll)
        jsExecutor.executeScript("window.scrollBy(0,750)");
        Actions builder3 = new Actions(getDriver()); // FASHION - 02 шаг 3
        builder3.moveToElement(getDriver().findElement(By.xpath(".//img[@alt='Faded Short Sleeve T-shirts']"))).build().perform();
        WebElement buttonAddToCart = getDriver().findElement(By.xpath(".//a[@title='Add to cart']")); // кнопка Add To Cart
        String parentWindowHandler = getDriver().getWindowHandle(); // сохраняем родительское окно
        String subWindowHandler = null;
        buttonAddToCart.click();  // FASHION - 02 шаг 4
        Set<String> handles = getDriver().getWindowHandles(); // получаем набор десрипторов окон
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        getDriver().switchTo().window(subWindowHandler); // переключаемся к всплывающему окну
        Thread.sleep(10000l);
        WebElement buttonContinue = getDriver().findElement(By.xpath(".//span[@class='continue btn btn-default button exclusive-medium']")); // выбор белого цвета
        buttonContinue.click();  // FASHION - 02 шаг 5
        getDriver().switchTo().window(parentWindowHandler);  // переключаемся к родительскому окну
        Actions builder4 = new Actions(getDriver()); // FASHION - 02 шаг 14 (предыдущие шаги пропущены, посколку фактически повторяют итерации)
        builder4.moveToElement(getDriver().findElement(By.xpath(".//img[@alt='Printed Chiffon Dress']"))).build().perform();
        WebElement buttonMore1 = getDriver().findElement(By.xpath(".//a[@data-id-product='7']/following-sibling::*[1]")); // кнопка More для продукта
        buttonMore1.click();  // FASHION - 02 шаг 15
        //Thread.sleep(50000l);
        WebElement selectSize1 = getDriver().findElement(By.tagName("select"));
        Select select1 = new Select(selectSize1); //FASHION - 01 шаг 16
        select1.selectByIndex(1);  // выбор размера М
        WebElement modelColorGreen = getDriver().findElement(By.xpath(".//a[@id='color_15']")); // выбор зеленого цвета
        modelColorGreen.click();  // FASHION - 01 шаг 17
        WebElement buttonAddToCart1 = getDriver().findElement(By.xpath(".//button[@class='exclusive']")); // кнопка Добавить в корзину для продукта
        buttonAddToCart1.click();  // FASHION - 02 шаг 18

        String parentWindowHandler1 = getDriver().getWindowHandle(); // сохраняем родительское окно
        String subWindowHandler1 = null;
        Set<String> handles1 = getDriver().getWindowHandles(); // получаем набор десрипторов окон
        Iterator<String> iterator1 = handles1.iterator();
        while (iterator1.hasNext()){
            subWindowHandler1 = iterator1.next();
        }
        getDriver().switchTo().window(subWindowHandler1); // переключаемся к всплывающему окну
        WebElement buttonClose = getDriver().findElement(By.xpath(".//span[@class='cross']")); // выбор белого цвета
        Thread.sleep(5000);
        buttonClose.click();  // FASHION - 02 шаг 19
        getDriver().switchTo().window(parentWindowHandler1);  // переключаемся к родительскому окну
        Actions builder5 = new Actions(getDriver()); // FASHION - 01 шаг 20 и 21
        builder5.moveToElement(getDriver().findElement(By.xpath(".//a[@title='View my shopping cart']"))).build().perform();
        WebElement buttonOrder = getDriver().findElement(By.xpath(".//a[@id='button_order_cart']")); // Кнопка Check Out
        buttonOrder.click();  // FASHION - 02 шаг 22
        // добавлен скроллинг
        jsExecutor.executeScript("window.scrollBy(0,550)");
        // валидация полученных данных
        String[] siteDataArrary = {
                String.valueOf(getDriver().findElement(By.id("summary_products_quantity")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//p[@class='product-name']/following::p[1]/a")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//p[@class='product-name']/following::p[2]/a")).getText()),
                String.valueOf(getDriver().findElement(By.id("total_product")).getText()),
                String.valueOf(getDriver().findElement(By.id("total_price_without_tax")).getText()),
                String.valueOf(getDriver().findElement(By.id("total_price")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//span[@class='address_city']")).getText())
        };
        String[] expectedDataArrary = {
                "2 Products",
                "Faded Short Sleeve T-shirts",
                "Printed Chiffon Dress",
                "$32.91",
                "$34.91",
                "$34.91",
                "Hollywood, Florida 13139"
        };
        assertArrayEquals(siteDataArrary, expectedDataArrary);
        Thread.sleep(5000); // исключительно, чтобы заметить результат

    }

    @Test
    @DisplayName("Fashion - 3")
    void placeOrder() throws InterruptedException {

        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("window.scrollBy(0,750)");
        Actions builder3 = new Actions(getDriver()); // Поскольку incognito и тесты атомизированы - это небольшое предусловие
        builder3.moveToElement(getDriver().findElement(By.xpath(".//img[@alt='Faded Short Sleeve T-shirts']"))).build().perform();
        WebElement buttonAddToCart = getDriver().findElement(By.xpath(".//a[@title='Add to cart']")); // кнопка Add To Cart
        String parentWindowHandler = getDriver().getWindowHandle(); // сохраняем родительское окно
        String subWindowHandler = null;
        buttonAddToCart.click();  // добавляем товар в корзину
        Set<String> handles = getDriver().getWindowHandles(); // получаем набор десрипторов окон
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        getDriver().switchTo().window(subWindowHandler); // переключаемся к всплывающему окну
        Thread.sleep(10000l);
        WebElement buttonContinue = getDriver().findElement(By.xpath(".//span[@class='continue btn btn-default button exclusive-medium']")); // выбор белого цвета
        buttonContinue.click();  // продолжаем покупки
        getDriver().switchTo().window(parentWindowHandler);  // переключаемся к родительскому окну
        Actions builder5 = new Actions(getDriver()); // FASHION - 01 шаг 20 и 21
        builder5.moveToElement(getDriver().findElement(By.xpath(".//a[@title='View my shopping cart']"))).build().perform();
        WebElement buttonOrder = getDriver().findElement(By.xpath(".//a[@id='button_order_cart']")); // Кнопка Check Out
        buttonOrder.click();  // FASHION - 02 шаг 1
        WebElement buttonProceed = getDriver().findElement(By.xpath(".//a[contains(@class,'standard-checkout')] ")); // Кнопка Check Out
        buttonProceed.click();  // FASHION - 03 шаг 2 (шаг 3 и 4 выполнен в предусловии BeforeAll)
        jsExecutor.executeScript("window.scrollBy(0,500)");
        // валидация полученных данных FASHION - 03 шаг 5
        String[] siteDataArrary = {
                String.valueOf(getDriver().findElement(By.xpath(".//ul[@id='address_delivery']//li[contains(@class,'address_lastname')]")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//ul[@id='address_delivery']//li[contains(@class,'address_postcode')] ")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//ul[@id='address_delivery']//li[contains(@class,'address_phone_mobile')] ")).getText())
        };
        String[] expectedDataArrary = {
                "Alex Krutikov",
                "Hollywood, Florida 13139",
                "3055658989"
        };
        assertArrayEquals(siteDataArrary, expectedDataArrary);
        WebElement buttonProceed1 = getDriver().findElement(By.xpath(".//button[@name='processAddress']")); // Кнопка Proceed to CheckOut
        buttonProceed1.click();  // FASHION - 03 шаг 6
        WebElement selectorAgree = getDriver().findElement(By.xpath(".//input[@id='cgv']")); // Селектор Я Согласен
        selectorAgree.click();  // FASHION - 03 шаг 7
        WebElement buttonProceed2 = getDriver().findElement(By.xpath(".//button[@name='processCarrier']")); // Кнопка Proceed to CheckOut
        buttonProceed2.click();  // FASHION - 03 шаг 8
        WebElement linkPayByCheck = getDriver().findElement(By.xpath(".//a[@class='cheque']")); // Кнопка PayByCheck
        linkPayByCheck.click();  // FASHION - 03 шаг 9
        WebElement buttonIConfirm = getDriver().findElement(By.xpath(".//button[contains(@class,'button-medium')]")); // Кнопка I confirm
        buttonIConfirm.click();  // FASHION - 03 шаг 10
        // Проверка данных на сайте
        String[] siteDataArrary1 = {
                String.valueOf(getDriver().findElement(By.xpath(".//p[@class='alert alert-success']")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//span[@class='ajax_cart_no_product']")).getText())
        };
        String[] expectedDataArrary1 = {
                "Your order on My Store is complete.",
                "(empty)"
        };
        assertArrayEquals(siteDataArrary1, expectedDataArrary1);
        Thread.sleep(5000); // исключительно, чтобы заметить результат
    }

    @Test
    @DisplayName("Fashion - 4")
    void checkOrderHistory() throws InterruptedException {

        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        WebElement buttonAccount = getDriver().findElement(By.xpath(".//a[@class='account']")); // Кнопка Входа в аккаунт
        buttonAccount.click();  // FASHION - 04 шаг 1
        WebElement buttonOrderHistory = getDriver().findElement(By.xpath(".//a[@title='Orders']")); // Кнопка Входа в Заказы
        buttonOrderHistory.click();  // FASHION - 04 шаг 2
        Thread.sleep(1000); // время для отрисовки страницы
        jsExecutor.executeScript("window.scrollBy(0, window.innerHeight)");
        WebElement orderLink = getDriver().findElement(By.xpath(".//a[contains(text(),'WFAWJIFAN')]")); // ссылка на искомый заказ
        orderLink.click();  // FASHION - 04 шаг 3
        Thread.sleep(1000); // время для отрисовки страницы
        jsExecutor.executeScript("window.scrollBy(0,1200)");
        // Проверка данных заказа
        String[] siteDataArrary = {
                String.valueOf(getDriver().findElement(By.xpath(".//ul[@class='address alternate_item box']//span[@class='address_lastname']")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//tr[@class='totalprice item']/td[2]")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//h1[@class='page-heading']")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//p[@class='dark']")).getText())
        };
        String[] expectedDataArrary = {
                "Krutikov",
                "$88.91",
                "FOLLOW YOUR ORDER'S STATUS STEP-BY-STEP",
                "Order Reference WFAWJIFAN -- placed on 04/03/2022"
        };
        assertArrayEquals(siteDataArrary, expectedDataArrary);

        Thread.sleep(5000); // исключительно, чтобы заметить результат
    }
    @Test
    @DisplayName("Fashion - 5")// Добавление сообщения к существующему заказу
    void addAMessage() throws InterruptedException {

        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        WebElement buttonAccount = getDriver().findElement(By.xpath(".//a[@class='account']")); // Кнопка Входа в аккаунт
        buttonAccount.click();  // FASHION - 04 шаг 1
        WebElement buttonOrderHistory = getDriver().findElement(By.xpath(".//a[@title='Orders']")); // Кнопка Входа в Заказы
        buttonOrderHistory.click();  // FASHION - 04 шаг 2
        Thread.sleep(1000); // время для отрисовки страницы
        jsExecutor.executeScript("window.scrollBy(0, window.innerHeight)");
        WebElement orderLink = getDriver().findElement(By.xpath(".//a[contains(text(),'WFAWJIFAN')]")); // ссылка на искомый заказ
        orderLink.click();  // FASHION - 04 шаг 3
        Thread.sleep(1000); // время для отрисовки страницы
        jsExecutor.executeScript("window.scrollBy(0, window.innerHeight)");
        Actions sendMessage = new Actions(getDriver());
        sendMessage.sendKeys(getDriver().findElement(By.xpath(".//textarea")),"Some message") // вводим сообщение в поле
                .pause(1000l)
                .click(getDriver().findElement(By.xpath(".//button[@name='submitMessage']")))
                .build()
                .perform();
        // Проверка данных и статуса сообщения
        String[] siteDataArrary = {
                String.valueOf(getDriver().findElement(By.xpath(".//p[@class='alert alert-success']")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//th[contains(text(),'Message')]/../../following::tbody/tr/td[2]")).getText())
        };
        String[] expectedDataArrary = {
                "Message successfully sent",
                "Some message"
        };
        assertArrayEquals(siteDataArrary, expectedDataArrary);

        Thread.sleep(5000); // исключительно, чтобы заметить результат
    }

    @Test
    @DisplayName("Fashion - 6")// Проверка корректности сохраненного в аккаунте адреса
    void checkMyAddresses() throws InterruptedException {

        WebElement buttonAccount = getDriver().findElement(By.xpath(".//a[@class='account']")); // Кнопка Входа в аккаунт
        buttonAccount.click();
        WebElement buttonAddresses = getDriver().findElement(By.xpath(".//a[@title='Addresses']")); // Кнопка Входа в сохраненные адреса аккаунта
        buttonAddresses.click();
        Thread.sleep(1000); // время для отрисовки страницы
        // Проверка данных страницы и сохраненных адресов
        String[] siteDataArrary = {
                String.valueOf(getDriver().findElement(By.xpath(".//h1")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//h3")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//h3/../following::li/span")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//h3/../following::li/span[2]")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//span[@class='address_address1']")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//span[@class='address_address1']/../following::li")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//span[@class='address_address1']/../following::li[2]")).getText()),
                String.valueOf(getDriver().findElement(By.xpath(".//span[@class='address_phone_mobile']")).getText())
        };
        String[] expectedDataArrary = {
                "MY ADDRESSES",
                "MY ADDRESS",
                "Alex",
                "Krutikov",
                "Collins Ave. 23",
                "Hollywood, Florida 13139",
                "United States",
                "3055658989"
        };
        assertArrayEquals(siteDataArrary, expectedDataArrary);

        Thread.sleep(5000); // исключительно, чтобы заметить результат
    }

    public static WebDriver getDriver() {
        return driver;
    }

}
