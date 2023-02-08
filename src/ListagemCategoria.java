import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListagemCategoria {
    public static void main(String[] args) throws SQLException {

      try(Connection connection = new ConnectionFactory().recuperarConexao()) {
        CategoriaDAO categoriaDAO = new CategoriaDAO(connection);

        List<Categoria> listaCategoria = categoriaDAO.listar();
        listaCategoria.stream().forEach(ct -> {
          System.out.println(ct.getNome());
            try {
              for (Produto produto : new ProdutoDAO(connection).buscar(ct)) {
                System.out.println(ct.getNome() + " - " + produto.getNome());

              }
            } catch (SQLException e)
            {
              e.printStackTrace();
           }
         }
       );
     }
   }
}
