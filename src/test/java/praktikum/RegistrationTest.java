package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    public void successRegistration() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickProfileButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        String email = "ivan" + new Random().nextInt(9999) + "@yandex.ru";
        registerPage.register("Ivan", email, "password123");

        assertTrue("Не отображается форма входа после регистрации", loginPage.isLoginHeaderDisplayed());
    }

    @Test
    @DisplayName("Ошибка при некорректном пароле (меньше 6 символов)")
    public void errorShortPassword() {
        driver.get("https://stellarburgers.education-services.ru/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Ivan", "ivan@yandex.ru", "12345");

        assertTrue("Сообщение об ошибке пароля не появилось", registerPage.isPasswordErrorDisplayed());
    }
}