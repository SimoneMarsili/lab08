package it.unibo.deathnote;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDeathNote {
    private static final int TEST_TRIES = 10;
    private static final int TEST_CAUSE_TOO_LONG = 100;
    private static final int TEST_DETAILS_TOO_LONG = 6100;
    private static final String TEST_DETAILS_NAME = "Waluigi";
    private static final String TEST_HUMAN_NAME = "Mario";
    private DeathNote book;

    /**
     * Configuration step.
     */
    @BeforeEach
    void setUp() {
        this.book = new DeathNoteImpl(); //test implementation
    }

    @Test
    void testWrongRuleIndex() {
        for (int i = 0; i < TEST_TRIES; i++) {
            try {
                book.getRule(-i);
                fail("Getting a rule with invalid index was possible but should have thrown an exception");
            } catch (final IllegalArgumentException e) {
                assertNotNull(e.getMessage());
                assertFalse(e.getMessage().isBlank());
            }
        }
    }

    @Test
    void testNullRule() {
        for (int i = 1; i <= DeathNote.RULES.size(); i++) {
            assertNotNull(book.getRule(i));
            assertFalse(book.getRule(i).isBlank());
        }
    }

    @Test
    void testHumanDeath() {
        assertFalse(book.isNameWritten(TEST_HUMAN_NAME));
        book.writeName(TEST_HUMAN_NAME);
        assertTrue(book.isNameWritten(TEST_HUMAN_NAME));
        assertFalse(book.isNameWritten("Luigi"));
        assertFalse(book.isNameWritten(""));
    }

    @Test
    void testDeathCause() {
        try {
            book.writeDeathCause("Chocking");
            fail("Writing a deathcasue without a previous name was possible, but should have thrown an exception");
        } catch (final IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        book.writeName("Peach");
        assertEquals(book.getDeathCause("Peach"), "Heart attack");
        book.writeName("Bowser");
        assertTrue(book.writeDeathCause("karting accident"));
        assertEquals(book.getDeathCause("Bowser"), "karting accident");
        try {
            Thread.sleep(TEST_CAUSE_TOO_LONG);
        } catch (final InterruptedException e) {
            fail("Exception occurred in Thread sleeping: " + e.getMessage());
        }
        assertFalse(book.writeDeathCause("Train accident"));
    }

    @Test
    void testDeathDetails() {
        try {
            book.writeDetails("Multiple organ failure");
            fail("Writing a death detail without a previous name was possible, but should have thrown an exception");
        } catch (final IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        book.writeName(TEST_DETAILS_NAME);
        assertTrue(book.getDeathDetails(TEST_DETAILS_NAME).isBlank());
        assertTrue(book.writeDetails("ran for too long"));
        assertEquals(book.getDeathDetails(TEST_DETAILS_NAME), "ran for too long");
        book.writeName("Koopa");
        try {
            Thread.sleep(TEST_DETAILS_TOO_LONG);
        } catch (final InterruptedException e) {
            fail("Exception occurred in Thread sleeping: " + e.getMessage());
        }
        assertFalse(book.writeDetails("Overdose di metanfetamina"));
        assertTrue(book.getDeathDetails("Koopa").isBlank());
    }
}
