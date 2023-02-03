import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
//factory connections é chamada quando preciso ligar algo ao DB

  public Connection recuperarConexao() throws SQLException {

    return DriverManager
      .getConnection //estabelecendo conexão
        ("jdbc:mysql://localhost:3306/loja_virtual?useTimezone=true&serverTimezone=UTC",
          "root", "");


  }
}
