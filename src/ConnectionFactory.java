import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
//factory connections é chamada quando preciso ligar algo ao DB

  public DataSource dataSource;

  public ConnectionFactory() {
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/loja_virtual?useTimezone=true&serverTimezone=UTC");
    comboPooledDataSource.setUser("root");
    comboPooledDataSource.setPassword("Linha123@");

    comboPooledDataSource.setMaxPoolSize(15);

    this.dataSource = comboPooledDataSource;
  }//construtor que cria o objeto dataSource para acessar o DB, toda vez que um user terminar, vem outro

  public Connection recuperarConexao() throws SQLException {

    return this.dataSource.getConnection();


  }
}
