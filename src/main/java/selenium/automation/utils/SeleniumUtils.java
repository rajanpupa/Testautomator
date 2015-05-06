package selenium.automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
public class SeleniumUtils {

    public static WebElement waitForElement(WebDriver driver,By by, long timeOutInSecs) {
        return new WebDriverWait(driver, timeOutInSecs)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    
}
