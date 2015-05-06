package com.green.rukh.test.manager;

import selenium.automation.treeelements.elements.TestPlan;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
public class TreeDataModel extends DefaultTreeModel {

    private DefaultMutableTreeNode root;
    private String rootName = "";
    private TestPlan testPlan;

    public TreeDataModel(TreeNode tn) {
        super(tn);

        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) root;
        rootName = (String) parentNode.getUserObject();
        
        testPlan = PlanProvider.getDefaultTestPlan();
    }

    public Object getChild(Object parent, int index) {
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) parent;
        String parentName = (String) parentNode.getUserObject();

        if (parentName.equals(rootName)) {
            return super.getChild(parent, index);
        } else {
            return new DefaultMutableTreeNode(testPlan.getName());
        }
    }
}
