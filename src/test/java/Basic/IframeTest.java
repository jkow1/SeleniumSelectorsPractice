package Basic;

import Base.RandomElement;
import Base.TestBase;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class IframeTest extends TestBase {

    @Test
    @Tag("Regression")
    public void iframePracticeSuccessfulTest() {
        driver.get("https://seleniumui.moderntester.pl/iframes.php#");
        driver.switchTo().frame("iframe1");
        driver.findElement(By.cssSelector("#inputFirstName3")).sendKeys("Jan");
        driver.findElement(By.cssSelector("#inputSurname3")).sendKeys("Kowalski");
        driver.switchTo().defaultContent();
        driver.switchTo().frame("iframe2");
        driver.findElement(By.cssSelector("#inputLogin")).sendKeys("Login");
        driver.findElement(By.cssSelector("#inputPassword")).sendKeys("password");
        Select selectContinent = new Select(driver.findElement(By.cssSelector("#inlineFormCustomSelectPref")));
        selectContinent.selectByIndex((new Random()).nextInt(1, selectContinent.getOptions().size()));
        List<WebElement> expirienceList = driver.findElements(By.cssSelector(".form-check-input"));
        driver.findElement(By.cssSelector("#" + RandomElement.getRandomWebElement(expirienceList).getAttribute("id"))).click();
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector(".nav-link")).click();
    }
}
