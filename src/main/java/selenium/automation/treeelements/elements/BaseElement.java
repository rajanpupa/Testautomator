package selenium.automation.treeelements.elements;

import selenium.automation.treeelements.events.IConfigurable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rajan Prasad Upadhyay
 */
public class BaseElement implements IConfigurable{

    //properties 
    protected String type = "Element";
    //protected String name = "Element";
    protected Map<String, String> properties = new HashMap<String, String>();

    //protected WebDriverManager dManager = new WebDriverManager();

    //methods
    public boolean execute() {
        return false;
    }

    public boolean execute(Map<String, String> properties) {
        return false;
    }

    //constructors
    public BaseElement() {
        properties.put("name", "Element");
    }

    public BaseElement(String name) {
        //this.name = name;
        setName(name);
    }
    
    

    //Setters and Getters
    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    //@Override
    public String getProperty(String key) {
        return properties.get(key);
    }

    public String getName() {
        return properties.get("name");
    }

    public void setName(String name){
        //this.name = name;
        properties.put("name", name);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return this.getName();
    }

}
