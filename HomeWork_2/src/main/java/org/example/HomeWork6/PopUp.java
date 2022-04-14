package org.example.HomeWork6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PopUp extends AbstractPage {

    @FindBy(xpath = ".//span[@class='continue btn btn-default button exclusive-medium']") // кнопка Continue Shopping
    private WebElement buttonContinue;

    public PopUp clickButtonContinue(){
        buttonContinue.click();
        return this;
    }

    public PopUp(WebDriver webDriver) {
        super(webDriver);
    }
}
