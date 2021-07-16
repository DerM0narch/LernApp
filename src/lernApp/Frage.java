package lernApp;

/**
 * Frageklasse - beinhaltet Fragen und Antworten
 */
public class Frage {

  private String fragestellung;
  private String[] antworten;
  private boolean makiert;

    /**
     * Instantiates a new Frage.
     *
     * @param fragestellung the fragestellung
     * @param antworten     the antworten
     */
    public Frage(String fragestellung, String[] antworten) {
        this.fragestellung = fragestellung;
        this.antworten = antworten;
    }

    /**
     * Gets fragestellung.
     *
     * @return the fragestellung
     */
    public String getFragestellung() {
        return fragestellung;
    }

    /**
     * Sets fragestellung.
     *
     * @param fragestellung the fragestellung
     */
    public void setFragestellung(String fragestellung) {
        this.fragestellung = fragestellung;
    }

    /**
     * Get antworten string [ ].
     *
     * @return the string [ ]
     */
    public String[] getAntworten() {
        return antworten;
    }

    /**
     * Sets antworten.
     *
     * @param antworten the antworten
     */
    public void setAntworten(String[] antworten) {
        this.antworten = antworten;
    }

    /**
     * Is makiert boolean.
     *
     * @return the boolean
     */
    public boolean isMakiert() {
        return makiert;
    }

    /**
     * Sets makiert.
     *
     * @param makiert the makiert
     */
    public void setMakiert(boolean makiert) {
        this.makiert = makiert;
    }
}
