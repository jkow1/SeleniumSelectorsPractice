package Interactions;

import Base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Droppable extends TestBase {

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/droppable.php");
    }

    @ParameterizedTest
    @ValueSource(strings = "Dropped!")
    @Tag("Regression")
    public void droppableSuccessfulTest(String expectedResult) {
        WebElement dropElement = driver.findElement(By.cssSelector("#draggable"));
        WebElement targetElement = driver.findElement(By.cssSelector("#droppable"));
        (new Actions(driver)).dragAndDrop(dropElement, targetElement).perform();
        assertThat(targetElement.getText()).isEqualTo(expectedResult);
    }

}
