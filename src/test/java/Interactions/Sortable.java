package Interactions;

import Base.TestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Sortable extends TestBase {

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/sortable.php");
    }


    @ParameterizedTest(name = "Sortable test for list of {0} elements")
    @ValueSource(ints = {7})
    public void sortableSuccessfulTest(int maxElements) {
        ArrayList<Integer> intList = new ArrayList<>();
        for (int i = 1; i <= maxElements; i++) {
            intList.add(i);
        }
        Collections.shuffle(intList);
        System.out.println(intList);
        List<WebElement> elementsList = driver.findElements(By.cssSelector("#sortable li"));
        Point firstPosition = new Point(elementsList.get(elementsList.size() - 1).getLocation().getX(), elementsList.get(elementsList.size() - 1).getLocation().getY());
        Actions actions = new Actions(driver);
        intList.stream().
                forEach(i -> moveBy(driver.findElements(By.cssSelector("#sortable li")).stream()
                        .filter(e -> e.getText().equals("Item " + i))
                        .findAny()
                        .get(), firstPosition, actions));
        ArrayList<String> actualResult = new ArrayList<>();
        driver.findElements(By.cssSelector("#sortable li")).forEach(e -> actualResult.add(e.getText()));
        ArrayList<String> expectedResult = new ArrayList<>();
        intList.stream()
                .forEach(i -> expectedResult.add("Item " + i));
        assertThat(actualResult).isEqualTo(expectedResult);

    }

    private void moveBy(WebElement element, Point point, Actions actions) {
        System.out.println(element.getText());
        actions.dragAndDropBy(element, 0, -element.getLocation().getY() + point.y + 5).perform();
    }
}
