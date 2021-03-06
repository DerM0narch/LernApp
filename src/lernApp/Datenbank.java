package lernApp;

import lernAppGUI.ControllerSchwerpunkt;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Verbindung zur Datenbank mit Befehlen
 */
public class Datenbank {
    private Connection c;
    private Statement stmt;
    private final String URL = "jdbc:sqlite:res/fragenkatalog.db";

    /**
     * Befehl zum erstellen von Tabellen
     *
     * @param sql SQL Syntax verwenden (z.B. CREATE TABLE ...)
     */
    public void execute(String sql) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);

            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    /**
     * Befehl zum erstellen von Inhalten für Tabellen
     *
     * @param sql SQL Syntax verwenden (z.B. INSERT INTO ... VALUES ...)
     */
    public void insert(String sql) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);

            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    /**
     * Select id as int.
     *
     * @param sql the sql statement
     * @return the id
     */
    public int selectId(String sql) {
        int id = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            id = rs.getInt("id");

            rs.close();
            stmt.close();
            c.close();
            return id;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return id;
    }

    /**
     * Select frage as string.
     *
     * @param sql the sql statement
     * @return frage
     */
    public String selectFrage(String sql) {
        String frage = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            frage = rs.getString("frage");

            rs.close();
            stmt.close();
            c.close();
            return frage;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return frage;
    }

    /**
     * Select richtig as string.
     *
     * @param sql the sql statement
     * @return richtige Antwort
     */
    public String selectRichtig(String sql) {
        String richtig = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            richtig = rs.getString("richtig");

            rs.close();
            stmt.close();
            c.close();
            return richtig;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return richtig;
    }

    /**
     * Select erste falsche Antwort as string.
     *
     * @param sql the sql statement
     * @return erste falsche Antwort
     */
    public String selectErsteFalsch(String sql) {
        String falsch = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            falsch = rs.getString("ersteFalsch");

            rs.close();
            stmt.close();
            c.close();
            return falsch;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return falsch;
    }

    /**
     * Select zweite falsche Antwort as string.
     *
     * @param sql the sql statement
     * @return zweite falsche Antwort
     */
    public String selectZweiteFalsch(String sql) {
        String falsch = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            falsch = rs.getString("zweiteFalsch");

            rs.close();
            stmt.close();
            c.close();
            return falsch;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return falsch;
    }

    /**
     * Select markiert as boolean
     *
     * @param sql the sql statement
     * @return markiert as boolean
     */
    public boolean selectMarkiert(String sql) {
        int markiert = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            markiert = rs.getInt("markiert");

            rs.close();
            stmt.close();
            c.close();
            return markiert != 0;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return markiert != 0;
    }

    /**
     * Counts questions
     *
     * @return Number of total questions
     */
    public int selectCount() {
        int count = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) as total from fragen");
            count = rs.getInt("total");

            rs.close();
            stmt.close();
            c.close();
            return count;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return count;
    }

    /**
     *
     * wählt alle Fragen die makiert wurden aus
     *
     * @return Liste von IDs der makierten Fragen
     */
    public Integer[] markierteFragen() {
        try {
            Set<Integer> markierteFragenHash = new LinkedHashSet<Integer>();
            Integer[] markierteFragenArray;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from fragen where markiert = 1");
            while (rs.next()) {
                markierteFragenHash.add(rs.getInt("id"));
            }
            markierteFragenArray = new Integer[markierteFragenHash.size()];
            markierteFragenArray = markierteFragenHash.toArray(markierteFragenArray);

            rs.close();
            stmt.close();
            c.close();

            return markierteFragenArray;

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return new Integer[]{0};
    }

    /**
     *
     * @param schwerpunkt verwendet um Fragen nach diesem Schwerpunkt zu suchen
     *
     * @return Liste von IDs der Fragen zum schwerpunkt
     */
    public Integer[] schwerpunktFragen(String schwerpunkt) {
        try {
            Set<Integer> schwerpunktFragenHash = new LinkedHashSet<Integer>();
            Integer[] SchwerpunktFragenArray;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from fragen where schwerpunkt = '" + schwerpunkt + "'");

            while (rs.next()) {
                schwerpunktFragenHash.add(rs.getInt("id"));
            }

            SchwerpunktFragenArray = new Integer[schwerpunktFragenHash.size()];
            SchwerpunktFragenArray = schwerpunktFragenHash.toArray(SchwerpunktFragenArray);

            rs.close();
            stmt.close();
            c.close();

            return SchwerpunktFragenArray;

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return new Integer[]{0};
    }

    /**
     * wählt Fragen aus die falsch beantwortet wurden
     *
     * @return Liste von IDs der Fragen zum schwerpunkt
     */
    public Integer[] falscheFragen() {
        try {
            Set<Integer> falscheFragenHash = new LinkedHashSet<Integer>();
            Integer[] falscheFragenArray;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from fragen where istfalsch = 1");
            while (rs.next()) {
                falscheFragenHash.add(rs.getInt("id"));
            }
            falscheFragenArray = new Integer[falscheFragenHash.size()];
            falscheFragenArray = falscheFragenHash.toArray(falscheFragenArray);

            rs.close();
            stmt.close();
            c.close();

            return falscheFragenArray;

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return new Integer[]{0};
    }

    /**
     * ermittelt wie viele falsche Fragen in der db sind
     * @return count Anzahl von falschen Fragen
     */
    public int Countfalsche() {
        int count = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) as total from fragen where istfalsch = 1");
            count = rs.getInt("total");

            rs.close();
            stmt.close();
            c.close();
            return count;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return count;
    }

    /**
     * ermittelt wie viele markierten Fragen in der db sind
     * @return count Anzahl von markierten Fragen
     */
    public int countMarkiert() {
        int count = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) as total from fragen where markiert = 1");
            count = rs.getInt("total");

            rs.close();
            stmt.close();
            c.close();
            return count;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return count;
    }

    /**
     * Ermittelt alle IDs aus der db, fügt diese in eine ArrayList
     * @return allIDs, Liste mit allen IDs
     */
    public ArrayList<Integer> selectAllFragenIDs() {

        ArrayList <Integer> allIDs = new ArrayList<Integer>();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select id from fragen");
            for (int i = 0; rs.next(); i++) {
                allIDs.add(rs.getInt("id"));
            }
            rs.close();
            stmt.close();
            c.close();
            return allIDs;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return allIDs;
    }

}
