package org.example.HomeWork6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ModelPage extends AbstractPage {

    @FindBy(xpath = ".//div[@class='selector']") // кнопка вызова выпадающего меню
    private WebElement selectButton;

    @FindBy(tagName = "select") // выпадающее меню выбoра размера
    private WebElement selector;

    @FindBy(xpath = ".//a[@id='color_8']") // кнопка выбора белого цвета
    private WebElement whiteColor;

    @FindBy(xpath = ".//select[@id='group_1']/option[2]") // кнопка вызова выпадающего меню
    private WebElement selectMSize;

    public void getSizeByIndex(int x) {
        Actions builder = new Actions(getWebDriver());
        builder.moveToElement(selectButton).build().perform();
        Select select = new Select(selector);
        select.selectByIndex(x);  // выбор размера 1 = М
//        whiteColor.click();
    }

    public String getSelectMSize() {
        return selectMSize.getText();
    }

    public void getColorWhite() {
        whiteColor.click();
    }

    public ModelPage(WebDriver webDriver) {
        super(webDriver);
    }
}
