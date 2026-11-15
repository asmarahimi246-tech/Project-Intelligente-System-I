package util;

import org.junit.jupiter.api.Test;

import app.util.NameGenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NameGeneratorTest {

    /**
     * Test if the name is of the correct format
     */
    @Test
    void getName() {
        NameGenerator generator = new NameGenerator();

        String name = generator.getName();

        assertTrue(name.matches("Player\\d{4}"));
    }
}