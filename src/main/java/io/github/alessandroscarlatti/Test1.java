package io.github.alessandroscarlatti;

import org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import java.util.*;

public class Test1 extends JFrame {

    public Test1() throws Exception {
        super("TreeDemo");
        setSize(500, 500);
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        String theParent = "Feature Set #1";
        String thefeature = "sdfdsfds";
        String feature2 = "Second Feature";
        String feature3 = "Third Feature";
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(theParent, true);
        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(thefeature, true);
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(feature2, true);
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(feature3, false);

        n1.add(n3);
        top.add(n1);
        top.add(n2);
        JTree tree = new JTree(top);
        p.add(tree, BorderLayout.NORTH);
        getContentPane().add(p);

        TreeCellRenderer origTreeCellRenderer = tree.getCellRenderer();
        TestRenderer tr = new TestRenderer(origTreeCellRenderer);
        TestEditor1 te = new TestEditor1();


        tree.setEditable(false);
        tree.setCellRenderer(tr);
        tree.setCellEditor(te);


        tree.addMouseMotionListener(new AutoEditMouseMotionListener(tree));
    }

    public static class AutoEditMouseMotionListener implements MouseMotionListener {

        private JTree tree;
        private TreePath currentPath;

        public AutoEditMouseMotionListener(JTree tree) {
            this.tree = tree;
        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            TreePath path = tree.getClosestPathForLocation(e.getX(), e.getY());

//            int row = tree.getRowForLocation(e.getX(), e.getY());
//            tree.get

            if (currentPath != null && currentPath.equals(path)) {
                System.out.println("existing path " + path);
                return;
            } else {
                System.out.println("new path " + path);
                tree.stopEditing();
                tree.startEditingAtPath(path);
                currentPath = path;
            }
        }
    }


    public class TestEditor1 implements TreeCellEditor {

        public TestEditor1() {
        }

        public void addCellEditorListener(CellEditorListener l) {
        }

        public void cancelCellEditing() {
        }

        public Object getCellEditorValue() {
            return this;
        }

        public boolean isCellEditable(EventObject evt) {
            return true;
//            if (evt instanceof MouseEvent) {
//                MouseEvent mevt = (MouseEvent) evt;
//
//                if (mevt.getClickCount() == 1) {
//                    return true;
//                }
//            }
//
//            return false;
        }

        public void removeCellEditorListener(CellEditorListener l) {
        }

        public boolean shouldSelectCell(EventObject anEvent) {
            return true;
        }

        public boolean stopCellEditing() {
            return false;
        }

        public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
                                                    boolean leaf, int row) {
            DefaultMutableTreeNode temp = (DefaultMutableTreeNode) value;
            JPanel jPanel = new JPanel();
            jPanel.setOpaque(false);
            jPanel.add(new JLabel("                            "));
            jPanel.add(new JButton((String) temp.getUserObject()));
            return (Component) jPanel;
        }
    }

    public class TestRenderer implements TreeCellRenderer {

        private TreeCellRenderer origTreeCellRenderer;

        public TestRenderer(TreeCellRenderer origTreeCellRenderer) {
            this.origTreeCellRenderer = origTreeCellRenderer;
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                      boolean leaf, int row, boolean hasFocus) {
            DefaultMutableTreeNode temp = (DefaultMutableTreeNode) value;
//            JComponent jComponent = (JComponent) temp.getUserObject();
//            jComponent.setBackground(UIManager.getColor("Tree.textBackground"));

//            return new JButton((String) temp.getUserObject());
            JComponent jComponent = (JComponent) origTreeCellRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
//            jComponent.add(new JButton((String) temp.getUserObject()));
//            JPanel jPanel = new JPanel();
//            jPanel.add(new JButton((String) temp.getUserObject()));
//            jPanel.add(jComponent);

//            if (hasFocus)
//                return new JLabel();

            return jComponent;
        }
    }

    public static void main(String args[]) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            try {
                UIManager.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
                JFrame frame = new Test1();
                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });

                frame.pack();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}