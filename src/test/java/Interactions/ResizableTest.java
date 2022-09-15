package Interactions;

import Base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Arrays;

public class ResizableTest extends TestBase {

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/resizable.php");
    }

    @Test
    @Tag("Regression")
    public void resizableSuccessfulTest() {

        WebElement resizableElement = driver.findElement(By.cssSelector("#resizable"));
        int resizablePadding = Integer.parseInt(resizableElement.getCssValue("padding").replaceAll("[^0-9]", ""));
        int resizableBorder = Integer.parseInt(Arrays.stream(resizableElement.getCssValue("border").split(" ")).findFirst().get().replaceAll("[^0-9]", ""));
        mySleep(1);
        WebElement resizableElementCorner = driver.findElement(By.cssSelector(".ui-icon-gripsmall-diagonal-se"));
        Actions resizeAction = new Actions(driver);
        resizeAction.clickAndHold(resizableElementCorner).moveByOffset(10 + (resizableBorder + resizablePadding) * 2, 0).release().perform();
        mySleep(1);
        resizeAction.clickAndHold(resizableElementCorner).moveByOffset(0, 10 + (resizableBorder + resizablePadding) * 2).release().perform();
        mySleep(1);
        resizeAction.clickAndHold(resizableElementCorner).moveByOffset(10 + (resizableBorder + resizablePadding) * 2, 10 + (resizableBorder + resizablePadding) * 2).release().perform();
        mySleep(1);
    }

    private void mySleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
