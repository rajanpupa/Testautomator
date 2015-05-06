package test.selanium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 *
 * @author "Rajan Prasad Upadhyay"
 * @email   "rajanpupa@gmail.com"
 */
public class WordpressPostPublish {
    WebDriver driver = new FirefoxDriver();
    String baseUrl = "http://localhost";
    
    public static void main(String [] args){
        WordpressPostPublish abc = new WordpressPostPublish();
        abc.postArticle();
    }
    public void postArticle(){
        driver.get(baseUrl + "/wordpress/");
        driver.findElement(By.linkText("Log in")).click();
        driver.findElement(By.id("user_login")).clear();
        driver.findElement(By.id("user_login")).sendKeys("rajan");
        driver.findElement(By.id("user_pass")).clear();
        driver.findElement(By.id("user_pass")).sendKeys("rajan");
        driver.findElement(By.id("wp-submit")).click();
        driver.findElement(By.cssSelector("h3")).click();
        driver.findElement(By.linkText("Add New")).click();
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys("The world is at war, and the war is an economic one.");
        driver.findElement(By.id("content_ifr")).sendKeys("Those who Fail to act, are doomed to die.");
        driver.findElement(By.id("publish")).click();
        
        System.out.println("The world post is published");
    }

}
