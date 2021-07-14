package klassen;

public class Schwerpunkt {

    private String schwerpunkt;
    private Frage[] fragen;

    public Schwerpunkt(String schwerpunkt, Frage[] fragen) {
        this.schwerpunkt = schwerpunkt;
        this.fragen = fragen;
    }

    public String getSchwerpunkt() {
        return schwerpunkt;
    }

    public void setSchwerpunkt(String schwerpunkt) {
        this.schwerpunkt = schwerpunkt;
    }

    public Frage[] getFragen() {
        return fragen;
    }

    public void setFragen(Frage[] fragen) {
        this.fragen = fragen;
    }
}
