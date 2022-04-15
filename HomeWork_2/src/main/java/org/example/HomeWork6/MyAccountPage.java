package org.example.HomeWork6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends AbstractPage {

    @FindBy(xpath = ".//a[@title='Orders']") // кнопка Просмотра заказов
    private WebElement myOrders;

    @FindBy(xpath = ".//a[@title='Addresses']") // кнопка Add to cart
    private WebElement myAddresses;

    public MyAccountPage clickMyOrders(){
        myOrders.click();
        return this;
    }

    public MyAccountPage clickMyAddresses(){
        myAddresses.click();
        return this;
    }




    public MyAccountPage(WebDriver webDriver) {
        super(webDriver);
    }
}
