package main;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;

/**
 * The TestJudge class provides utility methods for testing the Judge class.
 * @author Derancourt Louis
 */
class TestJudge {

    /**
     * Tests the results of the Judge.
     */
    @Test
    void testResults() {
        File file = FileManager.fileFromPath("src/test/resources/program.txt");
        Judge j = new Judge(file, file); // ignoring the file type
        String a = "Test", b = "Test 2";
        Result valid = new Result(false, b + System.lineSeparator() + a);

        j.setResult(new Result(true, a));
        j.setResult(new Result(false, b));
        Assertions.assertEquals(valid.toString(), j.getResult().toString());
    }

    @Test
    void testJudge() {
        File prog = FileManager.fileFromPath("src/test/resources/testCpp.cpp");
        File data = FileManager.fileFromPath("src/test/resources/data");
        assert prog != null;
        assert data != null;
        Judge j = new Judge(prog, data); // ignoring the file type

        j.run();

    }

}
