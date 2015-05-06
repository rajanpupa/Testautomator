
package com.green.rukh.gui.panels;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import selenium.automation.treeelements.events.IConfigurable;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
public class AbstractPropertyPanel extends javax.swing.JPanel {
    //property elements
    protected String key = "Key";
    
    
    //gui
    javax.swing.JScrollPane jScrollPane = new JScrollPane();
    
    
    public int width = 50;
    public BasePanel bPanel;

//    public BasePanel getbPanel() {
//        return bPanel;
//    }

    public void setbPanel(BasePanel bPanel) {
        this.bPanel = bPanel;
    }
    
    /**
     * Creates new form AbstractPropertyPanel
     */
    public AbstractPropertyPanel() {
        initComponents();
        this.setLayout(new BorderLayout());
        this.add(jScrollPane);
    }
    public  AbstractPropertyPanel(BasePanel parent) {
        this.bPanel = parent;
        initComponents();
        this.setLayout(new BorderLayout());
        this.add(jScrollPane);
    }
    
    public void setUpTestElement(IConfigurable pageObject){
        
    }

    @Override
    public void setVisible(boolean value){
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

        setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
