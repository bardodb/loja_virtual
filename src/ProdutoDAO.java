import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

  private final Connection connection;

  public ProdutoDAO(Connection connection) {
    this.connection = connection;
  }

  public void salvarProduto(Produto produto) throws SQLException {

    String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (? , ?)";

    try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstm.setString(1, produto.getNome());
      pstm.setString(2, produto.getDescricao());

      pstm.execute();

      try (ResultSet rst = pstm.getGeneratedKeys()) {
        while (rst.next()) {
          produto.setId(rst.getInt(1));
        }
      }
    }
  }

  public List<Produto> listarProdutos() throws SQLException {
    List<Produto> produtos = new ArrayList<Produto>();
    String sql = "SELECT ID, NOME PRODUTO, DESCRICAO FROM PRODUTO";
    try (PreparedStatement pstm = connection.prepareStatement(sql)) {
      pstm.execute();
      try (ResultSet rst = pstm.getResultSet()) {
        while (rst.next()) {
          Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
          produtos.add(produto);
        }
      }
    }
    return produtos;
  }

  public List<Produto> buscar(Categoria ct) throws SQLException {
    List<Produto> produtos = new ArrayList<Produto>();
    String sql = "SELECT ID, NOME PRODUTO, DESCRICAO FROM PRODUTO WHERE categoria_id = ?";
    try (PreparedStatement pstm = connection.prepareStatement(sql)) {
      pstm.setInt(1, ct.getID());
      pstm.execute();
      try (ResultSet rst = pstm.getResultSet()) {
        while (rst.next()) {
          Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
          produtos.add(produto);
        }

      }
    }
    return produtos;
  }
}

