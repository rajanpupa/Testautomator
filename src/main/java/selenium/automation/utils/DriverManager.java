package selenium.automation.utils;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
public class DriverManager {
    
    private static WebDriver driver;
    
    public static WebDriver getWebDriver(){
        if(driver == null){
            return getFirefoxDriver();
        }else{
            return driver;
        }
    }
    
    public static WebDriver getFirefoxDriver(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
        return driver;
    }
    
    public static void quitDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }

    
}
