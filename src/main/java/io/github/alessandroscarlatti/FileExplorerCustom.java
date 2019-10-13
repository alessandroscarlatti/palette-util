package io.github.alessandroscarlatti;

//import org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.EventObject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author NagasharathK
 */
public class FileExplorerCustom extends JFrame {

    private JTree fileManagerTree = null;
    private Path dir;

    public FileExplorerCustom(Path dir) {
        this.dir = dir;
        initComponents();
    }

    /**
     * @param args
     * @throws InvocationTargetException
     * @throws InterruptedException
     * @throws UnsupportedLookAndFeelException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws InvocationTargetException, InterruptedException,
        ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {


//        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        WebLookAndFeel.install();
//        UIManager.setLookAndFeel(new QuaquaLookAndFeel());

//        UIManager.setLookAndFeel(new PgsLookAndFeel());


//        UIManager.put("Tree.leafIcon", new ImageIcon(new ImageIcon("item.png").getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH)));
//        UIManager.put("Tree.openIcon", new ImageIcon(new ImageIcon("Folder.png").getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH)));
//        UIManager.put("Tree.closedIcon", new ImageIcon(new ImageIcon("Folder.png").getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH)));
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                try {
//                    UIManager.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
                    FileExplorerCustom explorerUI = new FileExplorerCustom(Paths.get("C:\\Users\\pc\\IdeaProjects\\palette-util"));
                    explorerUI.setVisible(true);
                } catch (Exception e) {
                    throw new RuntimeException("Error running app.", e);
                }
            }
        });
    }

    /**
     * Initializes components
     */
    private void initComponents() {

        try {
            List<Image> images = Arrays.asList(
                ImageIO.read(this.getClass().getResource("/icon32.png")),
                ImageIO.read(this.getClass().getResource("/icon48.png"))
            );

            setIconImages(images);


            JTabbedPane tabPane = new JTabbedPane();
            tabPane.setFont(tabPane.getFont().deriveFont(16f));
            tabPane.addTab("Group1", new JScrollPane(createFileManagerTree()));
            tabPane.addTab("Group2", new JScrollPane(createFileManagerTree()));
            tabPane.addTab("Group2", new JScrollPane(createFileManagerTree()));
            tabPane.addTab("Group2", new JScrollPane(createFileManagerTree()));
            tabPane.addTab("Group2", new JScrollPane(createFileManagerTree()));
            tabPane.addTab("Group2", new JButton("stuff"));



            getContentPane().add(tabPane);
            setSize(500, 500);
            setResizable(true);
            setTitle("Palette [" + dir.toAbsolutePath().normalize() + "]");
            setAlwaysOnTop(true);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing app.", e);
        }
    }

    /**
     * @return JPanel object which contains other comp...
     */
    private JPanel createFileManagerTree() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());


        panel.add(new CustomJTree(dir));
        return panel;
    }

    static class CustomJTree extends JTree {
        public CustomJTree(Path dir) {
            setDragEnabled(true);
            setModel(new FilesContentProvider(dir));
            setTransferHandler(new CustomTransferHandler());
            setFont(getFont().deriveFont(16f));
            setRowHeight(24);

//            setCellEditor(new MyTreeCellEditor(this, (DefaultTreeCellRenderer) getCellRenderer()));
        }
    }

    static class ButtonCellRenderer implements TreeCellRenderer {
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            return new JButton(((FileNode) value).toString());
        }
    }

    static class CustomTransferHandler extends TransferHandler {

        @Override
        public int getSourceActions(JComponent c) {
            return TransferHandler.COPY;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            JTree jTree = (JTree) c;
            return new Transferable() {
                @Override
                public DataFlavor[] getTransferDataFlavors() {
                    return new DataFlavor[]{DataFlavor.javaFileListFlavor};
                }

                @Override
                public boolean isDataFlavorSupported(DataFlavor flavor) {
                    return true;
                }

                @Override
                public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                    return Arrays.stream(jTree.getSelectionModel().getSelectionPaths())
                        .map(treePath -> ((FileNode) treePath.getLastPathComponent()).getFile())
                        .collect(Collectors.toList());
                }
            };
        }
    }

    static class FileNode {

        private File file;

        public FileNode(File file) {
            this.file = file;
        }

        public File getFile() {
            return file;
        }

        @Override
        public String toString() {
            return file.getName();
        }


    }

    static class FilesContentProvider implements TreeModel {

        private FileNode node;

        public FilesContentProvider(Path dir) {
            node = new FileNode(dir.toFile());

        }

        @Override
        public void addTreeModelListener(TreeModelListener l) {

        }

        @Override
        public Object getChild(Object parent, int index) {
            if (parent == null)
                return null;
            return new FileNode(((FileNode) parent).getFile().listFiles()[index]);
        }

        @Override
        public int getChildCount(Object parent) {
            if (parent == null)
                return 0;
            return (((FileNode) parent).getFile().listFiles() != null) ? ((FileNode) parent).getFile().listFiles().length : 0;
        }

        @Override
        public int getIndexOfChild(Object parent, Object child) {
            List<File> list = Arrays.asList(((FileNode) parent).getFile().listFiles());
            return list.indexOf(child);
        }

        @Override
        public Object getRoot() {
            return node;
        }

        @Override
        public boolean isLeaf(Object node) {
            return ((FileNode) node).getFile().isFile();
        }

        @Override
        public void removeTreeModelListener(TreeModelListener l) {

        }

        @Override
        public void valueForPathChanged(TreePath path, Object newValue) {
            System.out.println("FilesContentProvider.valueForPathChanged() path = [" + path + "], newValue = [" + newValue + "]");
        }

    }
}