import java.sql.*;

public class InsercaoComParametro {

  public static void main(String[] args) throws SQLException {

    ConnectionFactory factory = new ConnectionFactory();
    Connection conexao = factory.recuperarConexao();


    conexao.setAutoCommit(false);

    try {

      PreparedStatement stm = conexao.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES(? , ?)",
        Statement.RETURN_GENERATED_KEYS);

      AdicionarVariavel("SmartTV", "45 polegadas", stm);
      AdicionarVariavel("Radio", "Radio de Bateria", stm);

      conexao.commit();//se tudo der certo, vai fazer a trasnferencia pro bd
    } catch (Exception e){
e.printStackTrace();
      System.out.println("rollback executado");
      conexao.rollback();
    }

  }


  private static void AdicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
    stm.setString(1, nome);
    stm.setString(2, descricao);


/*
    if (nome.equals("Radio")){
      throw new RuntimeException(("não foi possível completar a transação"));
    }

 */
    stm.execute();


    ResultSet rst = stm.getGeneratedKeys();

    while (rst.next()) {
      int id = rst.getInt(1);
      System.out.println("O id criado foi: " + id);
    }
  }
}
