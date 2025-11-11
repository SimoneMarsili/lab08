package it.unibo.deathnote;

import it.unibo.deathnote.api.*;
import it.unibo.deathnote.impl.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TestDeathNote {
    private final static int TEST_TRIES = 10;
    private DeathNote book;

    /**
     * Configuration step
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
            } catch(IllegalArgumentException e) {
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
        assertFalse(book.isNameWritten("Mario"));
        try {
            book.writeName(null);
            fail("Adding a null name was possibile but should have thrown an exception");
        } catch(NullPointerException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        book.writeName("Mario");
        assertTrue(book.isNameWritten("Mario"));
        assertFalse(book.isNameWritten("Luigi"));
        assertFalse(book.isNameWritten(""));
    }

    @Test
    void testDeathCause() {
        try {
            book.writeDeathCause("Chocking");
            fail("Writing a deathcasue without a previous name was possible, but should have thrown an exception");
        } catch(IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        book.writeName("Peach");
        assertEquals(book.getDeathCause("Peach"), "Heart attack");
        book.writeName("Bowser");
        assertTrue(book.writeDeathCause("karting accident"));
        assertEquals(book.getDeathCause("Bowser"), "karting accident");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertFalse(book.writeDeathCause("Train accident"));
        
    }


    @Test
    void testDeathDetails() {
        try {
            book.writeDetails("Multiple organ failure");
            fail("Writing a death detail without a previous name was possible, but should have thrown an exception");
        } catch(IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        book.writeName("Waluigi");
        assertTrue(book.getDeathDetails("Waluigi").isBlank());
        assertTrue(book.writeDetails("ran for too long"));
        assertEquals(book.getDeathDetails("Waluigi"), "ran for too long");
        book.writeName("Koopa");
        try {
            Thread.sleep(6100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertFalse(book.writeDetails("Overdose di metanfetamina"));
        assertTrue(book.getDeathDetails("Koopa").isBlank());
    }





}