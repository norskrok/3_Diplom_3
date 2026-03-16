package praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;

    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By profileButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By orderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By modalOverlay = By.className("Modal_modal_overlay__x2ZCr");
    private final By bunSection = By.xpath(".//span[text()='Булки']");
    private final By sauceSection = By.xpath(".//span[text()='Соусы']");
    private final By fillingSection = By.xpath(".//span[text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForModalToDisappear() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated(modalOverlay));
    }

    public void clickLoginButton() {
        waitForModalToDisappear();
        driver.findElement(loginButton).click();
    }

    public void clickProfileButton() {
        waitForModalToDisappear();
        driver.findElement(profileButton).click();
    }

    public boolean isOrderButtonVisible() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
        return driver.findElement(orderButton).isDisplayed();
    }

    public void clickBunSection() { driver.findElement(bunSection).click(); }
    public void clickSauceSection() { driver.findElement(sauceSection).click(); }
    public void clickFillingSection() { driver.findElement(fillingSection).click(); }
}