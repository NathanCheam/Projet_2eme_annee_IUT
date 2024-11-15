package main;

import main.problem.Problem;
import main.problem.ProblemBuilder;
import main.process.*;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * Test class for executing different types of processes (C++, C, Python, Java).
 * @author Derancourt Louis
 * @author Notteau Romain
 * @author Fauquet Sacha
 * @author Cheam Nathan
 */
class TestExecute {

    /**
     * Tests the execution of a C++ process.
     */
    @Test
    void testCppExecute() {
        File srcfile = FileManager.fileFromPath("src/test/resources/testCpp.cpp");
        File datafile = FileManager.fileFromPath("src/test/resources/data");
        Judge j = new Judge(srcfile, datafile);

        IProcess comp = new CppProcess();
        File finalFile = comp.compile(j, srcfile);

        ProblemBuilder pb = new ProblemBuilder();
        pb.addTestCase(datafile);
        Problem p = pb.build();

        comp.execute(j, finalFile, p);
    }

    /**
     * Tests the execution of a C process.
     */
    @Test
    void testCExecute() {
        File srcfile = FileManager.fileFromPath("src/test/resources/testC.c");
        File datafile = FileManager.fileFromPath("src/test/resources/data");
        Judge j = new Judge(srcfile, datafile);

        IProcess comp = new CProcess();
        File finalFile = comp.compile(j, srcfile);

        ProblemBuilder pb = new ProblemBuilder();
        pb.addTestCase(datafile);
        Problem p = pb.build();

        comp.execute(j, finalFile, p);
    }

    /**
     * Tests the execution of a Python process.
     */
    @Test
    void testPythonExecute() {
        File srcfile = FileManager.fileFromPath("src/test/resources/testPy.py");
        File datafile = FileManager.fileFromPath("src/test/resources/data");
        Judge j = new Judge(srcfile, datafile);

        IProcess comp = new PythonProcess();
        File finalFile = comp.compile(j, srcfile);

        ProblemBuilder pb = new ProblemBuilder();
        pb.addTestCase(datafile);
        Problem p = pb.build();

        comp.execute(j, finalFile, p);
    }

    /**
     * Tests the execution of a Java process.
     */
    @Test
    void testJavaExecute() {
        File srcfile = FileManager.fileFromPath("src/test/resources/TestJava.java");
        File datafile = FileManager.fileFromPath("src/test/resources/data");
        Judge j = new Judge(srcfile, datafile);

        IProcess comp = new JavaProcess();
        File finalFile = comp.compile(j, srcfile);

        ProblemBuilder pb = new ProblemBuilder();
        pb.addTestCase(datafile);
        Problem p = pb.build();

        comp.execute(j, finalFile, p);
    }

}