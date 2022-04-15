package org.example.HomeWork6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class OrderHistory extends AbstractPage {

    @FindBy(xpath = ".//a[contains(text(),'WFAWJIFAN')]") // проверяемый заказ
    private WebElement myOrder;

    @FindBy(xpath = ".//button[@name='submitMessage']") // отправка сообщения
    private WebElement submitMessage;

    @FindBy(xpath = ".//textarea") // поле ввода сообщения
    private WebElement inputMessage;

    @FindBy(xpath = ".//a[@title='Addresses']") // кнопка Add to cart
    private WebElement buttonAddToCart;

    public OrderHistory clickMyOrder(){
        myOrder.click();
        return this;
    }


    public OrderHistory clickButtonAddToCart(){
        buttonAddToCart.click();
        return this;
    }

    public void sendMessage(String message) {
        Actions sendMessage = new Actions(getWebDriver());
        sendMessage
                .sendKeys(inputMessage, message)
                .pause(1000l)
                .click(submitMessage)
                .build()
                .perform();
    }

    public void validateResult() {
        String[] siteDataArrary = {
                String.valueOf(getWebDriver().findElement(By.xpath(".//ul[@class='address alternate_item box']//span[@class='address_lastname']")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//tr[@class='totalprice item']/td[2]")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//h1[@class='page-heading']")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//p[@class='dark']")).getText())
        };
        String[] expectedDataArrary = {
                "Krutikov",
                "$88.91",
                "FOLLOW YOUR ORDER'S STATUS STEP-BY-STEP",
                "Order Reference WFAWJIFAN -- placed on 04/03/2022"
        };
        assertArrayEquals(siteDataArrary, expectedDataArrary, "Данные о заказе отсутствуют или некорректны!");
    }

    public void validateResult1() {
        String[] siteDataArrary = {
                String.valueOf(getWebDriver().findElement(By.xpath(".//p[@class='alert alert-success']")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//th[contains(text(),'Message')]/../../following::tbody/tr/td[2]")).getText())
        };
        String[] expectedDataArrary = {
                "Message successfully sent",
                "Some message"
        };
        assertArrayEquals(siteDataArrary, expectedDataArrary, "Сообщение неверно или не отправлено!");
    }

    public OrderHistory(WebDriver webDriver) {
        super(webDriver);
    }
}
