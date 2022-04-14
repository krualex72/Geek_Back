package org.example.HomeWork6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {

    @FindBy(id = "email") // поле ввода email
    private WebElement inputEmail;

    @FindBy(id = "passwd") // поле ввода пароля
    private WebElement inputPasswd;

    @FindBy(id = "SubmitLogin") // кнопка Submit
    private WebElement submit;

    public void toLoginIn(){
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.elementToBeClickable(inputEmail));
        Actions logIn = new Actions(getWebDriver());
        logIn.sendKeys(inputEmail,"krutikov-alex@ya.ru")
                .pause(1000l)
                .sendKeys(inputPasswd,"TestPass")
                .pause(1000l)
                .click(submit)
                .build()
                .perform();
    }

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }
}
