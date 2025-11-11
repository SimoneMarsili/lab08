package it.unibo.deathnote.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.api.DeathNoteSubject;

/**
 * Implementation of the interface Deathnote.
 */
public class DeathNoteImpl implements DeathNote {

    private static final int DEATH_CAUSE_TIME_LIMIT = 40;
    private static final int DETAILS_TIME_LIMIT = 6040;
    private final List<DeathNoteSubject> subjects = new ArrayList<>();
    private Long time = 0L;

    /**
     * Creates a new instance of {@code DeathNoteImpl}.
     */
    public DeathNoteImpl() {
        //default constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("Rule index out of range: should be between 1 and " + RULES.size());
        }
        return RULES.get(ruleNumber - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeName(final String name) {
        if (name == null) { //persone con stesso nome?
            throw new NullPointerException("The name given was null"); // NOPMD
            //the method has to trow this exception in this case
        }
        subjects.add(new DeathNoteSubjectImpl(name));
        this.time = System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDeathCause(final String cause) {
        if (this.subjects.isEmpty() || cause == null) {
            throw new IllegalStateException("There was no name in the book or the specified cause was null");
        }
        if (System.currentTimeMillis() - this.time > DEATH_CAUSE_TIME_LIMIT) {
            return false;
        }
        subjects.getLast().setDeathCause(cause);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDetails(final String details) {
        if (this.subjects.isEmpty() || details == null) {
            throw new IllegalStateException("There was no name in the book or the specified detail was null");
        }
        if (System.currentTimeMillis() - this.time > DEATH_CAUSE_TIME_LIMIT + DETAILS_TIME_LIMIT) {
            return false;
        }
        subjects.getLast().setDeathDetails(details);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathCause(final String name) {
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException("No such name in the book");
        }
        return subjects.get(subjects.indexOf(new DeathNoteSubjectImpl(name))).getCause();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathDetails(final String name) {
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException("No such name in the book");
        }
        return subjects.get(subjects.indexOf(new DeathNoteSubjectImpl(name))).getDetails();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNameWritten(final String name) {
        for (final DeathNoteSubject person : subjects) {
            if (person.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
