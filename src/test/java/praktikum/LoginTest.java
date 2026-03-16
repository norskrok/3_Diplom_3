package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {
    private String email;
    private final String password = "password123";
    private final String name = "Ivanivan";

    @Before
    @Override
    public void setUp() {
        super.setUp();
        email = "ivan" + new Random().nextInt(999999) + "@yandex.ru";

        driver.get("https://stellarburgers.education-services.ru/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(name, email, password);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginFromMainPage() {
        driver.get("https://stellarburgers.education-services.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        assertTrue("Кнопка 'Оформить заказ' не появилась", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginFromProfileButton() {
        driver.get("https://stellarburgers.education-services.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickProfileButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        assertTrue("Вход через Личный кабинет не удался", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    public void loginFromRegisterPage() {
        driver.get("https://stellarburgers.education-services.ru/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        assertTrue("Вход со страницы регистрации не удался", new MainPage(driver).isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginFromForgotPasswordPage() {
        driver.get("https://stellarburgers.education-services.ru/forgot-password");
        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.clickLoginLink();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        assertTrue("Вход через страницу восстановления пароля не удался",
                new MainPage(driver).isOrderButtonVisible());
    }
}