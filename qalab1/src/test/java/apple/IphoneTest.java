package apple;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.crypto.KeyAgreementSpi;

public class IphoneTest {
    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }

    @Test
    public void checkSmth(){
        WebElement qName = driver.findElement(By.name("q"));


        qName.sendKeys("Apple", Keys.ENTER);
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        String curURL = driver.getCurrentUrl();
        Assert.assertTrue(curURL.contains("Apple"));

        WebElement imgTab = driver.findElement(By.xpath("//a[text()='Картинки']"));
        imgTab.click();
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        Assert.assertNotNull(driver.findElement(By.xpath("//span[@class='rQEFy NZmxZe']"))); //unique web element that appear only if image tab is opened
    }


    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
