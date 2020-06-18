package locadora.locadoradecarrosmaven;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rolucon
 */
public class ConnectionFactory {
private static final String DB_NAME = "locadora";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;            
    private static final String USER = "root";
    private static final String PW = "";
    public static Connection getConnection() throws SQLException {
        // Mudei a forma de conseguir a conexão porque não estava funcionando e baseado em https://stackoverflow.com/a/2839563
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(USER);
        dataSource.setPassword(PW);
        System.out.println(URL);
        dataSource.setURL(URL);
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException ex) {
            throw new RuntimeException("CONN");
//            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
//            throw new SQLException("Erro de Conexao", ex);
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) throws SQLException {
        conn.close();
    }

    }
    

