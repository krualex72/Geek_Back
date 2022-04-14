package org.example.HomeWork6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TopMenu extends AbstractPage {

    @FindBy(xpath = ".//a[@class='logout']") // кнопка выхода из аккаунта
    private WebElement signOut;

    @FindBy(xpath = ".//div[@id='contact-link']/a") // кнопка Контактов
    private WebElement contactUs;

    @FindBy(xpath = ".//a[@class='login']") // кнопка входа в аккаунт
    private WebElement logIn;

    @FindBy(xpath = ".//a[@class='account']") // кнопка перехода в ЛК
    private WebElement myAccount;

    @FindBy(xpath = ".//a[@title='My Store']") // кнопка перехода на главную страницу
    private WebElement mainPageLink;

    public TopMenu(WebDriver webDriver) { super(webDriver);}

    public TopMenu clickSignOut(){
        signOut.click();
        return this;
    }

    public TopMenu clickContactUs(){
        contactUs.click();
        return this;
    }

    public TopMenu clickLogIn(){
        logIn.click();
        return this;
    }

    public TopMenu clickMyAccount(){
        myAccount.click();
        return this;
    }

    public TopMenu clickMainPageLink(){
        mainPageLink.click();
        return this;
    }

}
