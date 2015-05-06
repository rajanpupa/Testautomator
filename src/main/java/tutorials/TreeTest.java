package tutorials;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
public class TreeTest {

    String name = "name";

    public static void main(String[] args) {
        javax.swing.JFrame topFrame = new JFrame();
        javax.swing.JPanel topPanel = new JPanel();
        javax.swing.JScrollPane leftScrollPane = new JScrollPane();
        javax.swing.JTree leftTree = new JTree();
        
        //for the cell renderer
        leftTree.setCellRenderer(new CustomCellRenderer());

        topFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //layouts
        topFrame.setLayout(new BorderLayout());
        topPanel.setLayout(new BorderLayout());

        // addition of components
//        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        topPanel.add(leftScrollPane);

        leftScrollPane.getViewport().add(leftTree);

        addExperimentEvents(leftTree);

        topFrame.setSize(600, 500);
        topFrame.setContentPane(topPanel);
        topFrame.setVisible(true);
    }

    public static void addExperimentEvents(JTree tree) {
        MouseAdapter ma = new MouseAdapter() {
            private void myPopupEvent(MouseEvent e) {
                final int x = e.getX();
                final int y = e.getY();
                final JTree tree = (JTree) e.getSource();
                TreePath path = tree.getPathForLocation(x, y);
                if (path == null) {
                    return;
                }

                tree.setSelectionPath(path);

                Object obj = (Object) path.getLastPathComponent();

                String label = "Add:";// + obj.toString();
                JPopupMenu popup = new JPopupMenu("Menu");

                JMenu sectionsMenu = new JMenu("Sections");
                JMenuItem menuItem1 = new JMenuItem("Item1");
                sectionsMenu.add(menuItem1);
                JMenuItem menuItem2 = new JMenuItem("Item2");
                sectionsMenu.add(menuItem2);

                JMenuItem item = new JMenuItem(label);
                item.addActionListener(new ActionListener() {

                    //@Override
                    public void actionPerformed(ActionEvent ae) {
                        System.out.println("Editing the tree");
                        DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) tree
                                .getLastSelectedPathComponent();
                        TreeNode root = (TreeNode) tree.getModel().getRoot();
                        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();//new DefaultTreeModel(root);

                        if (selNode != null) {
                            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("New Node" + (selNode.getChildCount() + 1));
                            model.insertNodeInto(newNode, selNode, selNode.getChildCount());//
                            //model.insertNodeInto(newNode, (MutableTreeNode) root, root.getChildCount());
                            //tree.expandPath(new TreePath(model.getPathToRoot(newNode.getParent())));

                            TreeNode[] nodes = model.getPathToRoot(newNode);
                            TreePath path = new TreePath(nodes);

                            System.out.println(selNode.getChildCount() + ", " + path);
                            tree.scrollPathToVisible(path);
                            tree.setSelectionPath(path);
                            tree.startEditingAtPath(path);
                            //model.reload((TreeNode) model.getRoot());
                        } else {
                            System.out.println("selNode is null");
                        }
                    }
                });
                //addHoverAction(item);//
                //item.add(new JMenuItem("page"));

                popup.add(item);
                JMenuItem delete = new JMenuItem("Delete");
                delete.addActionListener(new ActionListener() {

                    //@Override
                    public void actionPerformed(ActionEvent ae) {
                        
                        TreePath path = tree.getPathForLocation(x, y);
                        final DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();

                        int childNodeIndex = parentNode.getIndex(node);

                        System.out.println("Index of node deleted, wrt its parent is : " + childNodeIndex);

                        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

                        if (node.getParent() != null) {
                            model.removeNodeFromParent(node);
                        }
                    }
                });
                popup.add(delete);
                popup.add(new JMenuItem("Move: "));
                popup.add(sectionsMenu);
                popup.show(tree, x, y);
            }

            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    myPopupEvent(e);
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    myPopupEvent(e);
                }
            }
        };
        tree.addMouseListener(ma);
    }

    // <editor-fold defaultstate="collapsed" desc="hoverAction">
    public static void addHoverAction(final JComponent com) {

        MouseAdapter adapter = new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                System.out.println("mouse Entered: ");
                if (true) {
                    JPopupMenu pop = new JPopupMenu();
                    pop.add(new JMenuItem("Node"));

                    pop.show(com.getParent(), com.getWidth(), com.getHeight());
                }
            }
        };
        com.addMouseListener(adapter);
    }
    // </editor-fold>

    //static methods
    
        
    
}

class CustomCellRenderer extends DefaultTreeCellRenderer{
    Icon cup;
    Icon triangle;
    Icon page;
    
    public CustomCellRenderer(){
        cup = createImageIcon("/image/cup.GIF");
        triangle = createImageIcon("/image/middle.GIF");
        page = createImageIcon("/image/cup.GIF");
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
            
            if(value.toString().equalsIgnoreCase("colors")){
                setIcon(cup);
            }else if(value.toString().equalsIgnoreCase("sports")){
                setIcon(triangle);
            }else if (value.toString().equalsIgnoreCase("food")){
                setIcon(page);
            }

            return this;
        }
    protected static  ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TreeTest.class.getResource(path);
//        System.out.println(CustomCellRenderer.class.g);
        System.out.println("The image uri is : " + imgURL + " " + path);
        if (imgURL != null) {
            System.out.println("returning new image icon.");
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}

