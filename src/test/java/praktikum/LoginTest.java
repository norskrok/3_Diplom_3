package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка успешной авторизации при переходе с главной страницы через кнопку 'Войти в аккаунт'")
    public void loginFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue("Кнопка 'Оформить заказ' не появилась, вход не выполнен",
                mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка перехода к форме логина и успешной авторизации через кнопку 'Личный кабинет' в хедере")
    public void loginFromProfileButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickProfileButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue("Не удалось авторизоваться через Личный кабинет",
                mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    @Description("Проверка возможности перехода на страницу логина через ссылку 'Войти' на странице регистрации и последующей авторизации")
    public void loginFromRegisterPage() {
        driver.get("https://stellarburgers.education-services.ru/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue("Не удалось авторизоваться через страницу регистрации",
                new MainPage(driver).isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка авторизации после перехода на страницу логина со страницы восстановления пароля (forgot password)")
    public void loginFromForgotPasswordPage() {
        driver.get("https://stellarburgers.education-services.ru/forgot-password");
        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.clickLoginLink();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue("Не удалось авторизоваться через страницу восстановления пароля",
                new MainPage(driver).isOrderButtonVisible());
    }
}