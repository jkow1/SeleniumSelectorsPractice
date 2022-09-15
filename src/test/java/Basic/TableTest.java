package Basic;

import Base.TestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class TableTest extends TestBase {

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/table.php");
    }

    @ParameterizedTest(name = "Successful table test")
    @CsvFileSource(resources = "/tableClassTestData.csv", delimiter = ',')
    @Tag("Regresion")
    public void getTableElementsSuccessfulTest(String state, String height) {
        List<WebElement> list = driver.findElements(By.cssSelector("tbody tr"))
                .stream().filter(webElement -> webElement.findElements(By.cssSelector("td")).get(2).getText().equals(state)
                        && Integer.parseInt(webElement.findElements(By.cssSelector("td")).get(3).getText()) > Integer.parseInt(height)).toList();
        list.stream()
                .forEach(webElement -> System.out.println("Peak \"" +
                        webElement.findElements(By.cssSelector("td")).get(0).getText() +
                        "\" has rank " +
                        webElement.findElements(By.cssSelector("th")).get(0).getText() +
                        "  and has height: " +
                        webElement.findElements(By.cssSelector("td")).get(3).getText())
                );
        Assertions.assertThat(list.isEmpty()).isFalse();
    }
}
