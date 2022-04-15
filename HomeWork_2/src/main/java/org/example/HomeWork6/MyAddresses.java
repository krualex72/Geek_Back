package org.example.HomeWork6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MyAddresses extends AbstractPage{

    public void validateResult() {
        String[] siteDataArrary = {
                String.valueOf(getWebDriver().findElement(By.xpath(".//h1")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//h3")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//h3/../following::li/span")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//h3/../following::li/span[2]")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//span[@class='address_address1']")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//span[@class='address_address1']/../following::li")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//span[@class='address_address1']/../following::li[2]")).getText()),
                String.valueOf(getWebDriver().findElement(By.xpath(".//span[@class='address_phone_mobile']")).getText())
        };
        String[] expectedDataArrary = {
                "MY ADDRESSES",
                "MY ADDRESS",
                "Alex",
                "Krutikov",
                "Collins Ave. 23",
                "Hollywood, Florida 13139",
                "United States",
                "3055658989"
        };
        assertArrayEquals(siteDataArrary, expectedDataArrary, "Данные адреса неполны, некорректны или отсутствуют!");
    }

    public MyAddresses(WebDriver webDriver) {
        super(webDriver);
    }
}
