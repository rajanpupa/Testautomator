package selenium.automation.treeelements.finders;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.automation.treeelements.events.IConfigurable;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
public class ByIdFinder implements IFinder, IConfigurable{

    private Map<String, String> properties = new HashMap<String, String>();
    public static final String KEYWORD = "id";
    
    public ByIdFinder(){
        setDefaults();
    }
    
    public ByIdFinder(String id){
        setDefaults();
        properties.put(KEYWORD, id);
    }
    
    private void setDefaults(){
        properties.put("name", "IdFinder");
        properties.put(KEYWORD, "id");
    }
    
    //@Override
    public WebElement findElement(WebDriver driver) {
        return driver.findElement(By.id(properties.get(KEYWORD)));
    }

    //@Override
    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    //@Override
    public String getProperty(String key) {
        return properties.get(key);
    }

}
