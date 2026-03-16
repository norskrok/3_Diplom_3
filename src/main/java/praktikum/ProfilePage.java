package praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
    private final WebDriver driver;

    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    private final By constructorLink = By.xpath(".//p[text()='Конструктор']");
    private final By logoLink = By.xpath(".//div[contains(@class, 'AppHeader_header__logo')]");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoad() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
    }

    public void clickLogout() {
        driver.findElement(logoutButton).click();
    }

    public void clickConstructor() {
        driver.findElement(constructorLink).click();
    }

    public void clickLogo() {
        driver.findElement(logoLink).click();
    }
}