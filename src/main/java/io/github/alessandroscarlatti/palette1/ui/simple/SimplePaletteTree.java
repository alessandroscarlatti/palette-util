package io.github.alessandroscarlatti.palette1.ui.simple;

import io.github.alessandroscarlatti.palette1.App;
import io.github.alessandroscarlatti.palette1.domain.Palette;
import io.github.alessandroscarlatti.palette1.ui.FileSelector;

import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alessandro Scarlatti
 * @since Sunday, 10/13/2019
 */
public class SimplePaletteTree extends JTree implements FileSelector {

    public SimplePaletteTree(Palette palette) {
        setDragEnabled(true);
        setModel(new SimpleDirectoryTreeModel(palette.getDir()));
        setTransferHandler(new DragDropTransferHandler());
        setFont(getFont().deriveFont(16f));
        setRowHeight(24);

//        addMouseListener(new ContextClickListener(this, new SimpleContextMenu()));
    }

    @Override
    public List<Path> getSelections() {
        TreePath[] selectionPaths = getSelectionPaths();
        if (selectionPaths == null)
            return Collections.emptyList();
        else
            return Arrays.stream(getSelectionPaths())
                .map(treePath -> ((FileNode) treePath.getLastPathComponent()).getFile())
                .collect(Collectors.toList());
    }

    private static class ContextClickListener implements MouseListener {

        private JTree tree;
        private JPopupMenu popupMenu;

        public ContextClickListener(JTree tree, JPopupMenu popupMenu) {
            this.tree = tree;
            this.popupMenu = popupMenu;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
                int row = tree.getClosestRowForLocation(e.getX(), e.getY());
                tree.addSelectionPath(tree.getPathForRow(row));
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    private static class DragDropTransferHandler extends TransferHandler {

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
                        .map(treePath -> ((FileNode) treePath.getLastPathComponent()).getFile().toFile())
                        .collect(Collectors.toList());
                }
            };
        }
    }

    private static class FileNode {

        private Path file;

        public FileNode(Path file) {
            this.file = file;
        }

        public Path getFile() {
            return file;
        }

        @Override
        public String toString() {
            return file.getFileName().toString();
        }

        public List<Path> getChildFiles() {
            try {
                return Files.list(file)
                    .filter(path -> !path.getFileName().toString().matches(App.getInstance().getPalette().getExcludeRegex()))
                    .collect(Collectors.toList());
            } catch (Exception e) {
                throw new RuntimeException("Error iterating files in " + file, e);
            }
        }
    }

    private static class SimpleDirectoryTreeModel implements TreeModel {
        private FileNode node;

        public SimpleDirectoryTreeModel(Path dir) {
            node = new FileNode(dir);
        }

        @Override
        public void addTreeModelListener(TreeModelListener l) {
        }

        @Override
        public Object getChild(Object parent, int index) {
            if (parent == null)
                return null;
            return new FileNode(((FileNode) parent).getChildFiles().get(index));
        }

        @Override
        public int getChildCount(Object parent) {
            if (parent == null)
                return 0;
            return (((FileNode) parent).getChildFiles() != null) ? ((FileNode) parent).getChildFiles().size() : 0;
        }

        @Override
        public int getIndexOfChild(Object parent, Object child) {
            List<Path> list = ((FileNode) parent).getChildFiles();
            return list.indexOf(child);
        }

        @Override
        public Object getRoot() {
            return node;
        }

        @Override
        public boolean isLeaf(Object node) {
            return Files.isRegularFile(((FileNode) node).getFile());
        }

        @Override
        public void removeTreeModelListener(TreeModelListener l) {
        }

        @Override
        public void valueForPathChanged(TreePath path, Object newValue) {
        }
    }
}
