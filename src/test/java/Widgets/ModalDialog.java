package Widgets;

import Base.TestBase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ModalDialog extends TestBase {

    @BeforeEach
    private void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/modal-dialog.php");
    }

    @ParameterizedTest(name = "Creating new user successful test {0}")
    @CsvFileSource(resources = "/modalDialogUsersData.csv", delimiter = ',')
    @Tag("Reggresion")
    @DisplayName("Create new user test")
    public void createUserSuccessfulTest(String testNumber, String expectedName, String expectedMail, String expectedPassword) {
        driver.findElement(By.cssSelector("#create-user")).click();
        WebDriverWait waitForDialog = new WebDriverWait(driver, Duration.ofSeconds(3));
        waitForDialog.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#name")));
        driver.findElement(By.cssSelector("#name")).clear();
        driver.findElement(By.cssSelector("#name")).sendKeys(expectedName);
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys(expectedMail);
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys(expectedPassword);
        driver.findElement(By.xpath("//*[@class='ui-dialog-buttonset']//button[contains(text(),'Create')]")).click();
        List<WebElement> usersList = driver.findElements(By.cssSelector("#users tbody tr"));
        assertThat(usersList.get(1).findElements(By.cssSelector("td")).get(0).getText()).isEqualTo(expectedName);
        assertThat(usersList.get(1).findElements(By.cssSelector("td")).get(1).getText()).isEqualTo(expectedMail);
        assertThat(usersList.get(1).findElements(By.cssSelector("td")).get(2).getText()).isEqualTo(expectedPassword);
    }
}
