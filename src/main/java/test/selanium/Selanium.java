/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.selanium;

import com.green.rukh.gui.frames.BaseFrame;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 *
 * @author "Rajan Prasad Upadhyay"
 */
public class Selanium {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Selanium selanium = new Selanium();
//        selanium.Example2();
        
        //gui
        BaseFrame.main(args);
        
    }
    
    public void Example1(){
        // Create a new instance of the html unit driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        WebDriver driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // And now use this to visit Google
        driver.get("http://localhost/wordpress");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("s"));

        element.clear();
        element.sendKeys("Image");
        
        driver.findElement(By.id("searchsubmit")).click();

        System.out.println("Page title is: " + driver.getTitle());

        //System.out.println(driver.getPageSource());
    }
    
    public void Example2(){
        // The Firefox driver supports javascript 
        WebDriver driver = new FirefoxDriver();

        // Go to the Google Suggest home page
        driver.get("http://localhost/wordpress");

        // Enter the query string "Cheese"
        WebElement query = driver.findElement(By.name("s"));
        query.sendKeys("Image");

        // Sleep until the div we want is visible or 5 seconds is over
        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            WebElement resultsDiv = driver.findElement(By.className("gssb_e"));

            // If results have been returned, the results are displayed in a drop down.
            if (resultsDiv.isDisplayed()) {
              break;
            }
        }

        // And now list the suggestions
        List<WebElement> allSuggestions = driver.findElements(By.xpath("//td[@class='gssb_a gbqfsf']"));
        
        for (WebElement suggestion : allSuggestions) {
            System.out.println(suggestion.getText());
        }

    }
    
}
