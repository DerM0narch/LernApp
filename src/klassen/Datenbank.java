package klassen;

import java.sql.*;

public class Datenbank {
    private Connection c;
    private Statement stmt;
    private final String URL = "jdbc:sqlite:res/fragenkatalog.db";

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
