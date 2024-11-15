package main;

import main.process.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.File;

/**
 * Test class for various compilers.
 * @author Notteau Romain
 */
class TestCompiler {

    /**
     * Test the Python compiler.
     */
    @Test
    void testPythonCompile() {
        File srcfile = FileManager.fileFromPath("src/test/resources/testPy.py");
        Judge j = new Judge(srcfile, srcfile);
        IProcess comp = new PythonProcess();
        File finalFile = comp.compile(j, srcfile);
        System.out.println(finalFile.getAbsolutePath());
        assertNotNull(finalFile.getAbsolutePath());
    }

    /**
     * Test the C++ compiler.
     */
    @Test
    void testCppCompile() {
        File srcfile = FileManager.fileFromPath("src/test/resources/testCpp.cpp");
        Judge j = new Judge(srcfile, srcfile);
        IProcess comp = new CppProcess();
        File finalFile = comp.compile(j, srcfile);
        System.out.println(finalFile.getAbsolutePath());
        assertNotNull(finalFile.getAbsolutePath());
    }

    /**
     * Test the C compiler.
     */
    @Test
    void testCCompile() {
        File srcfile = FileManager.fileFromPath("src/test/resources/testC.c");
        Judge j = new Judge(srcfile, srcfile);
        IProcess comp = new CProcess();
        File finalFile = comp.compile(j, srcfile);
        System.out.println(finalFile.getAbsolutePath());
        assertNotNull(finalFile.getAbsolutePath());
    }

    /**
     * Test the Java compiler.
     */
    @Test
    void testJavaCompile() {
        File srcfile = FileManager.fileFromPath("src/test/resources/TestJava.java");
        Judge j = new Judge(srcfile, srcfile);
        IProcess comp = new JavaProcess();
        File finalFile = comp.compile(j, srcfile);
        System.out.println(finalFile.getAbsolutePath());
        assertNotNull(finalFile.getAbsolutePath());
    }
}