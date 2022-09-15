package Interactions;

import Base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SelectableTest extends TestBase {

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/selectable.php");
    }

    @ParameterizedTest
    @ValueSource(strings = "You've selected: #1 #3 #4.")
    @Tag("Regression")
    public void selectableSuccessfulTest(String expectedResult) {
        List<WebElement> list = driver.findElements(By.cssSelector("#selectable li"));
        (new Actions(driver)).keyDown(Keys.CONTROL)
                .click(list.get(0))
                .click(list.get(2))
                .click(list.get(3))
                .perform();
        String actualResult = driver.findElement(By.cssSelector("#feedback")).getText();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
