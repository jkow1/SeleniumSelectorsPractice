package Other;

import Base.TestBase;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class HighSiteTest extends TestBase {

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/high-site.php");
        driver.manage().window().fullscreen();
    }

    @Test
    @Tag("Regression")
    public void scrollingHighSiteSuccessfulTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(driver -> {
                    scrollDown();
                    return driver.findElement(By.cssSelector("#scroll-button")).isDisplayed();
                }
        );
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./image.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int height = driver.manage().window().getSize().height;
        String script = "window.scrollBy(0," + height / 2 + ");";
        js.executeScript(script);
    }

}
