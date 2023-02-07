import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Remocao {
  public static void main(String[] args) throws SQLException {
    ConnectionFactory factory = new ConnectionFactory();
    Connection connection = factory.recuperarConexao();


    PreparedStatement stm = connection.prepareStatement("DELETE  FROM PRODUTO WHERE ID > ?");
    stm.setInt(1, 2);
    stm.execute();


    int linhasModificadas = stm.getUpdateCount();

    System.out.println("Quantidade de linhas apagadas " + linhasModificadas);


    connection.close();
  }


}
