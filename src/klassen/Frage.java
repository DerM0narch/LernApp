package klassen;

public class Frage {

  private String fragestellung;
  private String[] antworten;

    public Frage(String fragestellung, String[] antworten) {
        this.fragestellung = fragestellung;
        this.antworten = antworten;
    }

    public String getFragestellung() {
        return fragestellung;
    }

    public void setFragestellung(String fragestellung) {
        this.fragestellung = fragestellung;
    }

    public String[] getAntworten() {
        return antworten;
    }

    public void setAntworten(String[] antworten) {
        this.antworten = antworten;
    }
}
