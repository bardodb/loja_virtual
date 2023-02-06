import java.sql.*;

public class TestaListagem {
  public static void main(String[] args) throws SQLException {

    ConnectionFactory factory = new ConnectionFactory();
    Connection connection = factory.recuperarConexao();

    PreparedStatement stm = connection.prepareStatement
      ("SELECT ID, NOME, DESCRICAO FROM PRODUTO"); //criando o Statement para executar a query
    stm.execute();


    ResultSet rst = stm.getResultSet(); //retorna o resultado da query
    while (rst.next()) {//enquanto houver registros no ResultSet,  o while vai executar o código abaixo
      Integer id = rst.getInt("ID");
      System.out.println(id);
      String nome = rst.getString("NOME");
      System.out.println(nome);
      String descricao = rst.getString("DESCRICAO");
      System.out.println(descricao);


    } //pegando o resultado da query e armazenando em um ResultSet
    connection.close();
  }
}
