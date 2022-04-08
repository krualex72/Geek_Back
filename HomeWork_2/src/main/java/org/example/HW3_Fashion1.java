package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class HW3_Fashion1 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup(); // установка WebDriver
        ChromeOptions options = new ChromeOptions(); // установка опций для Хрома
        options.addArguments("--incognito");  // режим инкогнито
        //options.addArguments("--headless"); // не открываем хром как окно
        options.addArguments("start-maximized"); // максимальный размер окна браузера


        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/"); // FASHION - 01 шаг 1

        WebElement searchField = driver.findElement(By.id("search_query_top")); // поле ввода запроса поиска
        WebElement buttonSearch = driver.findElement(By.name("submit_search"));
//        WebElement webElement2 = driver.findElement(By.cssSelector("input.gLFyf.gsfi"));
//        WebElement webElement3 = driver.findElement(By.xpath(".//input[@name='q']"));

        try {
            WebElement webElementError = driver.findElement(By.name("error"));
        } catch (NoSuchElementException e){
            System.out.println(e.getSupportUrl());
        }

        searchField.sendKeys("Blouse"); // FASHION - 01 шаг 2
        buttonSearch.click(); // FASHION - 01 шаг 2 (продолжение)

        Actions builder = new Actions(driver); // FASHION - 01 шаг 3
        builder.moveToElement(driver.findElement(By.xpath(".//a[@class='product_img_link']"))).build().perform();
        WebElement buttonMore = driver.findElement(By.xpath(".//a[@class='button lnk_view btn btn-default']")); // кнопка More
        buttonMore.click();  // FASHION - 01 шаг 4
        Actions builder1 = new Actions(driver); // FASHION - 01 шаг 5
        builder1.moveToElement(driver.findElement(By.xpath(".//div[@class='selector']"))).build().perform();
        WebElement selectSize = driver.findElement(By.tagName("select"));
        Select select = new Select(selectSize);
        select.selectByIndex(1);  // выбор размера М
        WebElement modelColorWhite = driver.findElement(By.xpath(".//a[@id='color_8']")); // выбор белого цвета
        modelColorWhite.click();  // FASHION - 01 шаг 6

//      Второй тест-кейс FASHION - 02
        driver.navigate().to("http://automationpractice.com/"); // FASHION - 02 шаг 1
        Actions builder3 = new Actions(driver); // FASHION - 02 шаг 2 и 3
        builder3.moveToElement(driver.findElement(By.xpath(".//img[@alt='Faded Short Sleeve T-shirts']"))).build().perform();
        WebElement buttonAddToCart = driver.findElement(By.xpath(".//a[@title='Add to cart']")); // кнопка Add To Cart

        String parentWindowHandler = driver.getWindowHandle(); // сохраняем родительское окно
        String subWindowHandler = null;

        buttonAddToCart.click();  // FASHION - 02 шаг 4

        Set<String> handles = driver.getWindowHandles(); // получаем набор десрипторов окон
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler); // переключаемся к всплывающему окну
        WebElement buttonContinue = driver.findElement(By.xpath(".//span[@class='continue btn btn-default button exclusive-medium']")); // выбор белого цвета
        Thread.sleep(10000l);
        buttonContinue.click();  // FASHION - 02 шаг 5
        driver.switchTo().window(parentWindowHandler);  // переключаемся к родительскому окну
        Actions builder4 = new Actions(driver); // FASHION - 02 шаг 14 (предыдущие шаги пропущены, посколку фактически повторяют итерации)
        builder4.moveToElement(driver.findElement(By.xpath(".//img[@alt='Printed Chiffon Dress']"))).build().perform();
        WebElement buttonMore1 = driver.findElement(By.xpath(".//a[@data-id-product='7']/following-sibling::*[1]")); // кнопка More для продукта
        buttonMore1.click();  // FASHION - 02 шаг 15
        //Thread.sleep(50000l);
        WebElement selectSize1 = driver.findElement(By.tagName("select"));
        Select select1 = new Select(selectSize1); //FASHION - 01 шаг 16
        select1.selectByIndex(1);  // выбор размера М
        WebElement modelColorGreen = driver.findElement(By.xpath(".//a[@id='color_15']")); // выбор зеленого цвета
        modelColorGreen.click();  // FASHION - 01 шаг 17
        WebElement buttonAddToCart1 = driver.findElement(By.xpath(".//button[@class='exclusive']")); // кнопка Добавить в корзину для продукта
        buttonAddToCart1.click();  // FASHION - 02 шаг 18

        String parentWindowHandler1 = driver.getWindowHandle(); // сохраняем родительское окно
        String subWindowHandler1 = null;
        Set<String> handles1 = driver.getWindowHandles(); // получаем набор десрипторов окон
        Iterator<String> iterator1 = handles1.iterator();
        while (iterator1.hasNext()){
            subWindowHandler1 = iterator1.next();
        }
        driver.switchTo().window(subWindowHandler1); // переключаемся к всплывающему окну
        WebElement buttonClose = driver.findElement(By.xpath(".//span[@class='cross']")); // выбор белого цвета
        Thread.sleep(5000l);
        buttonClose.click();  // FASHION - 02 шаг 19
        driver.switchTo().window(parentWindowHandler1);  // переключаемся к родительскому окну

        Actions builder5 = new Actions(driver); // FASHION - 01 шаг 20 и 21
        builder5.moveToElement(driver.findElement(By.xpath(".//a[@title='View my shopping cart']"))).build().perform();
        WebElement buttonOrder = driver.findElement(By.xpath(".//a[@id='button_order_cart']")); // Кнопка Check Out
        //Thread.sleep(10000l);
        buttonOrder.click();  // FASHION - 02 шаг 22

        //      Третий тест-кейс FASHION - 03 (шаг 1 = шаг 22 предыдущего теста)
        WebElement buttonProceed = driver.findElement(By.xpath(".//a[contains(@class,'standard-checkout')] ")); // Кнопка Check Out
        //Thread.sleep(10000l);
        buttonProceed.click();  // FASHION - 03 шаг 2
        WebElement fieldEmail = driver.findElement(By.xpath(".//input[@id='email']")); // поле email для зарегистрированных пользователей
        //Thread.sleep(10000l);
        fieldEmail.sendKeys("krutikov-alex@ya.ru");;  // FASHION - 03 шаг 3/1
        WebElement fieldPwd = driver.findElement(By.xpath(".//input[@id='passwd']")); // поле пароля для зарегистрированных пользователей
        //Thread.sleep(10000l);
        fieldPwd.sendKeys("TestPass");;  // FASHION - 03 шаг 3/2
        WebElement buttonSingIn = driver.findElement(By.xpath(".//button[@id='SubmitLogin'] ")); // Кнопка Sing In
        buttonSingIn.click();  // FASHION - 03 шаг 4
        String fio = driver.findElement(By.xpath(".//ul[@id='address_delivery']//li[contains(@class,'address_lastname')]")).getText(); // FASHION - 03 шаг 5
