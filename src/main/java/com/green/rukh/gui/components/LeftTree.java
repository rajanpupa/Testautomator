package com.green.rukh.gui.components;

import com.green.rukh.gui.panels.BasePanel;
import selenium.automation.treeelements.elements.BaseElement;
import selenium.automation.treeelements.elements.Page;
import selenium.automation.treeelements.elements.Test;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import selenium.automation.treeelements.elements.TestPlan;
import selenium.automation.treeelements.events.ARootEvent;
import selenium.automation.treeelements.events.ClearEvent;
import selenium.automation.treeelements.events.ClickEvent;
import selenium.automation.treeelements.events.HoverEvent;
import selenium.automation.treeelements.events.IPageEvent;
import selenium.automation.treeelements.events.NavigateEvent;
import selenium.automation.treeelements.events.SendKeysEvent;
import selenium.automation.treeelements.events.SnapShotEvent;
import selenium.automation.treeelements.events.WaitEvent;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
/**
 * LeftTree extends JTree, It had custom CellRenderer It expects Object of type
 * BaseElement in its nodes, and can handle right click events on the Test and
 * Page type objects which extends BaseElement
 *
 * This tree is created by {com.green.rukh.test.manager.TreeHelper} class on the
 * basis of a TestPlan
 */
public class LeftTree extends JTree {

    static Logger logger = LogManager.getLogger(LeftTree.class.getName());

    //Properties
    BasePanel basePanel;

    public LeftTree(DefaultMutableTreeNode rootNode) {
        super(rootNode);
        this.setCellRenderer(new CustomCellRenderer());

        initComponents();
    }

