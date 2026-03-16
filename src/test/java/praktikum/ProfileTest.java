package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

public class ProfileTest extends BaseTest {
    private String email;
    private final String password = "password123";

    @Before
    @Override
    public void setUp() {
        super.setUp();
        email = "ivan" + new java.util.Random().nextInt(999999) + "@yandex.ru";

        driver.get("https://stellarburgers.education-services.ru/register");
        new RegisterPage(driver).register("Ivan", email, password);

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.urlContains("/login"));

        new LoginPage(driver).login(email, password);

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.urlToBe("https://stellarburgers.education-services.ru/"));
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void goToProfileTest() {
        new MainPage(driver).clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoad();

        String currentUrl = driver.getCurrentUrl();
        assertTrue("URL не содержит путь профиля: " + currentUrl, currentUrl.contains("/account/profile"));
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void fromProfileToConstructorTest() {
        new MainPage(driver).clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoad();
        profilePage.clickConstructor();

        assertTrue("Кнопка заказа не видна в конструкторе", new MainPage(driver).isOrderButtonVisible());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void logoutTest() {
        new MainPage(driver).clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoad();
        profilePage.clickLogout();

        assertTrue("Заголовок Вход не отобразился после логаута", new LoginPage(driver).isLoginHeaderDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип")
    public void fromProfileToConstructorByLogoTest() {
        new MainPage(driver).clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoad();

        profilePage.clickLogo();

        assertTrue("После клика на логотип не открылся конструктор",
                new MainPage(driver).isOrderButtonVisible());
    }
}