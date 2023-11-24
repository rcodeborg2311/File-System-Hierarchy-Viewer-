import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSystemScanner {

    public List<FileNode> scanDirectory(String path) {
        List<FileNode> fileNodes = new ArrayList<>();
        File rootDir = new File(path);

        scanAndAddToList(rootDir, fileNodes);

        return fileNodes;
    }

    private void scanAndAddToList(File file, List<FileNode> fileNodes) {
        if (file == null || !file.exists()) {
            return;
        }

        FileNode node = new FileNode(file.getName(), file.length(), file.isDirectory());
        fileNodes.add(node);

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    scanAndAddToList(f, fileNodes);
                }
            }
        }
    }
}

