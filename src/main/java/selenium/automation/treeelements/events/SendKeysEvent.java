package selenium.automation.treeelements.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import selenium.automation.treeelements.finders.IFinder;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
public class SendKeysEvent extends ARootEvent{
    //private String Url = null;
    private final Map<String, String> properties = new HashMap<String, String>();
    
    private final List<IFinder> finders = new ArrayList<IFinder>();
    
    public SendKeysEvent(){
        setDefaults();
    }
    
    public SendKeysEvent(IFinder finder){
        setDefaults();
        addFinder(finder);
    }
    
    public SendKeysEvent(IFinder finder, String keys){
        setDefaults();
        addFinder(finder);
        properties.put("keys", keys);
    }
    
    public void setDefaults(){
        properties.put("name", "SendKeysName");
        properties.put("keys", "Text to input");
    }
    
    public void addFinder(IFinder finder){
        finders.removeAll(finders);
        finders.add(finder);
    }
    
    public IFinder getOneFinder(){
        if(finders.size() >= 1){
            return finders.get(finders.size() - 1);
        }
        return null;
    }
    
    public List<IFinder> getFinder(){
        return finders;
    }
    
    @Override
    public boolean run(WebDriver driver) {
        String text = getProperty("keys");
        driver.getTitle();
        
        if(text != null){
            if(driver != null){
                for(IFinder finder : finders){
                    finder.findElement(driver).sendKeys(text);
                }
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
