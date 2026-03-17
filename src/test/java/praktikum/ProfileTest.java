package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class ProfileTest extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    @Before
    @Override
    public void setUp() {
        super.setUp();

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue("Не удалось авторизоваться перед тестами профиля",
                mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    @Description("Проверка перехода в настройки профиля")
    public void goToProfileTest() {
        mainPage.clickProfileButton();
        profilePage.waitForLoad();
        String currentUrl = driver.getCurrentUrl();
        assertTrue("URL должен содержать /account/profile. Текущий: " + currentUrl,
                currentUrl.contains("/account/profile"));
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Клик по ссылке 'Конструктор'")
    public void fromProfileToConstructorTest() {
        mainPage.clickProfileButton();
        profilePage.waitForLoad();
        profilePage.clickConstructor();
        assertTrue("Конструктор не открылся", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка логаута из профиля")
    public void logoutTest() {
        mainPage.clickProfileButton();
        profilePage.waitForLoad();
        profilePage.clickLogout();
        assertTrue("Страница логина не загрузилась после выхода",
                loginPage.isLoginHeaderDisplayed());
    }

    @Test
    @DisplayName("Переход в конструктор по клику на логотип")
    @Description("Клик по логотипу Stellar Burgers из профиля")
    public void fromProfileToConstructorByLogoTest() {
        mainPage.clickProfileButton();
        profilePage.waitForLoad();
        profilePage.clickLogo();
        assertTrue("Логотип не вернул пользователя в конструктор",
                mainPage.isOrderButtonVisible());
    }
}