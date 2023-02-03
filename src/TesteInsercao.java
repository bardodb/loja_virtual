import java.sql.*;

public class TesteInsercao {
  public static void main(String[] args) throws SQLException {


    ConnectionFactory criaConexao = new ConnectionFactory();
    Connection connection = criaConexao.recuperarConexao();
    PreparedStatement stmt = connection.prepareStatement(
      "INSERT INTO PRODUTO (nome, descricao) VALUES ('Mouse', 'Mouse sem fio')",
      Statement.RETURN_GENERATED_KEYS);


    boolean resultado =
      stmt.execute();
    ResultSet rst = stmt.getGeneratedKeys();
    while (rst.next()) {
      int id = rst.getInt(1);
      System.out.println("O id criado foi: " + id);
    }
    System.out.println(resultado);

  }


}
