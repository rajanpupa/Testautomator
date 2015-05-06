
package selenium.automation.treeelements.events;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
public class SnapShotEvent extends ARootEvent{

    //String fileName = "snapshot.png";
    private Map<String, String> properties = new HashMap<String, String>();
    
    public SnapShotEvent(String outputFullPath){
        //this.fileName = outputFullPath;
        properties.put("filename", outputFullPath);
        
        properties.put("name", "snapshotEvent");
    }
    
    @Override
    public boolean run(WebDriver driver) {
        try {
            driver.getTitle();//just to make selenium wait till the page is loaded
            
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Now you can do whatever you need to do with it, for example copy somewhere
            FileUtils.copyFile(scrFile, new File(getProperty("filename")));
            return true;
        } catch (IOException ex) {
            System.out.println("Error occured");
            Logger.getLogger(SnapShotEvent.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    @Override
    public String getProperty(String key) {
        return properties.get(key);
    }
    
    @Override
    public String toString(){
        //return "SnapshotEvent";
        return properties.get("name");
    }
}
