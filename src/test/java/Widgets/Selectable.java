package Widgets;

import Base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Selectable extends TestBase {

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/selectmenu.php");
    }

    @Test
    @Tag("Regression")
    public void selectOptionSuccessfulTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#speed-button")));
        driver.findElement(By.cssSelector("#speed-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#speed-menu")));
        List<WebElement> speedList = driver.findElements(By.cssSelector("#speed-menu li"));
        speedList.stream().skip((new Random()).nextInt(speedList.size())).findFirst().get().click();
        driver.findElement(By.cssSelector("#files-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#files-menu")));
        driver.findElements(By.cssSelector("#files-menu li[class='ui-menu-item']")).stream().filter(webElement -> webElement.findElement(By.cssSelector(".ui-menu-item-wrapper")).getText().equals("Some unknown file")).findAny().get().click();
        driver.findElement(By.cssSelector("#number-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#number-menu")));
        driver.findElements(By.cssSelector("#number-menu li")).get(5).click();
        driver.findElement(By.cssSelector("#salutation-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#salutation-menu")));
        List<WebElement> titleList = driver.findElements(By.cssSelector("#salutation-menu div[class='ui-menu-item-wrapper']"));
        titleList.get((new Random(System.currentTimeMillis()).nextInt(titleList.size()))).click();

    }
}