    private void initComponents() {
        // add the mouse listener for the popup events
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                logger.trace("LeftTree.java: addMouseListener");
                jTree1MouseEventPerformed(evt);
            }
            
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logger.trace("LeftTree.java: addMouseListener");
                jTree1MouseEventPerformed(evt);
            }

        });
    }

    public BasePanel getBasePanel() {
        return basePanel;
    }

    public void setBasePanel(BasePanel basePanel) {
        this.basePanel = basePanel;
    }

    // <editor-fold defaultstate="collapsed" desc="MouseReleased PopupEvent Action">
    private void jTree1MouseEventPerformed(java.awt.event.MouseEvent evt) {
        // TODO addMenu your handling code here:
        if (evt.isPopupTrigger()) {
            popUpTriggerEvent(evt);
        } else {
            logger.trace("Not a popup trigger event");
            notPopUpTriggerEvent(evt);
        }

    }

    private void popUpTriggerEvent(java.awt.event.MouseEvent evt) {
        logger.trace("Pop up on release");
        //display pop up menu here
        int x = evt.getX();
        int y = evt.getY();
        JTree tree = (JTree) evt.getSource();
        TreePath path = tree.getPathForLocation(x, y);

        if (path == null) {
            return;
        }

        tree.setSelectionPath(path);

        DefaultMutableTreeNode obj = (DefaultMutableTreeNode) path.getLastPathComponent();

        String objectType = "";
        Object object = obj.getUserObject();

        if (object instanceof BaseElement) {
            objectType = ((BaseElement) object).getType();

            logger.trace(obj + " of type " + objectType);
            //PopUpManager.getPopupMenu(TOOL_TIP_TEXT_KEY)
            if ("TestPlan".equalsIgnoreCase(objectType)) {
                logger.trace("The object right clicked is type TestPlan.");
                testPlanRightClickAction(tree, x, y);
            } else if ("Test".equals(objectType)) {
                logger.trace("The object right clicked is type Test.");
                //add the popup menu here.
                testRightClickAction(tree, x, y);
            } else if ("Page".equals(objectType)) {
                logger.trace("The object right clicked is of type Page.");
                pageRightClickAction(tree, x, y);
            }
        } else if (object instanceof ARootEvent) {
            // this section is handeled in the Abstract Control Panel
            //logger.trace("The object selected is of type ArootEvent.");
        }
    }

    private void notPopUpTriggerEvent(java.awt.event.MouseEvent evt) {
        //System.out.println("Not a popup event");
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="TesePlan element Right Clicked methods, add new page event">
    public void testPlanRightClickAction(final JTree tree, int x, int y) {
        TreePath path = tree.getPathForLocation(x, y);
        final DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        final BaseElement element = (BaseElement) node.getUserObject();

        JPopupMenu popup = new JPopupMenu();

        //level 1 menus
        JMenu addMenu = new JMenu("Add");
        //JMenuItem delete = new JMenuItem("Delete");

        //level 2 items
        JMenuItem addPageMenuItem = new JMenuItem("Test");

        addPageMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addNewTestEvent(tree, node);
            }
        });

        addMenu.add(addPageMenuItem);

        popup.add(addMenu);
        //popup.add(delete);

        popup.show(tree, x, y);
    }

    public void addNewTestEvent(JTree tree, DefaultMutableTreeNode node) {
        TestPlan testPlan = (TestPlan) node.getUserObject();
        Test newTest = new Test("Test " + (node.getChildCount() + 1));
        testPlan.addTest(newTest);
        logger.trace(newTest + " added inside " + testPlan + " in the data structure.");

        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newTest);
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        model.insertNodeInto(newNode, node, node.getChildCount());

        newNode.setUserObject(newTest);
        TreeNode[] nodes = model.getPathToRoot(newNode);
        TreePath path = new TreePath(nodes);
        tree.scrollPathToVisible(path);
        tree.setSelectionPath(path);

        logger.trace("addNewPageEvent Path :" + path);

        tree.startEditingAtPath(path);
    }
    // test right clicked action
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tese element Right Clicked methods, add new page event">
    public void testRightClickAction(final JTree tree, int x, int y) {
        TreePath path = tree.getPathForLocation(x, y);
        final DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        final BaseElement element = (BaseElement) node.getUserObject();

        JPopupMenu popup = new JPopupMenu();

        //level 1 menus
        JMenu addMenu = new JMenu("Add");
        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                deleteTestEvent(tree, node);
            }

        });

        //level 2 items
        JMenuItem addPageMenuItem = new JMenuItem("Page");

        addPageMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addNewPageEvent(tree, node);
            }
        });

        addMenu.add(addPageMenuItem);

        popup.add(addMenu);
        popup.add(delete);

        popup.show(tree, x, y);
    }

    public void addNewPageEvent(JTree tree, DefaultMutableTreeNode node) {
        Test elem = (Test) node.getUserObject();
        Page p = new Page("Page " + (node.getChildCount() + 1));
        elem.addPage(p);
        logger.trace(p + " added inside " + elem + " in the data structure.");

        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(p);
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        model.insertNodeInto(newNode, node, node.getChildCount());

        newNode.setUserObject(p);
        TreeNode[] nodes = model.getPathToRoot(newNode);
        TreePath path = new TreePath(nodes);
        tree.scrollPathToVisible(path);
        tree.setSelectionPath(path);

        logger.trace("addNewPageEvent Path :" + path);

        tree.startEditingAtPath(path);
    }

    public void deleteTestEvent(JTree tree, DefaultMutableTreeNode node) {
        Test elem = (Test) node.getUserObject();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        TestPlan parentElem = (TestPlan) parentNode.getUserObject();

        int childNodeIndex = parentNode.getIndex(node);
        parentElem.remove(childNodeIndex);

        logger.trace("Inside the delete event. Index of selected " + childNodeIndex);

        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        if (node.getParent() != null) {
            model.removeNodeFromParent(node);
        }
    }

    // test right clicked action
    // </editor-fold>     
    
    // <editor-fold defaultstate="collapsed" desc="page element right clicked action, delete, add event event">
    public void pageRightClickAction(final JTree tree, int x, int y) {
        TreePath path = tree.getPathForLocation(x, y);
        final DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        final BaseElement element = (BaseElement) node.getUserObject();

        JPopupMenu popup = new JPopupMenu();

        //level 1 menus
        //menu item
        JMenu addEvent = new JMenu("Add Event");

        //submenu item
        JMenuItem clearItem = new JMenuItem("Clear Event");
        JMenuItem clickItem = new JMenuItem("Click Event");
        JMenuItem hoverItem = new JMenuItem("Hover Event");
        JMenuItem navigationItem = new JMenuItem("Navigation Event");
        JMenuItem sendKeysItem = new JMenuItem("Send Keys Event");
        JMenuItem snapshotItem = new JMenuItem("Snapshot Event");
        JMenuItem WaitItem = new JMenuItem("Wait Event");

        //add the items to the menu
        addEvent.add(clearItem);
        addEvent.add(clickItem);
        addEvent.add(hoverItem);
        addEvent.add(navigationItem);
        addEvent.add(sendKeysItem);
        addEvent.add(snapshotItem);
        addEvent.add(WaitItem);

        //adding eventHandlers
        clearItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addClearEvent(tree, node);
            }
        });
        hoverItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addHoverEvent(tree, node);
            }
        });
        clickItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addClickEvent(tree, node);
            }
        });
        navigationItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addNavigationEvent(tree, node);
            }
        });
        sendKeysItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addSendKeysEvent(tree, node);
            }
        });
        snapshotItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addSnapshotEvent(tree, node);
            }
        });
        WaitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addWaitEvent(tree, node);
            }
        });

        popup.add(addEvent);
        addEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                deletePageEvent(tree, node);
            }
        });

        //delete
        JMenuItem delete = new JMenuItem("Delete");
        popup.add(delete);
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                deletePageEvent(tree, node);
            }
        });

        popup.show(tree, x, y);
    }

    public void deletePageEvent(JTree tree, DefaultMutableTreeNode node) {
        Page elem = (Page) node.getUserObject();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        Test parentElem = (Test) parentNode.getUserObject();

        int childNodeIndex = parentNode.getIndex(node);
        parentElem.remove(childNodeIndex);

        logger.trace("Inside the delete event. Index of selected " + childNodeIndex);

        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        if (node.getParent() != null) {
            model.removeNodeFromParent(node);
        }
    }

    private void addClickEventAdditionHandeling(JTree tree, DefaultMutableTreeNode node, IPageEvent p){
        //update the ui nodes
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(p);
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        model.insertNodeInto(newNode, node, node.getChildCount());

        newNode.setUserObject(p);
        TreeNode[] nodes = model.getPathToRoot(newNode);
        TreePath path = new TreePath(nodes);
        tree.scrollPathToVisible(path);
        tree.setSelectionPath(path);
        logger.trace("added Event. Path :" + path);

        tree.startEditingAtPath(path);
    }
    public void addClearEvent(JTree tree, DefaultMutableTreeNode node) {
        //add the event in the page
        Page elem = (Page) node.getUserObject();
        IPageEvent p = new ClearEvent();
        elem.addEvent(p);

        logger.trace(p + " added inside " + elem + " in the data structure.");

        addClickEventAdditionHandeling(tree, node, p);
    }
    public void addHoverEvent(JTree tree, DefaultMutableTreeNode node) {
        //add the event in the page
        Page elem = (Page) node.getUserObject();
        IPageEvent p = new HoverEvent();
        elem.addEvent(p);

        logger.trace(p + " added inside " + elem + " in the data structure.");

        addClickEventAdditionHandeling(tree, node, p);
    }
    
    public void addClickEvent(JTree tree, DefaultMutableTreeNode node) {
        //add the event in the page
        Page elem = (Page) node.getUserObject();
        IPageEvent p = new ClickEvent();
        elem.addEvent(p);

        logger.trace(p + " added inside " + elem + " in the data structure.");

        addClickEventAdditionHandeling(tree, node, p);
    }

    public void addNavigationEvent(JTree tree, DefaultMutableTreeNode node) {
        //add the event in the page
        Page elem = (Page) node.getUserObject();
        IPageEvent p = new NavigateEvent(elem.getProperty("url"));
        elem.addEvent(p);

        addClickEventAdditionHandeling(tree, node, p);
    }

    public void addSendKeysEvent(JTree tree, DefaultMutableTreeNode node) {
        //add the event in the page
        Page elem = (Page) node.getUserObject();
        IPageEvent p = new SendKeysEvent();
        elem.addEvent(p);

        addClickEventAdditionHandeling(tree, node, p);
    }

    public void addSnapshotEvent(JTree tree, DefaultMutableTreeNode node) {
        System.out.println("Add code in addSnapshotEvent method to add a navigation item");

        //add the event in the page
        Page elem = (Page) node.getUserObject();
        IPageEvent p = new SnapShotEvent("result\\" + elem + ".png");
        elem.addEvent(p);

        addClickEventAdditionHandeling(tree, node, p);
    }
    public void addWaitEvent(JTree tree, DefaultMutableTreeNode node) {
        System.out.println("Add code in addWaitEvent method to add a navigation item");

        //add the event in the page
        Page elem = (Page) node.getUserObject();
        IPageEvent p = new WaitEvent();
        elem.addEvent(p);

        addClickEventAdditionHandeling(tree, node, p);
    }
    // </editor-fold>

    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = LeftTree.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            logger.error("Couldn't find file: " + path);
            String str = "  Wait ";
            return null;
        }
    }
}

