package it.unibo.deathnote.api;

public interface DeathNoteSubject {
    String getName();
    String getCause();
    String getDetails();
    void setDeathCause(String deathCause);
    void setDeathDetails(String deathDetails);
    
}
