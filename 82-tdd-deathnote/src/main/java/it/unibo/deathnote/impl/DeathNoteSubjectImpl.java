package it.unibo.deathnote.impl;

import java.util.Objects;

import it.unibo.deathnote.api.DeathNoteSubject;

/**
 * Implementation of the interface DeathNoteSubject.
 */
public class DeathNoteSubjectImpl implements DeathNoteSubject {

    private static final String DEFAULT_DEATH_CAUSE = "Heart attack";
    private final String name;
    private String deathCause;
    private String deathDetails;
    private int hash;

    /**
     * Contructs a new subject for the DeathNote.
     *
     * @param name the name of the person 
     */
    public DeathNoteSubjectImpl(final String name) {
        this.name = name;
        this.deathCause = DEFAULT_DEATH_CAUSE;
        this.deathDetails = "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCause() {
        return this.deathCause;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDetails() {
        return this.deathDetails;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDeathCause(final String deathCause) {
        this.deathCause = deathCause;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDeathDetails(final String deathDetails) {
        this.deathDetails = deathDetails;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && getClass().equals(o.getClass())) {
            final DeathNoteSubjectImpl subject = (DeathNoteSubjectImpl) o;
            return name.equals(subject.getName());
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        /*
         * All fields are final and immutable: lazy initialization allowed.
         */
        if (hash == 0) {
            hash = Objects.hash(name);
        }
        return hash;
    }
}
