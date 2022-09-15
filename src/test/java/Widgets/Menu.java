package Widgets;

import Base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Menu extends TestBase {

    @BeforeEach
    private void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/menu-item.php");
    }

    @Test
    @Tag("Regression")
    public void clickOnMenuItemSuccessfulTest(){
        driver.findElement(By.cssSelector("#ui-id-9")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ui-id-13")));
        driver.findElement(By.cssSelector("#ui-id-13")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ui-id-16")));
        try {
            driver.findElement(By.cssSelector("#ui-id-16")).click();
        } catch (Exception e) {
            throw e;
        }
    }
}
