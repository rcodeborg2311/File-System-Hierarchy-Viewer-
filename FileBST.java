public class FileBST {
    private FileNode root;

    // Insert a new node
    public void insert(FileNode node) {
        root = insertRec(root, node);
    }

    private FileNode insertRec(FileNode current, FileNode node) {
        if (current == null) {
            return node;
        }

        if (node.getName().compareTo(current.getName()) < 0) {
            current.setLeft(insertRec(current.getLeft(), node));
        } else if (node.getName().compareTo(current.getName()) > 0) {
            current.setRight(insertRec(current.getRight(), node));
        }

        return current;
    }
    public void insertByName(FileNode node) {
        root = insertByNameRec(root, node);
    }

    private FileNode insertByNameRec(FileNode current, FileNode node) {
        if (current == null) {
            return node;
        }

        if (node.getName().compareTo(current.getName()) < 0) {
            current.setLeft(insertByNameRec(current.getLeft(), node));
        } else if (node.getName().compareTo(current.getName()) > 0) {
            current.setRight(insertByNameRec(current.getRight(), node));
        }

        return current;
    }

    // Insert nodes based on file size
    public void insertBySize(FileNode node) {
        root = insertBySizeRec(root, node);
    }

    private FileNode insertBySizeRec(FileNode current, FileNode node) {
        if (current == null) {
            return node;
        }

        if (Long.compare(node.getSize(), current.getSize()) < 0) {
            current.setLeft(insertBySizeRec(current.getLeft(), node));
        } else if (Long.compare(node.getSize(), current.getSize()) > 0) {
            current.setRight(insertBySizeRec(current.getRight(), node));
        }

        return current;
    }


    // Delete a node by name
    public void delete(String name) {
        root = deleteRec(root, name);
    }

    private FileNode deleteRec(FileNode current, String name) {
        if (current == null) {
            return null;
        }

        if (name.equals(current.getName())) {
            // Node with no children
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }
            // Node with one child
            if (current.getRight() == null) {
                return current.getLeft();
            }
            if (current.getLeft() == null) {
                return current.getRight();
            }
            // Node with two children
            FileNode smallest = findSmallest(current.getRight());
            current.setName(smallest.getName());
            current.setSize(smallest.getSize());
            current.setDirectory(smallest.isDirectory());
            current.setRight(deleteRec(current.getRight(), smallest.getName()));
            return current;
        }

        if (name.compareTo(current.getName()) < 0) {
            current.setLeft(deleteRec(current.getLeft(), name));
        } else {
            current.setRight(deleteRec(current.getRight(), name));
        }

        return current;
    }
    public FileNode searchByName(String name) {
        return searchByNameRec(root, name);
    }

    private FileNode searchByNameRec(FileNode current, String name) {
        if (current == null) {
            return null;
        }
        if (name.equals(current.getName())) {
            return current;
        }
        return name.compareTo(current.getName()) < 0 
               ? searchByNameRec(current.getLeft(), name) 
               : searchByNameRec(current.getRight(), name);
    }

    // Search by size (returns the first node found with the given size)
    public FileNode searchBySize(long size) {
        return searchBySizeRec(root, size);
    }

    private FileNode searchBySizeRec(FileNode current, long size) {
        if (current == null) {
            return null;
        }
        if (size == current.getSize()) {
            return current;
        }
        return size < current.getSize() 
               ? searchBySizeRec(current.getLeft(), size) 
               : searchBySizeRec(current.getRight(), size);
    }

    private FileNode findSmallest(FileNode root) {
        return root.getLeft() == null ? root : findSmallest(root.getLeft());
    }

    // Search for a node by name
    public FileNode search(String name) {
        return searchRec(root, name);
    }

    private FileNode searchRec(FileNode current, String name) {
        if (current == null || name.equals(current.getName())) {
            return current;
        }

        return name.compareTo(current.getName()) < 0
                ? searchRec(current.getLeft(), name)
                : searchRec(current.getRight(), name);
    }

    // In-order Traversal
    public void inOrderTraversal() {
        inOrderRec(root);
    }

    private void inOrderRec(FileNode node) {
        if (node != null) {
            inOrderRec(node.getLeft());
            System.out.println("File: " + node.getName() + " | Size: " + node.getSize() + " bytes");
            inOrderRec(node.getRight());
        }
    }

    // Pre-order Traversal
    public void preOrderTraversal() {
        preOrderRec(root);
    }

    private void preOrderRec(FileNode node) {
        if (node != null) {
            System.out.println(node.getName());
            preOrderRec(node.getLeft());
            preOrderRec(node.getRight());
        }
    }

    // Post-order Traversal
    public void postOrderTraversal() {
        postOrderRec(root);
    }

    private void postOrderRec(FileNode node) {
        if (node != null) {
            postOrderRec(node.getLeft());
            postOrderRec(node.getRight());
            System.out.println(node.getName());
        }
    }
}

   

