package Basic;

import Base.TestBase;
import Logback.BasicLogger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class AlertTests extends TestBase {

    Logger logger = LoggerFactory.getLogger(AlertTests.class);

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/alerts.php");
    }

    @ParameterizedTest(name = "Successful Simple Alert Pop up test")
    @ValueSource(strings = "OK button pressed")
    @Tag("Regression")
    public void simpleAlertPopUpSuccessfulTest(String expectedResult) {
        driver.findElement(By.cssSelector("#simple-alert")).click();
        driver.switchTo().alert().accept();
        String actualResult = driver.findElement(By.cssSelector("#simple-alert-label")).getText();
        BasicLogger.logAssertedValues(logger, expectedResult, actualResult);
        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "Successful Prompt Alert box test")
    @ValueSource(strings = "Hello Lord Vader! How are you today?")
    @Tag("Regression")
    public void promptAlertBoxSuccessfulTest(String expectedResult) {
        driver.findElement(By.cssSelector("#prompt-alert")).click();
        driver.switchTo().alert().sendKeys("Lord Vader");
        driver.switchTo().alert().accept();
        String actualResult = driver.findElement(By.cssSelector("#prompt-label")).getText();
        BasicLogger.logAssertedValues(logger, expectedResult, actualResult);
        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "Successful Ok press Confirm Alert box test")
    @ValueSource(strings = "You pressed OK!")
    @Tag("Regression")
    public void confirmAlertBoxOkPressTest(String expectedResult) {
        driver.findElement(By.cssSelector("#confirm-alert")).click();
        driver.switchTo().alert().accept();
        String actualResult = driver.findElement(By.cssSelector("#confirm-label")).getText();
        BasicLogger.logAssertedValues(logger, expectedResult, actualResult);
        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "Successful Delayed alert test")
    @ValueSource(strings = "OK button pressed")
    @Tag("Regression")
    public void delayedAlertSuccessfulTest(String expectedResult) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("#delayed-alert")).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        String actualResult = driver.findElement(By.cssSelector("#delayed-alert-label")).getText();
        BasicLogger.logAssertedValues(logger, expectedResult, actualResult);
        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

}
