package Widgets;

import Base.RandomElement;
import Base.TestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Autocomplete extends TestBase {

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/autocomplete.php");
    }

    @Test
    @Tag("Regression")
    public void autocompleteSuccessfulTest() {
        driver.findElement(By.cssSelector("#search")).sendKeys("a");
        List<WebElement> list = (driver.findElement(By.cssSelector("#ui-id-1"))).findElements(By.cssSelector(".ui-menu-item"));
        WebElement selectedElement = RandomElement.getRandomWebElement(list);
        System.out.println(selectedElement.getText());
        selectedElement.click();
        Assertions.assertThat(selectedElement.getText()).isEqualTo(driver.findElement(By.cssSelector("#search")).getText());
    }

}
