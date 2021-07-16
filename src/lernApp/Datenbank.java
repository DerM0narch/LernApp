package lernApp;

import java.sql.*;

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
}
