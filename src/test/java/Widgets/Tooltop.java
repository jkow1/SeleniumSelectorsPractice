package Widgets;

import Base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Duration;


public class Tooltop extends TestBase {

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/tooltip.php");
    }

    @Test
    @Tag("Regression")
    public void tooltipSuccessfulTest() {
        WebElement input = driver.findElement(By.cssSelector("#age"));
        (new Actions(driver)).moveToElement(driver.findElement(By.cssSelector("#age"))).click().build().perform();
        WebDriverWait waitForTooltip = new WebDriverWait(driver, Duration.ofSeconds(5));
        String tooltipTex = waitForTooltip.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-tooltip"))).getText();
        System.out.println(tooltipTex);
        assertThat(tooltipTex).isNotEmpty();
    }
}
