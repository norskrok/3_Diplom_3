package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.By;
import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    public void testSauceSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceSection();
        assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Соусы']")).isDisplayed());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    public void testFillingSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingSection();
        assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Начинки']")).isDisplayed());
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    public void testBunSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceSection();
        mainPage.clickBunSection();
        assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Булки']")).isDisplayed());
    }
}