// <editor-fold defaultstate="collapsed" desc="CustomCellRenderer class">
class CustomCellRenderer extends DefaultTreeCellRenderer {
    Icon cup;
    Icon triangle;
    Icon page;
    Icon chasma;
    Icon house;
    Icon upArrow;
    Icon downArrow;
    Icon leftArrow;
    Icon rightArrow;
    Icon smallWeb;
    Icon circularTick;
    Icon circularPlus;
    Icon circularCross;
    Icon tree4;
    Icon leaf3;
    Icon camera3;
    Icon text;
    Icon click;
    Icon hover;
    Icon clear;
    Icon wait;

    public CustomCellRenderer() {
        cup = LeftTree.createImageIcon("/image/cup.GIF");
        triangle = LeftTree.createImageIcon("/image/middle.GIF");
        page = LeftTree.createImageIcon("/icons/notebook.GIF");
        house = LeftTree.createImageIcon("/icons/house.GIF");
        chasma = LeftTree.createImageIcon("/icons/chasma.GIF");
        smallWeb = LeftTree.createImageIcon("/icons/small-web.GIF");
        tree4 = LeftTree.createImageIcon("/icons/tree4.GIF");
        leaf3 = LeftTree.createImageIcon("/icons/leaf3.GIF");
        rightArrow = LeftTree.createImageIcon("/icons/right-arrow.GIF");
        camera3 = LeftTree.createImageIcon("/icons/camera3.GIF");
        text = LeftTree.createImageIcon("/icons/text.GIF");
        click = LeftTree.createImageIcon("/icons/click.GIF");
        hover = LeftTree.createImageIcon("/icons/hover.GIF");
        clear = LeftTree.createImageIcon("/icons/clear.GIF");
        wait = LeftTree.createImageIcon("/icons/wait.GIF");
    }

    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {

        super.getTreeCellRendererComponent(
                tree, value, sel,
                expanded, leaf, row,
                hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Object obj = node.getUserObject();

        if (BaseElement.class.isInstance(obj)) {
            BaseElement elem = (BaseElement) node.getUserObject();

            if (elem.getType().equalsIgnoreCase("TestPlan")) {
                setIcon(tree4);
            } else if (elem.getType().equalsIgnoreCase("test")) {
                setIcon(chasma);
            } else if (elem.getType().equalsIgnoreCase("page")) {
                setIcon(leaf3);
            }
        } else {
            if (obj instanceof NavigateEvent) {
                setIcon(triangle);
            } else if (obj instanceof SnapShotEvent) {
                setIcon(camera3);
            } else if (obj instanceof SendKeysEvent) {
                setIcon(text);
            }else if (obj instanceof ClickEvent){
                setIcon(click);
            }else if (obj instanceof HoverEvent){
                setIcon(hover);
            }else if (obj instanceof ClearEvent){
                setIcon(clear);
            }else if (obj instanceof WaitEvent){
                setIcon(wait);
            }
        }
        return this;
    }

}

// </editor-fold>
