package main;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;


/**
 * Test class for the FileManager utility methods.
 * @author Notteau Romain
 * @author Derancourt Louis
 */
class TestFileManager {

    /**
     * Tests the fileFromPath method to ensure it returns null for non-existent files
     * and a File object for existing files.
     */
    @Test
    void testFileFromPath() {
        Assertions.assertNull(FileManager.fileFromPath("test.txt"));
        Assertions.assertNotNull(FileManager.fileFromPath("src/main/java/main/FileManager.java"));
    }

    /**
     * Tests the isExtension method to ensure it correctly identifies file extensions.
     */
    @Test
    void testIsExtension() {
        File file = FileManager.fileFromPath("src/main/java/main/FileManager.java");
        Assertions.assertFalse(FileManager.isExtension(file, "cpp"));
        Assertions.assertTrue(FileManager.isExtension(file, "java"));
    }

    /**
     * Tests the getFileExtension method to ensure it correctly returns the file extension.
     */
    @Test
    void testGetFileExtension() {
        Assertions.assertEquals("java", FileManager.getFileExtension(FileManager.fileFromPath("src/main/java/main/FileManager.java")));
    }

}
