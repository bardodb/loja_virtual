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
  }




