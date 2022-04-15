package org.example.HomeWork6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PopUp extends AbstractPage {

    @FindBy(xpath = ".//span[@class='continue btn btn-default button exclusive-medium']") // кнопка Continue Shopping
    private WebElement buttonContinue;

    @FindBy(xpath = ".//a[@title='Add to cart']") // кнопка Add to cart
    private WebElement buttonAddToCart;

    @FindBy(xpath = ".//span[@class='cross']") // кнопка закрыть окно
    private WebElement closeWindow;

    public PopUp clickCloseWindow(){
        closeWindow.click();
        return this;
    }

    public PopUp clickButtonContinue(){
        buttonContinue.click();
        return this;
    }

    public PopUp clickButtonAddToCart(){
        buttonAddToCart.click();
        return this;
    }

    public PopUp(WebDriver webDriver) {
        super(webDriver);
    }
}
