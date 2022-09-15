package Basic;

import Base.TestBase;
import Logback.BasicLogger;
import ch.qos.logback.core.joran.conditional.ThenAction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Random;


public class FormTest extends TestBase {

    Logger logger = LoggerFactory.getLogger(FormTest.class);
    File inputFile = new File("src/main/resources/file.txt");

    @ParameterizedTest(name = "Successful form fill")
    @ValueSource(strings = "Form send with success")
    @DisplayName("Successful form fill")
    @Tag("Regression")
    public void shouldFillFormWithSuccess(String expectedResult) {

        driver.get("https://seleniumui.moderntester.pl/form.php");

        driver.findElement(By.cssSelector("#inputFirstName3")).sendKeys("Jan");
        driver.findElement(By.cssSelector("#inputLastName3")).sendKeys("Nowak");
        driver.findElement(By.cssSelector("#inputEmail3")).sendKeys("mail@testmail.sii.pl");
        chooseRandomElement(driver.findElements(By.xpath("//*[@name='gridRadiosSex']")));
        driver.findElement(By.cssSelector("#inputAge3")).sendKeys("123");
        chooseRandomElement(driver.findElements(By.xpath("//*[@name='gridRadiosExperience']")));
        driver.findElement(By.cssSelector("#gridRadios3")).click();
        driver.findElement(By.cssSelector("[for='gridCheckAutomationTester']")).click();
        Select selectContinents = new Select(driver.findElement(By.cssSelector("#selectContinents")));
        selectContinents.selectByValue("europe");
        Select select = new Select(driver.findElement(By.cssSelector("#selectSeleniumCommands")));
        selectCommands(select);
        driver.findElement(By.cssSelector("#chooseFile")).sendKeys(inputFile.getAbsolutePath());
        driver.findElement(By.cssSelector(".btn-primary")).click();
        String actualResult = driver.findElement(By.cssSelector("#validator-message")).getText();
        BasicLogger.logAssertedValues(logger,expectedResult,actualResult);
        Assertions.assertThat(actualResult).isEqualTo(expectedResult);

    }

    private void chooseRandomElement(List<WebElement> list) {
        list.get(new Random(System.currentTimeMillis()).nextInt(list.size())).click();
    }

    private void selectCommands(Select select) {
        if (select.isMultiple()) {
            select.selectByValue("switch-commands");
            select.selectByValue("wait-commands");
        }else{
            System.out.println("Select is not multiple" + select.getClass().getName());
        }
    }
}
