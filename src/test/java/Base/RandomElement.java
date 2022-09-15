package Base;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class RandomElement {

    public static WebElement getRandomWebElement(List<WebElement> webElementT){
        Random random = new Random(System.currentTimeMillis());
        return webElementT.get(random.nextInt(webElementT.size()));
    }
}
