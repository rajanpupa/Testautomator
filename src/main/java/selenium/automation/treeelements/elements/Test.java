package selenium.automation.treeelements.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import selenium.automation.utils.DriverManager;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
public class Test extends BaseElement {

    List<Page> pages = new ArrayList<Page>();

    //constructors
    public Test() {
        this.type = "Test";
//        this.name = "Test";
        setName("Test");
        setDefaultProperties(properties);
    }

    public Test(String name) {
        //this.name = name;
        setName(name);
        this.type = "Test";
        setDefaultProperties(properties);
    }

    //<editor-fold defaultstate="collapsed" desc="utility methods ">
    private static void setDefaultProperties(Map<String, String> properties) {
        //add some of the default properties
        properties.put("closeBrowserOnTestComplete", "y");

    }
    //</editor-fold>

    //methods
    @Override
    public boolean execute() {
        return execute(new HashMap<String, String>(properties));
    }

    @Override
    public boolean execute(Map<String, String> parentProperties) {
        //create a new map with this.parentProperties and parentProperties
        //Map<String, String> clonedProperties = new HashMap<String, String>(parentProperties);
        for (Map.Entry<String, String> entry : this.properties.entrySet()) {
            //override the parent properties, and create a complete set of properties
            parentProperties.put(entry.getKey(), entry.getValue());
        }

        DriverManager.getWebDriver().get("");

        for (Page page : this.getPages()) {
            //page.dManager.driver = this.dManager.driver;

            try{
            if (!page.execute(new HashMap(parentProperties))) {
                DriverManager.quitDriver();
                return false;
            }
            }catch (Exception e){
                System.out.println("Test Fails");
                break;
            }
        }
        DriverManager.quitDriver();

        return true;
    }

    //getters and setters
    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="addPage methods">
    /**
     *
     * @param page
     * @return itself
     */
    public Test addPage(Page page) {
        if (pages != null) {
            pages.add(page);
        } else {
            pages = new ArrayList<Page>();
            pages.add(page);
        }
        return this;
    }

    public Test addPage(int i, Page page) {
        if (pages != null) {
            if (i > -1) {
                if (pages.size() > i) {
                    pages.add(i, page);
                } else {
                    pages.add(page);
                }
            } else {
                pages.add(0, page);
            }
        } else {
            pages = new ArrayList<Page>();
            pages.add(page);
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="remove page methods">
    /**
     *
     * @return the last page, removing it from the actual list
     */
    public Page remove() {
        if (pages.size() > 1) {
            return pages.remove(pages.size() - 1);
        } else {
            return null;
        }
    }

    /**
     *
     * @param i index of item to be removed
     * @return ith page if i is valid, else null
     */
    public Page remove(int i) {
        int size = pages.size();
        Page p = null;
        if (i < 0) {
            return p;
        }
        if (size >= 1) {
            if (size > i) {
                return pages.remove(i);
            }
        }
        return p;
    }
    //</editor-fold>

    //method for unit testing
    public static void main(String[] args) {
        Test t = new Test();

        t.addPage(new Page("Page1"))
                .addPage(new Page("Page2"))
                .addPage(new Page("Page3"));
        t.addPage(2, new Page("page4"));

        for (Page p : t.getPages()) {
            System.out.println(p);
        }
        System.out.println("------------------------------------------------------");
        System.out.println(t.remove(0));
        System.out.println(t.remove(0));
        System.out.println(t.remove(0));
        System.out.println(t.remove(0));
        System.out.println(t.remove(0));
    }

}