//        System.out.println(fio);
        assertEquals("Alex Krutikov", fio);// выборочная проверка заполнения полей формы из профиля
        String postcode = driver.findElement(By.xpath(".//ul[@id='address_delivery']//li[contains(@class,'address_postcode')] ")).getText();  // FASHION - 03 шаг 5
//        System.out.println(postcode);
        assertEquals("Hollywood, Florida 13139", postcode); // выборочная проверка заполнения полей формы из профиля
        String phone_mobile = driver.findElement(By.xpath(".//ul[@id='address_delivery']//li[contains(@class,'address_phone_mobile')] ")).getText();  // FASHION - 03 шаг 5
//        System.out.println(phone_mobile);
        assertEquals("3055658989", phone_mobile);// выборочная проверка заполнения полей формы из профиля
        WebElement buttonProceed1 = driver.findElement(By.xpath(".//button[@name='processAddress']")); // Кнопка Proceed to CheckOut
        buttonProceed1.click();  // FASHION - 03 шаг 6
        WebElement selectorAgree = driver.findElement(By.xpath(".//input[@id='cgv']")); // Селектор Я Согласен
        selectorAgree.click();  // FASHION - 03 шаг 7
        WebElement buttonProceed2 = driver.findElement(By.xpath(".//button[@name='processCarrier']")); // Кнопка Proceed to CheckOut
        buttonProceed2.click();  // FASHION - 03 шаг 8
        WebElement linkPayByCheck = driver.findElement(By.xpath(".//a[@class='cheque']")); // Кнопка PayByCheck
        linkPayByCheck.click();  // FASHION - 03 шаг 9
        WebElement buttonIConfirm = driver.findElement(By.xpath(".//button[contains(@class,'button-medium')]")); // Кнопка I confirm
        buttonIConfirm.click();  // FASHION - 03 шаг 10
        String result = driver.findElement(By.xpath(".//p[@class='alert alert-success']")).getText(); // FASHION - 03 шаг 10 проверка результата
//        System.out.println(result);
        assertEquals("Your order on My Store is complete.", result);
        String emptyCart = driver.findElement(By.xpath(".//span[@class='ajax_cart_no_product']")).getText(); // FASHION - 03 шаг 10 проверка результата
        assertEquals("(empty)", emptyCart);

        WebElement myLogo = driver.findElement(By.xpath(".//div[@id='header_logo']")); //FASHION - 03 шаг 10 - фактически постусловие
        myLogo.click();

        //      Четвертый тест-кейс FASHION - 04
        WebElement buttonAccount = driver.findElement(By.xpath(".//a[@class='account']")); // Кнопка Входа в аккаунт
        buttonAccount.click();  // FASHION - 04 шаг 1
        WebElement buttonOrderHistory = driver.findElement(By.xpath(".//a[@title='Orders']")); // Кнопка Входа в аккаунт
        buttonOrderHistory.click();  // FASHION - 04 шаг 2

        WebElement orderLink = driver.findElement(By.xpath(".//a[contains(text(),'WFAWJIFAN')]")); // ссылка на искомый заказ
        orderLink.click();  // FASHION - 04 шаг 3
        String lastName = driver.findElement(By.xpath(".//ul[@class='address alternate_item box']//span[@class='address_lastname']")).getText(); // выборочная проверка результата
        assertEquals("Krutikov", lastName);
        String totalPrice = driver.findElement(By.xpath(".//tr[@class='totalprice item']/td[2]")).getText(); // FASHION - 03 шаг 10 проверка результата
        System.out.println(totalPrice);
        assertEquals("$88.91", totalPrice);


        // driver.quit();  //Завершаем работу с ресурсом
    }
}
