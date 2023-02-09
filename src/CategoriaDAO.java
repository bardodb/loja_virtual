import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

  private final Connection conecction;

  public CategoriaDAO(Connection connection) {
    this.conecction = connection;
  }

  public List<Categoria> listar() throws SQLException {
    List<Categoria> categorias = new ArrayList<>();

    String sql = "SELECT * FROM categoria";
    try (PreparedStatement pstm = conecction.prepareStatement(sql)) {
      pstm.execute();

      try {
        ResultSet rst = pstm.getResultSet();
        while (rst.next()) {
          Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
          categorias.add(categoria);
        }

      }
      catch (SQLException e) {
        e.printStackTrace();
          }
      }
    return categorias;
    }

    public List<Categoria> listarComProduto() throws SQLException {
      Categoria ultima = null;
      List<Categoria> categorias = new ArrayList<>();

      String sql = "SELECT C.ID, C.nome, P.id, P.nome, P.descricao FROM categoria c inner join" +
              " produto p on c.id = p.categoria_id";
      try (PreparedStatement pstm = conecction.prepareStatement(sql)) {
        pstm.execute();

        try {
          ResultSet rst = pstm.getResultSet();
          while (rst.next()) {
            if (ultima == null || !ultima.getNome().equals(rst.getString(2))) {
              Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
              ultima = categoria;
              categorias.add(categoria);
            }
            Produto produto = new Produto(rst.getInt(3), rst.getString(4), rst.getString(5));
            ultima.adicionar(produto);
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
      return categorias;
    }
  }




