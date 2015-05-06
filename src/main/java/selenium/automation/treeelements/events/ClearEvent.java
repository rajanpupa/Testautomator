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
public class ClearEvent extends ARootEvent {

    //private String Url = null;
    private final Map<String, String> properties = new HashMap<String, String>();

    private final List<IFinder> finders = new ArrayList<IFinder>();

    public ClearEvent() {
        setDefaults();
    }

    public ClearEvent(IFinder finder) {
        setDefaults();
        addFinder(finder);
    }

    public void setDefaults() {
        properties.put("name", "Write TextS");
        //properties.put("keys", "Text to input");
    }

    public void addFinder(IFinder finder) {
        finders.removeAll(finders);
        finders.add(finder);
    }

    public IFinder getOneFinder() {
        if (finders.size() >= 1) {
            return finders.get(finders.size() - 1);
        }
        return null;
    }

    public List<IFinder> getFinder() {
        return finders;
    }

    @Override
    public boolean run(WebDriver driver) {
        driver.getTitle();

        if (driver != null) {
            for (IFinder finder : finders) {
                finder.findElement(driver).clear();
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
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
