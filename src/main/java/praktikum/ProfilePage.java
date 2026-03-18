package praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By logoutButton = By.xpath("//button[text()='Выход']");
    private final By constructorLink = By.xpath("//p[contains(text(),'Конструктор')]");
    private final By logoLink = By.xpath("//div[contains(@class, 'AppHeader_header__logo')]/a");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ожидание загрузки страницы профиля")
    public void waitForLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
    }

    @Step("Нажатие кнопки 'Выход'")
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    @Step("Переход в 'Конструктор'")
    public void clickConstructor() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorLink)).click();
    }

    @Step("Клик по логотипу")
    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logoLink)).click();
    }
}