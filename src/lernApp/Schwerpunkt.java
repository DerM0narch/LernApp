package lernApp;

/**
 * Schwerpunktklasse - Aufteilung der Fragen in Schwerpunkte
 */
public class Schwerpunkt {

    private String schwerpunkt;
    private Frage[] fragen;

    /**
     * Instantiates a new Schwerpunkt.
     *
     * @param schwerpunkt the schwerpunkt
     * @param fragen      the fragen
     */
    public Schwerpunkt(String schwerpunkt, Frage[] fragen) {
        this.schwerpunkt = schwerpunkt;
        this.fragen = fragen;
    }

    /**
     * Gets schwerpunkt.
     *
     * @return schwerpunkt schwerpunkt
     */
    public String getSchwerpunkt() {
        return schwerpunkt;
    }

    /**
     * Sets schwerpunkt.
     *
     * @param schwerpunkt the schwerpunkt
     */
    public void setSchwerpunkt(String schwerpunkt) {
        this.schwerpunkt = schwerpunkt;
    }

    /**
     * Gets fragen.
     *
     * @return
     */
    public Frage[] getFragen() {
        return fragen;
    }

    /**
     * Sets fragen.
     *
     * @param fragen the fragen
     */
    public void setFragen(Frage[] fragen) {
        this.fragen = fragen;
    }
}
