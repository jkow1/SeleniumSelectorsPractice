package Widgets;

import Base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import java.time.Duration;

public class ProgressbarTest extends TestBase {

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/progressbar.php");
    }

    @Test
    @Tag("Regression")
    public void waitForProgressBarSuccessfulTest(){
        WebDriverWait waitForProgressbar = new WebDriverWait(driver, Duration.ofSeconds(15));
        waitForProgressbar.until(ExpectedConditions.attributeContains(By.cssSelector("#progressbar"),"aria-valuenow","100"));
        assertThat(driver.findElement(By.cssSelector(".progress-label")).getText()).isEqualTo("Complete!");
    }
}
