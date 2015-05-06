package selenium.automation.treeelements.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import selenium.automation.treeelements.events.IPageEvent;
import selenium.automation.utils.DriverManager;

/**
 * @author Rajan Prasad Upadhyay
 */
public class Page extends BaseElement {

    protected List<IPageEvent> events = new ArrayList();

    //constructors
    public Page() {
        this.type = "Page";
        setName("page-X");

        setDefaultProperties(properties);

    }

    public Page(String name) {
        setName(name);
        this.type = "Page";
        setDefaultProperties(properties);
    }

    //<editor-fold defaultstate="collapsed" desc="utility methods ">
    private static void setDefaultProperties(Map<String, String> properties) {
        //add some of the default properties
        properties.put("url", "http://");

    }

    
    //methods    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public List<IPageEvent> getEvents() {
        
        return events;

    }

    public void setEvents(List<IPageEvent> events) {
        this.events = events;
    }
    //</editor-fold>
    
    
    //</editor-fold>
    //methods    
    public boolean execute(Map<String, String> properties) {
        Map<String, String> clonedProperties = new HashMap<String, String>(properties);
        
        for (Map.Entry<String, String> entry : this.properties.entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            clonedProperties.put(entry.getKey(), entry.getValue());
        }
        
        return executeEvents();

        //return true;
    }

    private boolean executeEvents() {
        if (events.size() < 1) {
            return true;
        } else {
            for(IPageEvent event : events){
                if(!event.run(DriverManager.getWebDriver())){
                    return false;
                }
            }
        }
        return true;
    }

    //<editor-fold defaultstate="collapsed" desc="addEvent methods">
    /**
     *
     * @param page
     * @return itself
     */
    public Page addEvent(IPageEvent e) {
        if (e != null) {
            events.add(e);
        } else {
            events = new ArrayList<IPageEvent>();
            events.add(e);
        }
        return this;
    }

    public Page addEvent(int i, IPageEvent event) {
        if (events != null) {
            if (i > -1) {
                if (events.size() > i) {
                    events.add(i, event);
                } else {
                    events.add(event);
                }
            } else {
                events.add(0, event);
            }
        } else {
            events = new ArrayList<IPageEvent>();
            events.add(event);
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="remove Event methods">
    /**
     *
     * @return the last page, removing it from the actual list
     */
    public IPageEvent remove() {
        if (events.size() > 1) {
            return events.remove(events.size() - 1);
        } else {
            return null;
        }
    }

    /**
     *
     * @param i index of item to be removed
     * @return ith page if i is valid, else null
     */
    public IPageEvent remove(int i) {
        int size = events.size();
        IPageEvent p = null;
        if (i < 0) {
            return p;
        }
        if (size >= 1) {
            if (size > i) {
                return events.remove(i);
            }
        }
        return p;
    }
    //</editor-fold>

    
    //for unit testing
    public static void main(String[] args) {
        BaseElement page = new Page("Page1");
        System.out.println(page);
    }

}
