package org.example.HomeWork6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage {

    @FindBy(name = "submit_search") // кнопка поиска
    private WebElement searchButton;

    @FindBy(id = "search_query_top") // поле ввода запроса
    private WebElement inputSearch;

    @FindBy(xpath = ".//a[@class='homefeatured']") // кнопка Популярные
    private WebElement popular;

    @FindBy(xpath = ".//a[@class='blockbestsellers']") // кнопка Лучшие продажи
    private WebElement bestSellers;

    @FindBy(xpath = ".//img[@alt='Faded Short Sleeve T-shirts']") // модель T-shirts
    private WebElement tShirts;

    @FindBy(xpath = ".//img[@alt='Printed Chiffon Dress']") // модель Chiffon Dress
    private WebElement сhiffonDress;

    @FindBy(xpath = ".//a[@class='product_img_link']") // модель Blouse image
    private WebElement imageBlouse;

    @FindBy(xpath = ".//a[@class='button lnk_view btn btn-default']") // модель Blouse кнопка More
    private WebElement buttonMore;

    public MainPage clickPopular(){
        popular.click();
        return this;
    }

    public MainPage clickBestSellers(){
        bestSellers.click();
        return this;
    }

    public MainPage clickTShirts(){
        tShirts.click();
        return this;
    }

    public MainPage clickChiffonDress(){
        сhiffonDress.click();
        return this;
    }

    public void toSearch(String value){
        inputSearch.click();
        inputSearch.sendKeys(value);
        searchButton.click();
    }

    public void getMoreInfo() {
        Actions builder = new Actions(getWebDriver());
        builder.moveToElement(imageBlouse).build().perform();
        buttonMore.click();
    }

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }
}
