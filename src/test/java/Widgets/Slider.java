package Widgets;

import Base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Slider extends TestBase {

    @BeforeEach
    public void getWebsite() {
        driver.get("https://seleniumui.moderntester.pl/slider.php");
    }

    @Test
    @Tag("Regression")
    public void sliderSuccessTest(){
        //driver.findElement(By.cssSelector("#custom-handle")).sendKeys(Keys.ARROW_RIGHT);
        moveSlider(50);
        assertThat(driver.findElement(By.cssSelector("#custom-handle")).getText().equals("50"));
        resetSlider();
        moveSlider(80);
        assertThat(driver.findElement(By.cssSelector("#custom-handle")).getText().equals("80"));
        resetSlider();
        moveSlider(80);
        assertThat(driver.findElement(By.cssSelector("#custom-handle")).getText().equals("80"));
        resetSlider();
        moveSlider(20);
        assertThat(driver.findElement(By.cssSelector("#custom-handle")).getText().equals("80"));
        resetSlider();
        moveSlider(0);
        assertThat(driver.findElement(By.cssSelector("#custom-handle")).getText().equals("80"));
    }

    private void moveSlider(int x){
        WebElement sliderElement = driver.findElement(By.cssSelector("#custom-handle"));

        for (int i = 0; i<x ;i++){
            sliderElement.sendKeys(Keys.ARROW_RIGHT);
        }
    }

    private void resetSlider(){
        driver.navigate().refresh();
    }

}
