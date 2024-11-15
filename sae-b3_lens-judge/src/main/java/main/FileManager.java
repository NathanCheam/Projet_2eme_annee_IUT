package main;

import java.io.File;

/**
 * The FileManager class provides utility methods for file operations.
 * @author Notteau Romain
 * @author Derancourt Louis
 */
public class FileManager {

    // Private constructor to prevent instantiation
    private FileManager() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Returns a File object for the given path if the file exists, otherwise returns null.
     *
     * @param path the path to the file
     * @return the File object if the file exists, otherwise null
     */
    public static File fileFromPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        return file;
    }

    /**
     * Returns the extension of the given file.
     *
     * @param file the file
     * @return the extension of the file
     */
    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf + 1);
    }

    /**
     * Checks if the file at the given path has the specified extension.
     *
     * @param file the path to the file
     * @param extension the extension to check for
     * @return true if the file has the specified extension, otherwise false
     */
    public static boolean isExtension(File file, String extension) {
        if (!file.exists()) {
            return false;
        }
        return file.getName().endsWith("." + extension);
    }

}
