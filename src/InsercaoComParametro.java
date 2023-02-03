import java.sql.*;

public class InsercaoComParametro {

  public static void main(String[] args) throws SQLException {

    ConnectionFactory factory = new ConnectionFactory();

    try( Connection connection = factory.recuperarConexao()){

    connection.setAutoCommit(false);

    try (  PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES(? , ?)",
      Statement.RETURN_GENERATED_KEYS);) {

      AdicionarVariavel("SmartTV", "45 polegadas", stm);
      AdicionarVariavel("Radio", "Radio de Bateria", stm);

      connection.commit();//se tudo der certo, vai fazer a trasnferencia pro bd
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("rollback executado");
      connection.rollback();
    }
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


    try (
      ResultSet rst = stm.getGeneratedKeys()) {


      while (rst.next()) {
        int id = rst.getInt(1);
        System.out.println("O id criado foi: " + id);
      }

    }
  }
}
