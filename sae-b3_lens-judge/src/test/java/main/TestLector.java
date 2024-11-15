package main;

import main.process.verification.AllLector;
import main.process.verification.LineLector;
import main.process.verification.OneLector;
import main.process.verification.SpaceIgnoreValidator;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * Test class for various Lector implementations using SpaceIgnoreValidator.
 * @author Fauquet Sacha
 */
class TestLector {

    /**
     * Tests the OneLector class with a SpaceIgnoreValidator.
     */
    @Test
    void testOneLector() {
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
     * Tests the AllLector class with a SpaceIgnoreValidator.
     */
    @Test
    void testAllLector() {
        File sourceFile = new File("src/test/resources/submissions/timethroughtheglass.c");
        File data = new File("src/test/resources/data");
        assert sourceFile.exists();
        assert data.exists();

        Judge judge = new Judge(sourceFile, data);
        judge.addValidator(SpaceIgnoreValidator.class);
        judge.setLector(AllLector.class);

        System.out.println(judge.run().toString());
    }

    /**
     * Tests the LineLector class with a SpaceIgnoreValidator.
     */
    @Test
    void testLineLector() {
        File sourceFile = new File("src/test/resources/submissions/timethroughtheglass.c");
        File data = new File("src/test/resources/data");
        assert sourceFile.exists();
        assert data.exists();

        Judge judge = new Judge(sourceFile, data);
        judge.addValidator(SpaceIgnoreValidator.class);
        judge.setLector(LineLector.class);

        System.out.println(judge.run().toString());
    }
}