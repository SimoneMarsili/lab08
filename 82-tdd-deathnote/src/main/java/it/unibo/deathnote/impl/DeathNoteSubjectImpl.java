package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNoteSubject;

public class DeathNoteSubjectImpl implements DeathNoteSubject {

    private final static String DEFAULT_DEATH_CAUSE = "Heart attack";
    private final String name;
    private String deathCause;
    private String deathDetails;

    public DeathNoteSubjectImpl(final String name) {
        this.name = name;
        this.deathCause = DEFAULT_DEATH_CAUSE;
        this.deathDetails = new String();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCause() {
        return this.deathCause;
    }

    @Override
    public String getDetails() {
        return this.deathDetails;
    }

    @Override
    public void setDeathCause(final String deathCause) {
        this.deathCause = deathCause;
    }

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
}
