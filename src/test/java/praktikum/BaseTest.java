package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected UserClient userClient;
    protected User user;
    protected String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        String email = RandomStringUtils.randomAlphanumeric(10).toLowerCase() + "@yandex.ru";
        user = new User(email, "password123", "Ivan");
        ValidatableResponse response = userClient.create(user);
        accessToken = response.extract().path("accessToken");

        String browser = System.getProperty("browser", "chrome");

        if ("yandex".equals(browser)) {
            setUpYandex();
        } else {
            setUpChrome();
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://stellarburgers.education-services.ru/");
    }

    public void setUpChrome() {
        WebDriverManager.chromedriver().browserVersion("146.0.7680.80").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
    }

    public void setUpYandex() {
        System.setProperty("webdriver.chrome.driver", "drivers/yandexdriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        options.addArguments("--incognito");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.out.println("Драйвер уже был закрыт или не отвечает: " + e.getMessage());
        } finally {
            if (accessToken != null) {
                userClient.delete(accessToken);
            }
        }
    }
}