package Interactions;

import Base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DraggableTest extends TestBase {

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/draggable.php");
    }

    @Test
    @Tag("Regression")
    public void draggableSuccesfulTest() {
        WebElement dd = driver.findElement(By.cssSelector("#draggable"));
        Actions actions = new Actions(driver);
        Dimension websiteSize = driver.manage().window().getSize();
        int ddPadding = Integer.parseInt((dd.getCssValue("padding")).replaceAll("[^0-9]", ""));

        //right up
        actions.clickAndHold(dd).moveByOffset(websiteSize.width - dd.getSize().width - dd.getLocation().getX() - ddPadding - 10, -dd.getLocation().getY()).perform();
        mySleep(1);
        //right down
        actions.clickAndHold(dd).moveByOffset(websiteSize.width - dd.getLocation().getX() - dd.getSize().width - ddPadding - 10, websiteSize.height - 2 * dd.getSize().height - dd.getLocation().getY() + ddPadding + 10).perform();
        mySleep(1);
        //center
        actions.clickAndHold(dd).moveByOffset((websiteSize.width - dd.getLocation().getX() - dd.getSize().width - ddPadding - 10) - (websiteSize.width / 2 - dd.getSize().width / 2 - ddPadding - 10), (websiteSize.height - 2 * dd.getSize().height - dd.getLocation().getY() + ddPadding + 10) - (websiteSize.height / 2 - dd.getSize().height - ddPadding - 10)).perform();
        mySleep(1);
        //bottom left
        actions.clickAndHold(dd).moveByOffset(-(websiteSize.width - dd.getLocation().getX() - dd.getSize().width - ddPadding + 10), (websiteSize.height - 2 * dd.getSize().height - dd.getLocation().getY() + ddPadding + 10)).perform();
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
