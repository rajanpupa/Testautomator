package selenium.automation.treeelements.events;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;

/**
 * @author Rajan Prasad Upadhyay
 */
public class NavigateEvent extends ARootEvent{
    //private String Url = null;
    private Map<String, String> properties = new HashMap<String, String>();
    
    public NavigateEvent(String url){
        //this.Url = url;
        properties.put("url", url);
        properties.put("name", "navigateEvent");
    }
    
    @Override
    public boolean run(WebDriver driver) {
        String Url = getProperty("url");
        
        if(Url != null){
            if(driver != null){
                driver.navigate().to(Url);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        //return "navigateEvent";
        return properties.get("name");
    }

    @Override
    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    @Override
    public String getProperty(String key) {
        return properties.get(key);
    }
}
