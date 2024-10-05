import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Variáveis de conexão
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_biblioteca"; // URL do banco de dados
    private static final String USER = "root"; // Usuário do MySQL
    private static final String PASSWORD = "12345"; // Senha do MySQL

    // Método que retorna uma conexão com o banco de dados
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Registrar o driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver"); // Certifique-se de que este driver está incluído no classpath
            // Estabelecer a conexão
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão bem-sucedida com o banco de dados!");
        } catch (SQLException e) {
            System.out.println("Erro de conexão com o banco de dados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
        }
        return connection; // Retorna a conexão ou null em caso de falha
    }
}

