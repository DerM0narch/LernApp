package klassen;

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
     * @param sql SQL Syntax verwenden (z.B. INSERT INTO ... VALUES ...)
     */
    public void insert(String sql) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    /**
     * Befehl zum auswählen von Inhalten in Tabellen
     * @param sql SQL Syntax verwenden (z.B. SELECT ... FROM ...)
     * @return ResultSet
     * @throws SQLException Wenn Verbindung mit Datenbank misslingt
     * @throws ClassNotFoundException wemm Statement nicht gefunden wird
     */
    public ResultSet select(String sql) throws SQLException, ClassNotFoundException {
        ResultSet rs;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);

            rs.close();
            stmt.close();
            c.close();

        return rs;
    }
}
