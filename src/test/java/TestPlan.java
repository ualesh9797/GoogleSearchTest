import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestPlan {
    private static WebDriver driver;

    @BeforeSuite
    public static void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=ru");
        driver = new ChromeDriver(options);

        System.setProperty("webdriver.chrome.driver", Config.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Проверка успешного поиска")
    public static void checkSuccessSearch(){
        driver.get(Config.BASE_URL);
        WebForm webForm = new WebForm(driver);
        webForm.enterSearchTextWithResults();
        webForm.pressEnterKey();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webForm.verifySearchSuccess();
    }

    @Test(testName = "Проверка нулевого результата")
    public static void checkFailSearch(){
        driver.get(Config.BASE_URL);
        WebForm webForm = new WebForm(driver);
        webForm.enterSearchTextWithoutResults();
        webForm.pressEnterKey();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(webForm.verifySearchFail());
    }

    @Test(testName = "Проверка кнопки очистить")
    public static void checkClearButton(){
        driver.get(Config.BASE_URL);
        WebForm webForm = new WebForm(driver);
        webForm.enterSearchTextWithResults();
        webForm.clickClearButton();
        Assert.assertTrue(webForm.verifyInputEmpty());
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

}