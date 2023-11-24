public class FileNode {
    private String name;
    private long size; // in bytes
    private boolean isDirectory;
    private FileNode left;
    private FileNode right;

    public FileNode(String name, long size, boolean isDirectory) {
        this.name = name;
        this.size = size;
        this.isDirectory = isDirectory;
        this.left = null;
        this.right = null;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

    public FileNode getLeft() {
        return left;
    }

    public void setLeft(FileNode left) {
        this.left = left;
    }

    public FileNode getRight() {
        return right;
    }

    public void setRight(FileNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "FileNode{" +
               "name='" + name + '\'' +
               ", size=" + size +
               (isDirectory ? ", is a directory" : ", is a file") +
               '}';
    }
}
