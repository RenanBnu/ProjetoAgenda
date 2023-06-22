package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import br.com.agenda.factory.ConnectorFactory;
import br.com.agenda.model.Endereco;
import br.com.agenda.model.Pessoa;

public class EnderecoDAO {

  /*
   * CRUD c: CREATE - OK - INSERT r: READ - SELECT u: UPDATE - UPDATE d: DELETE -
   * DELETE
   */

  public static void save(Endereco endereco) {

    String sql = "INSERT INTO endereco(pessoa_id, rua, numero, complemento, cidade, estado, cep, data_cadastro) VALUES (?, ?, ?, ?, ?, ?, ?, sysdate())";

    Connection conn = null;
    PreparedStatement pstm = null;

    try {
      // Criar uma conexão com o banco de dados
      conn = ConnectorFactory.createConnectionToMySQL();

      // Criamos uma PreparedStatement, para executar uma query
      pstm = (PreparedStatement) conn.prepareStatement(sql);
      // Adicionar os valores que são esperados pela query
      pstm.setInt(1, endereco.getPessoa().getId());
      pstm.setString(2, endereco.getRua());
      pstm.setString(3, endereco.getNumero());
      pstm.setString(4, endereco.getComplemento());
      pstm.setString(5, endereco.getCidade());
      pstm.setString(6, endereco.getEstado());
      pstm.setString(7, endereco.getCep());

      // Executar a query
      pstm.execute();

      System.out.println("Endereco salvo com sucesso!");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      // Fechar as conexões
      try {
        if (pstm != null) {
          pstm.close();
        }

        if (conn != null) {
          conn.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public List<Endereco> getEndereco() {
	
    String sql = "SELECT * FROM endereco";
    
    List<Endereco> enderecos = new ArrayList<Endereco>();
    
    Connection conn = null;
    PreparedStatement pstm = null;
    //Classe que vai recuperar os dados do banco. ***SELECT****
    ResultSet rset = null;
    
    try {
      conn = ConnectorFactory.createConnectionToMySQL();
      
      pstm = (PreparedStatement) conn.prepareStatement(sql);
      
      rset = pstm.executeQuery();
      
      while (rset.next()) {
        
        Endereco endereco = new Endereco();

        Pessoa pessoa = new Pessoa();
        pessoa.setId(rset.getInt("pessoa_id"));
        
        //Recuperar o id
        endereco.setId(rset.getInt("id_endereco"));
        //Recuperar a pessoa
        endereco.setPessoa(pessoa);
        //Recuperar a rua
        endereco.setRua(rset.getString("rua"));
        //Recuperar o numero 
        endereco.setNumero(rset.getString("numero"));
        //Recuperar o complemento
        endereco.setComplemento(rset.getString("complemento"));
        //Recuperar a cidade
        endereco.setComplemento(rset.getString("cidade"));
        //Recuperar o estado
        endereco.setComplemento(rset.getString("estado"));
        //Recuperar o CEP
        endereco.setCep(rset.getString("CEP"));
        // Recuperar a data de cadastrado
        pessoa.setDataCadastro(rset.getDate("data_cadastro"));
        
        enderecos.add(endereco);
        
      }
    }catch (Exception e) {
        e.printStackTrace();
      }finally {
        try {
          if(rset!=null) {
            rset.close();
          }
          
          if(pstm!=null) {
            pstm.close();
          }
          
          if(conn!=null) {
            conn.close();
          }
        }catch(Exception e) {
          e.printStackTrace();
        }
      }
    
      return enderecos;
  }

  public void update(Endereco endereco) {

    String sql = "UPDATE endereco SET rua = ?, numero = ?, complemento = ?, cidade = ?, estado = ?, cep = ?, data_cadastro = sysdate()" +
        "WHERE id_endereco = ?";

    Connection conn = null;
    PreparedStatement pstm = null;

    try {
      // Cria a conexão com o banco
      conn = ConnectorFactory.createConnectionToMySQL();

      // Cria a classe para executar a query
      pstm = (PreparedStatement) conn.prepareStatement(sql);

      // Adicionar valores para atualizar
      pstm.setString(1, endereco.getRua());
      pstm.setString(2, endereco.getNumero());
      pstm.setString(3, endereco.getComplemento());
      pstm.setString(4, endereco.getCidade());
      pstm.setString(5, endereco.getEstado());
      pstm.setString(6, endereco.getCep());

      // Qual ID do registro vai atualizar
      pstm.setInt(7, endereco.getId());

      pstm.execute();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (pstm != null) {
          pstm.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void deleteByID(int id) {

    String sql = "DELETE FROM endereco WHERE id_endereco = ?";

    Connection conn = null;
    PreparedStatement pstm = null;

    try {
      conn = ConnectorFactory.createConnectionToMySQL();

      pstm = (PreparedStatement) conn.prepareStatement(sql);

      pstm.setInt(1, id);

      pstm.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (pstm != null) {
          pstm.close();
        }

        if (conn != null) {
          conn.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
