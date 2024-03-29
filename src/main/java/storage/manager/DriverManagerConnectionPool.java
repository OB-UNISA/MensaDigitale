package storage.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * DriverManager.
 */
public class DriverManagerConnectionPool {

  private static final List<Connection> freeDbConnections;

  static {
    freeDbConnections = new LinkedList<>();
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("DB driver not found:" + e.getMessage());
      e.printStackTrace();
    }
  }

  public static List<Connection> getFreeDbConnections() {
    return freeDbConnections;
  }

  private static synchronized Connection createDbConnection() throws SQLException {
    Connection newConnection = null;
    String ip = "localhost";
    String port = "3306";
    String db = "MensaDigitale";
    String username = "esame";
    String password = "esame";
    newConnection =
        DriverManager.getConnection(
            "jdbc:mysql://"
                + ip
                + ":"
                + port
                + "/"
                + db
                + "?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
            username,
            password);
    System.out.println("Create a new DB connection");
    newConnection.setAutoCommit(false);
    return newConnection;
  }

  /**
   * Creazione di una connessione.
   */
  public static synchronized Connection getConnection() throws SQLException {
    Connection connection;
    if (!getFreeDbConnections().isEmpty()) {
      connection = getFreeDbConnections().get(0);
      getFreeDbConnections().remove(0);
      try {
        if (connection.isClosed()) {
          connection = getConnection();
        }
      } catch (SQLException e) {
        connection.close();
        connection = getConnection();
      }
    } else {
      connection = createDbConnection();
    }
    return connection;
  }

  /**
   * Rilascio connessione.
   */
  public static synchronized void releaseConnection(Connection connection) {
    if (connection != null) {
      getFreeDbConnections().add(connection);
    }
  }
}
