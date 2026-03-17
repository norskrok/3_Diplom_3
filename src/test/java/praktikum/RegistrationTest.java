package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка регистрации нового пользователя через UI")
    public void successRegistration() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickProfileButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();
        RegisterPage registerPage = new RegisterPage(driver);
        String email = RandomStringUtils.randomAlphanumeric(10).toLowerCase() + "@yandex.ru";
        registerPage.register("Ivan", email, "password123");
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.urlContains("/login"));
        assertTrue("Не отображается форма входа после регистрации",
                loginPage.isLoginHeaderDisplayed());
    }

    @Test
    @DisplayName("Ошибка при некорректном пароле")
    @Description("Проверка появления ошибки 'Некорректный пароль' при вводе менее 6 символов")
    public void errorShortPassword() {
        driver.get("https://stellarburgers.education-services.ru/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Ivan", "ivan_ivan@yandex.ru", "12345");
        assertTrue("Сообщение об ошибке пароля не появилось",
                registerPage.isPasswordErrorDisplayed());
    }
}