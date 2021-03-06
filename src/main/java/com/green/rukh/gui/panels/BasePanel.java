package com.green.rukh.gui.panels;

import com.green.rukh.gui.frames.BaseFrame;
import selenium.automation.treeelements.elements.BaseElement;
import selenium.automation.treeelements.elements.TestPlan;
import com.green.rukh.test.manager.PlanProvider;
import javax.swing.JSplitPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import selenium.automation.treeelements.elements.Page;
import selenium.automation.treeelements.elements.Test;
import selenium.automation.treeelements.events.ARootEvent;
import selenium.automation.treeelements.events.ClearEvent;
import selenium.automation.treeelements.events.ClickEvent;
import selenium.automation.treeelements.events.HoverEvent;
import selenium.automation.treeelements.events.IConfigurable;
import selenium.automation.treeelements.events.NavigateEvent;
import selenium.automation.treeelements.events.SendKeysEvent;
import selenium.automation.treeelements.events.SnapShotEvent;
import selenium.automation.treeelements.events.WaitEvent;

/**
 * @author Rajan Prasad Upadhyay
 */

public class BasePanel extends javax.swing.JPanel {

    static Logger logger = LogManager.getLogger(BasePanel.class.getName());

    // <editor-fold defaultstate="collapsed" desc="properties">
    //control variables
    Integer spDividerLocation = 200;
    //guis
    javax.swing.JSplitPane jSplitPane = new JSplitPane();

    //frames
    com.green.rukh.gui.frames.BaseFrame parentFrame;

    // panel iterators
    com.green.rukh.gui.panels.AbstractControlPanel icontrolPanel;
    com.green.rukh.gui.panels.AbstractPropertyPanel ipropertyPanel;

    //all the control panels
    com.green.rukh.gui.panels.AbstractControlPanel acontrolPanel;
    com.green.rukh.gui.panels.AbstractControlPanel controlPanel;//= new ControlPanel(this);

    //all the property panels
    AbstractPropertyPanel   apropertyPanel;
    NavigationPropertyPanel pagePropertyPanel;
    SnapshotPropertyPanel   snapshotPropertyPanel;
    NamePropertyPanel       namePropertyPanel;
    SendKeysPropertyPanel   sendkeysPropertyPanel;
    ClickPropertyPanel      clickPropertyPanel;
    ClickPropertyPanel      hoverPropertyPanel;
    ClickPropertyPanel      clearPropertyPanel;
    ClickPropertyPanel      waitPropertyPanel;

    //TestPlan
    TestPlan testPlan = PlanProvider.getDefaultTestPlan();

    // </editor-fold>
    /**
     * Creates new form BasePanel
     */
    public BasePanel() {
        initComponents();
        programaticInitialize();
    }

    // <editor-fold defaultstate="collapsed" desc="ProgramaticInitialize(), GUI components configurations/designing">
    public void programaticInitialize() {
        splitPaneAddEvent();
        this.add(jSplitPane);
        initializeObjects();
        configureIterators();
        //the layout must be changed to box layout, to allow components cover max area

        jSplitPane.setRightComponent(ipropertyPanel);
        jSplitPane.setLeftComponent(icontrolPanel);
        jSplitPane.setDividerLocation(spDividerLocation);
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="initializeObjects, initialization of controlpanels and property panels">
    /**
     * instanciate the Gui objects like AbstractControlPanel,
     * AbstractPropertyPanel, ControlPanel, NavigationPropertyPanel
     */
    public void initializeObjects() {
        acontrolPanel = new AbstractControlPanel(this);
        apropertyPanel = new AbstractPropertyPanel(this);

        controlPanel = new ControlPanel(this);
        pagePropertyPanel = new NavigationPropertyPanel(this);
        snapshotPropertyPanel = new SnapshotPropertyPanel(this);
        namePropertyPanel = new NamePropertyPanel(this);
        sendkeysPropertyPanel = new SendKeysPropertyPanel(this);
        clickPropertyPanel    = new ClickPropertyPanel(this);
        hoverPropertyPanel    = new ClickPropertyPanel(this);
        clearPropertyPanel    = new ClickPropertyPanel(this);
        waitPropertyPanel     = new ClickPropertyPanel(this);
    }

    //</editor-fold>
    private void configureIterators() {
        //icontrolPanel = acontrolPanel;
        icontrolPanel = controlPanel;
        ipropertyPanel = apropertyPanel;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters-Setters">   
    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }

    public void reDraw() {
        this.invalidate();
        this.revalidate();
    }

    public BaseFrame getParentFrame() {
        return parentFrame;
    }

    public void setParentFrame(BaseFrame parentFrame) {
        this.parentFrame = parentFrame;
    }
    // </editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(160, 204, 180));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    // <editor-fold defaultstate="collapsed" desc="Tree selection event(BaseElement), clicked, called by the controlPanel">
    // Tree node selection event.
    public void baseElementSelectionEvent(BaseElement e) {
        if (e instanceof Page) {
            logger.trace("page selected");
            changePropertyPanel(namePropertyPanel, e);
        } else if (e instanceof Test) {
            logger.trace("test selected");
            changePropertyPanel(namePropertyPanel, e);
        }
    }

    private void changePropertyPanel(AbstractPropertyPanel propertyPanel, IConfigurable e) {
        jSplitPane.invalidate();
        jSplitPane.remove(ipropertyPanel);

        ipropertyPanel = propertyPanel;//this is changed

        jSplitPane.setRightComponent(ipropertyPanel);
        ipropertyPanel.setUpTestElement(e);
        jSplitPane.setDividerLocation(spDividerLocation);
        jSplitPane.revalidate();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="split pane property change event">
    private void splitPaneAddEvent() {
        jSplitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSplitPane1PropertyChanged(evt);
            }
        });
    }

    private void jSplitPane1PropertyChanged(java.beans.PropertyChangeEvent evt) {
        //code to handle the jsplit pane resized event
        //if divider changed, update it
        if (spDividerLocation != jSplitPane.getDividerLocation()) {
            spDividerLocation = jSplitPane.getDividerLocation();
        }

    }

    // </editor-fold>
    
    public void executeSeleniumTest() {
        logger.info("Test execution under process.");
        this.getTestPlan().execute();
    }

    // <editor-fold defaultstate="collapsed" desc="Tree selection event(ARootEvent), clicked, called by the controlPanel">
    void pageeventSelectionEvent(ARootEvent e) {
        if (e instanceof NavigateEvent) {
            logger.trace("navigationEvent selected.");
            changePropertyPanel(pagePropertyPanel, e);
        } else if (e instanceof SnapShotEvent) {//e.toString().equalsIgnoreCase("snapshotEvent")
            logger.trace("snapshotEvent selected");
            changePropertyPanel(snapshotPropertyPanel, e);
        } else if (e instanceof SendKeysEvent){
            logger.trace("sendKeysEvent selected");
            changePropertyPanel(sendkeysPropertyPanel, e);
        }else if (e instanceof ClickEvent){
            logger.trace("ClickEvent selected");
            changePropertyPanel(clickPropertyPanel, e);
        }else if (e instanceof HoverEvent){
            logger.trace("HoverEvent selected");
            changePropertyPanel(hoverPropertyPanel, e);
        }else if (e instanceof ClearEvent){
            logger.trace("ClearEvent selected");
            changePropertyPanel(hoverPropertyPanel, e);
        }else if (e instanceof WaitEvent){
            logger.trace("WaitEvent selected");
            changePropertyPanel(waitPropertyPanel, e);
        }
    }
    //</editor-fold>
}
