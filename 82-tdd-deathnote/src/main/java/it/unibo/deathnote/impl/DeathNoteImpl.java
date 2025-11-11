package it.unibo.deathnote.impl;

import java.util.ArrayList;
import java.util.List;
import java.lang.System;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.api.DeathNoteSubject;

public class DeathNoteImpl implements DeathNote {

    private final static int DEATH_CAUSE_TIME_LIMIT = 40;
    private final static int DETAILS_TIME_LIMIT = 6040;
    private final List<DeathNoteSubject> subjects = new ArrayList<>();
    private Long time;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("Rule index out of range: should be between 1 and " + RULES.size());
        }
        return RULES.get(ruleNumber - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeName(String name) {
        if (name == null) { //persone con stesso nome?
            throw new NullPointerException("The name given was null");
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
    public boolean writeDetails(String details) {
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
    public String getDeathCause(String name) {
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException("No such name in the book");
        }
        return subjects.get(subjects.indexOf(new DeathNoteSubjectImpl(name))).getCause();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathDetails(String name) {
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException("No such name in the book");
        }
        return subjects.get(subjects.indexOf(new DeathNoteSubjectImpl(name))).getDetails();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNameWritten(String name) {
        for(final DeathNoteSubject person : subjects) {
            if (person.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
