import java.sql.Connection;
import java.sql.SQLException;


public class InsercaoProduto {

  public static void main(String[] args) throws SQLException {

    Produto comoda = new Produto("Cômoda", "comoda vertical");

    try (Connection connection = new ConnectionFactory().recuperarConexao()) {
      ProdutoDAO produtoDao = new ProdutoDAO(connection);
      produtoDao.salvarProduto(comoda);


    }
    System.out.println("Produto salvo com sucesso!");
  }
}


