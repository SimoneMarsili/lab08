package it.unibo.deathnote.api;

/**
 * This interface modelizes a person written in a DeathNote, a special book.
 * 
 * @see DeathNote
 */
public interface DeathNoteSubject {

    /**
     * Returns the name of this subject.
     *
     * @return the name of the subject
     */
    String getName();

    /**
     * Provides the cause of the death of this subject.
     *
     * @return the cause of death of the subject
     */
    String getCause();

    /**
     * Provides the details of the death of this subject.
     *
     * @return the death details of the subject
     */
    String getDetails();

    /**
     * Sets the cause of the death of this subject.
     *
     * @param deathCause the cause of death to set
     */
    void setDeathCause(String deathCause);

    /**
     * Sets the details of the death of this subject.
     *
     * @param deathDetails the details of death to set
     */
    void setDeathDetails(String deathDetails);
}
