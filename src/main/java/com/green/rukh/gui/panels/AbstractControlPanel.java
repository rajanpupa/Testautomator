package com.green.rukh.gui.panels;

import com.green.rukh.gui.components.LeftTree;
import selenium.automation.treeelements.elements.BaseElement;
import selenium.automation.treeelements.elements.TestPlan;
import com.green.rukh.test.manager.TreeHelper;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import selenium.automation.treeelements.events.ARootEvent;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
public class AbstractControlPanel extends javax.swing.JPanel {

    public BasePanel bPanel;
    static Logger logger = LogManager.getLogger(AbstractControlPanel.class.getName());

    //<editor-fold defaultstate="collapsed" desc="constructors">    
    
    public AbstractControlPanel(BasePanel parent) {
        this.bPanel = parent;
        initComponents();
    }
    //</editor-fold>
    
    
    public JTree getDefaultTree() {
        //jTree = TreeHelper.getTree(PlanProvider.getDefaultTestPlan());
        TestPlan testPlan = bPanel.getTestPlan();
        
        LeftTree tree = TreeHelper.getTree(testPlan);
        tree.setBasePanel(bPanel);
        
        return tree;
    }

    @Override
    public void setVisible(boolean value) {
        super.setVisible(value);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        setBackground(new java.awt.Color(255, 0, 51));
        setLayout(new java.awt.BorderLayout());

        jTree1 = getDefaultTree();
        //jTree1.setEditable(true);
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jTree1);

        add(jScrollPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Tree element selection event">
    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
        // TODO addMenu your handling code here:
        //logger.info( evt.getPath().toString());
        if (true) {
            
            logger.info("Value changed event. " + evt.getPath());
            
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
            
            if (node != null) {//prevents error when node is deleted
                if (BaseElement.class.isInstance(node.getUserObject())) {
                    BaseElement e = (BaseElement) node.getUserObject();
                    //Logger.log(Logger.Severity.INFO, "Last selection : Object type = " + e.getType());
                    bPanel.baseElementSelectionEvent(e);
                }else if(ARootEvent.class.isInstance(node.getUserObject())){
                     ARootEvent e = (ARootEvent) node.getUserObject();
                    //Logger.log(Logger.Severity.INFO, "Last selection : Object type = " + e.getType());
                    bPanel.pageeventSelectionEvent(e);
                }
            }
        }
        
    }//GEN-LAST:event_jTree1ValueChanged
    //</editor-fold>
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

}
