
package selenium.automation.treeelements.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rajan Prasad Upadhyay
 */
public class TestPlan extends BaseElement{
    //properties
    List<Test> tests = new ArrayList<Test>();

    public TestPlan(){
        //this.name = "TestPlan";
        setName("TestPlan");
        this.type = "TestPlan";
        setDefaultProperties(properties);
    }
    public TestPlan(String name){
        //this.name = name;
        setName(name);
        this.type = "TestPlan";
        setDefaultProperties(properties);
    }
    
    //<editor-fold defaultstate="collapsed" desc="utility methods ">
    private static void setDefaultProperties(Map<String, String> properties){
        //add some of the default properties
        properties.put("browser", "firefox");
        properties.put("waitTime", "1000");
        properties.put("snapshot", "y");
        properties.put("outputLocation", "testResult");
    }
    //</editor-fold>
    
    //getters and setters
    //<editor-fold defaultstate="collapsed" desc="getters and setters">

    public List<Test> getTests() {
        return tests;
    }
    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="add Test methods">
    public TestPlan addTest(Test test){
        /**
         * add the test at the end
         */
        if(tests!= null){
            tests.add(test);
        }else{
            tests = new ArrayList<Test>();
            tests.add(test);
        }
        return this;
    }
    
    public TestPlan addTest( int index, Test test){
        /**
         * add test at the index
         */
        if(tests!= null ){
            if(index > -1){
                if(tests.size() > index)
                    tests.add(index,test);
                else
                    tests.add(test);
            } else{
                tests.add(0, test);
            }
        }else{
            tests = new ArrayList<Test>();
            tests.add(test);
        }
        
        return this;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="remove Test methods">
    /**
     * 
     * @return the last test, removing it from the actual list
     */
    public Test remove(){
        if(tests.size() > 1){
            return tests.remove(tests.size()-1);
        }else{
            return null;
        }
    }
    /**
     * 
     * @param i index of item to be removed
     * @return ith test if i is valid, else null
     */
    public Test remove(int i){
        int size = tests.size();
        Test p = null;
        if(i<0){
            return p;
        }
        if(size >= 1){
            if(size > i){
                return tests.remove(i);
            }
        }
        return p;
    }
    //</editor-fold>
    
    @Override
    public boolean execute(){
        System.out.println("Executing the TestPlan: " + this.getName());
        for(Test test : this.getTests()){
            if(!test.execute(new HashMap(this.properties))){
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        TestPlan tp = new TestPlan();
        
        // the toString() will return just the name
        System.out.println(tp);
        
        tp.addTest(new Test("Test1"))
                .addTest(new Test("Test2"))
                .addTest(new Test("Test3"))
                ;
        tp.addTest(2,new Test("Test4"));
        
        for(Test p : tp.getTests()){
            System.out.println(p);
        }
        System.out.println("------------------------------------------------------");
        System.out.println(tp.remove(0));
        System.out.println(tp.remove(0));
        System.out.println(tp.remove(0));
        System.out.println(tp.remove(0));
        System.out.println(tp.remove(0));
        
        
    }
    
}
