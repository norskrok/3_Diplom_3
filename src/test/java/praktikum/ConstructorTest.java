package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("Проверка, что после клика на другой раздел можно успешно вернуться к разделу «Булки»")
    public void testBunSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceSection();
        mainPage.clickBunSection();
        String currentTab = mainPage.getActiveTabName();
        assertEquals("Должен быть выбран раздел Булки", "Булки", currentTab);
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("Проверка переключения на вкладку «Соусы» в конструкторе")
    public void testSauceSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceSection();
        String currentTab = mainPage.getActiveTabName();
        assertEquals("Должен быть выбран раздел Соусы", "Соусы", currentTab);
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("Проверка переключения на вкладку «Начинки» в конструкторе")
    public void testFillingSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingSection();
        String currentTab = mainPage.getActiveTabName();
        assertEquals("Должен быть выбран раздел Начинки", "Начинки", currentTab);
    }
}