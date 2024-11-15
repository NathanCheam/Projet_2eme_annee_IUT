package main;

import main.process.verification.CasseIgnoreValidator;
import main.process.verification.SpaceIgnoreValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * Test class for the Judge validators.
 * This class contains unit tests for verifying the list of validators in the Judge class.
 * @author Fauquet Sacha
 */
class TestListeJudgeValidator {

    /**
     * Tests the list of validators in the Judge class.
     * Ensures that the list of validators is not null after adding validators.
     */
    @Test
    void test() {
        File file = FileManager.fileFromPath("src/test/resources/program.txt");
        Judge juge = new Judge(file, file);
        juge.addValidator(SpaceIgnoreValidator.class);
        juge.addValidator(CasseIgnoreValidator.class);
        System.out.println("test" + juge.getListeJudgeValidator());
        System.out.println("test 2" + juge.getValidatorList());
        Assertions.assertNotNull(juge.getListeJudgeValidator());
    }
}