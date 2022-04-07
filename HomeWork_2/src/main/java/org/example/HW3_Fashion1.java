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

public class HW3_Fashion1 {
    public static void main(String[] args) {

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
        buttonContinue.click();  // FASHION - 02 шаг 5
        driver.switchTo().window(parentWindowHandler);  // переключаемся к родительскому окну
        Actions builder4 = new Actions(driver); // FASHION - 02 шаг 6
        builder4.moveToElement(driver.findElement(By.xpath(".//img[@alt='Blouse']"))).build().perform();
        WebElement buttonQuickView = driver.findElement(By.xpath(".//a[@class='quick-view']")); // кнопка Quick View
//        String parentWindowHandler1 = driver.getWindowHandle(); // сохраняем родительское окно (повтор)
//        String subWindowHandler1 = null;
        buttonQuickView.click();  // FASHION - 02 шаг 5
//        Set<String> handles1 = driver.getWindowHandles(); // получаем набор десрипторов окон
//        Iterator<String> iterator1 = handles.iterator();
//        while (iterator1.hasNext()){
//            subWindowHandler1 = iterator1.next();
//        }
//        driver.switchTo().window(subWindowHandler1); // переключаемся к всплывающему окну

        // driver.quit();  //Завершаем работу с ресурсом
    }
}
