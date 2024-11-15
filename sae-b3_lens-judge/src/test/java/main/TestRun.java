package main;

import main.process.verification.AbsoluteValidator;
import main.process.verification.AllLector;
import main.process.verification.OneLector;
import main.process.verification.SpaceIgnoreValidator;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * Test class for running various processes.
 * This class contains unit tests for running processes with different source files.
 * @author Derancourt Louis
 */
class TestRun {

    /**
     * Tests the C++ process.
     * Ensures that the source file and data directory exist, and runs the Judge on the C++ source file.
     */
    @Test
    void testCppProcess() {
        File sourceFile = new File("src/test/resources/submissions/timethroughtheglass.cc");
        File data = new File("src/test/resources/data");
        assert sourceFile.exists();
        assert data.exists();

        Judge judge = new Judge(sourceFile, data);

        System.out.println(judge.run().toString());
    }

    /**
     * Tests the C process.
     * Ensures that the source file and data directory exist, adds a SpaceIgnoreValidator, and runs the Judge on the C source file.
     */
    @Test
    void testCProcess() {
        File sourceFile = new File("src/test/resources/submissions/timethroughtheglass.c");
        File data = new File("src/test/resources/data");
        assert sourceFile.exists();
        assert data.exists();

        Judge judge = new Judge(sourceFile, data);
        judge.addValidator(SpaceIgnoreValidator.class);
        judge.setLector(OneLector.class);

        System.out.println(judge.run().toString());
    }

    /**
     * Tests the Java process.
     * Ensures that the source file and data directory exist, and runs the Judge on the Java source file.
     */
    @Test
    void testJavaProcess() {
        File sourceFile = new File("src/test/resources/submissions/TimeThroughTheGlass.java");
        File data = new File("src/test/resources/data");
        assert sourceFile.exists();
        assert data.exists();

        Judge judge = new Judge(sourceFile, data);

        System.out.println(judge.run().toString());
    }

    /**
     * Tests the Python process.
     * Ensures that the source file and data directory exist, and runs the Judge on the Python source file.
     */
    @Test
    void testPythonProcess() {
        File sourceFile = new File("src/test/resources/submissions/timethroughtheglass.py");
        File data = new File("src/test/resources/data");
        assert sourceFile.exists();
        assert data.exists();

        Judge judge = new Judge(sourceFile, data);

        System.out.println(judge.run().toString());
    }

}
