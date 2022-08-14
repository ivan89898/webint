import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class AppOrderTest {
    private WebDriver driver;

    @BeforeAll
    static void SetUpAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\driver\\chromedriver.exe");
    }

    @BeforeEach
    void SetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void TearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void ShouldTestV1() throws InterruptedException {
        driver.get("http://localhost:9999");
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        inputs.get(0).sendKeys("Вася");
        inputs.get(1).sendKeys("+75555555555");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.className("Success_successBlock__2L3Cw")).getText();
        Assertions.assertEquals(expected, actual);
    }
}
