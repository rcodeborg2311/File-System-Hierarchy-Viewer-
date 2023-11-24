import java.io.File;
import java.util.List;
import java.util.Scanner;

public class FileSystemViewerCLI {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String path;
            File dir;

            // Loop until a valid directory path is entered
            while (true) {
                System.out.println("Enter directory path to scan:");
                path = scanner.nextLine().trim(); // Get the user input

                dir = new File(path);
                if (dir.exists() && dir.isDirectory()) {
                    // If the path exists and it is a directory, break out of the loop
                    break;
                } else {
                    // If the path is not a valid directory, inform the user
                    System.out.println("The entered path is invalid or not a directory. Please try again.");
                }
            }

            FileSystemScanner fileSystemScanner = new FileSystemScanner();
            List<FileNode> fileNodes = fileSystemScanner.scanDirectory(path);

            FileBST fileTree = new FileBST(); // Create an empty FileBST

            System.out.println("Enter 1 to sort by name, 2 to sort by size:");
            int sortChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Insert nodes into the BST based on the chosen sorting criterion
            for (FileNode node : fileNodes) {
                if (sortChoice == 1) {
                    fileTree.insertByName(node);
                } else if (sortChoice == 2) {
                    fileTree.insertBySize(node);
                }
            }

            boolean running = true;
            while (running) {
                System.out.println("\nChoose an action:");
                System.out.println("1. Display file system (In-order Traversal)");
                System.out.println("2. Search for a file");
                System.out.println("3. Exit");
                System.out.print("Enter your choice (1-3): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        fileTree.inOrderTraversal();
                        break;
                    case 2:
                        if (sortChoice == 1) {
                            System.out.print("Enter file name to search: ");
                            String fileName = scanner.nextLine();
                            FileNode foundByName = fileTree.searchByName(fileName);
                            if (foundByName != null) {
                                System.out.println("File found: " + foundByName);
                            } else {
                                System.out.println("File not found.");
                            }
                        } else if (sortChoice == 2) {
                            System.out.print("Enter file size to search: ");
                            long fileSize = scanner.nextLong();
                            scanner.nextLine(); // Consume newline
                            FileNode foundBySize = fileTree.searchBySize(fileSize);
                            if (foundBySize != null) {
                                System.out.println("File found: " + foundBySize);
                            } else {
                                System.out.println("File not found.");
                            }
                        }
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}

