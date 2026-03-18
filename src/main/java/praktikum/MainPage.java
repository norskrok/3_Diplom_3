package praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By profileButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By orderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By modalOverlay = By.className("Modal_modal_overlay__x2ZCr");
    private final By bunSection = By.xpath(".//span[text()='Булки']");
    private final By sauceSection = By.xpath(".//span[text()='Соусы']");
    private final By fillingSection = By.xpath(".//span[text()='Начинки']");
    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание закрытия модального окна")
    public void waitForModalToDisappear() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(modalOverlay));
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        waitForModalToDisappear();
        WebElement element = driver.findElement(loginButton);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public void clickProfileButton() {
        waitForModalToDisappear();
        WebElement element = driver.findElement(profileButton);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Step("Проверка видимости кнопки 'Оформить заказ'")
    public boolean isOrderButtonVisible() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Переход в раздел 'Булки'")
    public void clickBunSection() { driver.findElement(bunSection).click(); }

    @Step("Переход в раздел 'Соусы'")
    public void clickSauceSection() { driver.findElement(sauceSection).click(); }

    @Step("Переход в раздел 'Начинки'")
    public void clickFillingSection() { driver.findElement(fillingSection).click(); }

    @Step("Получение названия активного раздела конструктора")
    public String getActiveTabName() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(activeTab))
                .getText();
    }
}