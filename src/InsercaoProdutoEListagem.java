import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class InsercaoProdutoEListagem {

  public static void main(String[] args) throws SQLException {

    Produto comoda = new Produto("Cômoda", "comoda vertical");

    try (Connection connection = new ConnectionFactory().recuperarConexao()) {
      ProdutoDAO produtoDao = new ProdutoDAO(connection);
      produtoDao.salvarProduto(comoda);
      List<Produto> ListaDeProdutos = produtoDao.listarProdutos();
      ListaDeProdutos.stream().forEach(lp -> System.out.println(lp));
    }
    System.out.println("Produto salvo com sucesso!");
  }
}


