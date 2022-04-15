package org.example.HomeWork6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ShoppingCart extends AbstractPage {

    @FindBy(xpath = ".//a[@title='View my shopping cart']") // кнопка перехода к просмотру корзины
    private WebElement viewCart;

    @FindBy(xpath = ".//a[@id='button_order_cart']") // кнопка Check Out
    private WebElement checkOut;

    @FindBy(xpath = ".//a[contains(@class,'standard-checkout')]") // кнопка Proceed to Check Out
    private WebElement buttonProceed;

    @FindBy(xpath = ".//button[@name='processAddress']") // Кнопка Proceed to Address
    private WebElement buttonProceedToAddress;

    @FindBy(xpath = ".//input[@id='cgv']") // Селектор "Я согласен"
    private WebElement agreeSelector;

    @FindBy(xpath = ".//button[@name='processCarrier']") // Кнопка Proceed to Carrier
    private WebElement toCarrier;

    @FindBy(xpath = ".//a[@class='cheque']") // Кнопка PayByCheck
    private WebElement payByCheck;

    @FindBy(xpath = ".//button[contains(@class,'button-medium')]") // Кнопка I confirm
    private WebElement buttonIConfirm;

    public void viewShoppingCart() {
        Actions builder = new Actions(getWebDriver());
        builder.moveToElement(viewCart).build().perform();
    }

    public ShoppingCart checkOut() throws InterruptedException {
        Thread.sleep(1000);
        checkOut.click();
        return this;
    }

    public ShoppingCart checkAgreeSelector() {
        agreeSelector.click();
        return this;
    }

    public ShoppingCart proceedToAddress() {
        buttonProceedToAddress.click();
        return this;
    }

    public ShoppingCart proceedToCarrier() {
        toCarrier.click();
        return this;
    }

    public ShoppingCart clickButtonProceed() {
        buttonProceed.click();
        return this;
    }

    public ShoppingCart clickPayByCheck() {
        payByCheck.click();
        return this;
    }

    public ShoppingCart clickIConfirm() {
        buttonIConfirm.click();
        return this;
    }

    public void validateResult() {
        String[] siteDataArrary = {
                String.valueOf(getWebDriver().findElement(By.id("summary_products_quantity")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//p[@class='product-name']/following::p[1]/a")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//p[@class='product-name']/following::p[2]/a")).getText()),
                String.valueOf(getWebDriver().findElement(By.id("total_product")).getText()),
                String.valueOf(getWebDriver().findElement(By.id("total_price_without_tax")).getText()),
                String.valueOf(getWebDriver().findElement(By.id("total_price")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//span[@class='address_city']")).getText())
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
        assertArrayEquals(siteDataArrary, expectedDataArrary, "Данные в корзине неверные!");
    }

    public void validateResult1() {
        String[] siteDataArrary = {
                String.valueOf(getWebDriver().findElement(By.xpath(".//ul[@id='address_delivery']//li[contains(@class,'address_lastname')]")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//ul[@id='address_delivery']//li[contains(@class,'address_postcode')] ")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//ul[@id='address_delivery']//li[contains(@class,'address_phone_mobile')] ")).getText())
        };
        String[] expectedDataArrary = {
                "Alex Krutikov",
                "Hollywood, Florida 13139",
                "3055658989"
        };
        assertArrayEquals(siteDataArrary, expectedDataArrary, "Данные в корзине неверные!");
    }

    public void validateResult2() {
        String[] siteDataArrary1 = {
                String.valueOf(getWebDriver().findElement(By.xpath(".//p[@class='alert alert-success']")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//span[@class='ajax_cart_no_product']")).getText())
        };
        String[] expectedDataArrary1 = {
                "Your order on My Store is complete.",
                "(empty)"
        };
        assertArrayEquals(siteDataArrary1, expectedDataArrary1, "Заказ не оформлен!!!");
    }

    public ShoppingCart(WebDriver webDriver) {
        super(webDriver);
    }
}
