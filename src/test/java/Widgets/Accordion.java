package Widgets;

import Base.TestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Accordion extends TestBase {

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/accordion.php");
    }

    @ParameterizedTest(name = "Test of accordion section {0}")
    @CsvFileSource(resources = "/accordionClassTestData.csv", delimiter = ';')
    @Tag("Regression")
    public void accordionSuccessfulTest(String testNumber, String accordionNumber) {
        String selector = "#ui-id-" + accordionNumber;
        String divWithTextSelector = "[aria-labelledby='ui-id-" + accordionNumber + "']";
        WebElement element = driver.findElement(By.cssSelector(selector));
        element.getAttribute("aria-expanded");
        if (!element.getAttribute("aria-expanded").equals("true"))
            element.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println((driver.findElement(By.cssSelector(divWithTextSelector)).getAttribute("textContent")));
        String acctualResult = element.getAttribute("aria-expanded");
        Assertions.assertThat(acctualResult).isEqualTo("true");
    }

}
