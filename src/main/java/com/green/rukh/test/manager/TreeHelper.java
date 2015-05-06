
package com.green.rukh.test.manager;

import com.green.rukh.gui.components.LeftTree;
import selenium.automation.treeelements.elements.Page;
import selenium.automation.treeelements.elements.Test;
import selenium.automation.treeelements.elements.TestPlan;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import selenium.automation.treeelements.events.IConfigurable;
import selenium.automation.treeelements.events.IPageEvent;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
public class TreeHelper {

    public static LeftTree getTree(TestPlan testPlan){
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(testPlan);
        
        //convert tests to the tree
        for(Test t : testPlan.getTests()){
//            root.add(new DefaultMutableTreeNode(t.getName()));
            DefaultMutableTreeNode testNode = new DefaultMutableTreeNode(t);
            
            for(Page p : t.getPages()){
                DefaultMutableTreeNode pageNode = new DefaultMutableTreeNode(p);
                
                for(IPageEvent event : p.getEvents()){
                    String name = event.toString();
                    
                    //not needed anymore
//                    if(event instanceof IConfigurable){//override the name
//                        name = ((IConfigurable)event).getProperty(name);
//                    }
                    DefaultMutableTreeNode eventNode = new DefaultMutableTreeNode(name);
                    eventNode.setUserObject(event);
                    
                    pageNode.add(eventNode);
                }
                
                testNode.add(pageNode);
            }
            rootNode.add(testNode);
        }
        
        //return new JTree(rootNode);
        return new LeftTree(rootNode);
    }
}
