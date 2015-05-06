
package com.green.rukh.test.manager;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import selenium.automation.treeelements.elements.Page;
import selenium.automation.treeelements.elements.Test;
import selenium.automation.treeelements.elements.TestPlan;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
public class PlanProvider {

    /**
     * 
     * @return the @{TestPlan}, with very basic structure
     */
    public static TestPlan getDefaultTestPlan(){
        TestPlan defaultPlan = new TestPlan();
        defaultPlan.setName("Test Plan");
        
        Test test = new Test();
        test.setName("Test");
        
        Page page = new Page("Page 1");
        
        test.addPage(page);
        defaultPlan.addTest(test);
        
        return defaultPlan;
    }
    
    // for unit testing
    public static void main(String[] args) {
        try{
            TestPlan dPlan = getDefaultTestPlan();
            String json = JsonWriter.objectToJson(dPlan);
            System.out.println(json);
            
            TestPlan cPlan = (TestPlan) JsonReader.jsonToJava(json);
            System.out.println("Page name : " + cPlan.getTests().remove(0).getPages().get(0).getName());
        }catch(Exception e){
            System.out.println("Exception thrown");
        }
    }
}
