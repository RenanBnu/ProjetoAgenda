package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import br.com.agenda.factory.ConnectorFactory;
import br.com.agenda.model.Pessoa;

public class PessoaDAO {

  /*
   * CRUD c: CREATE - OK - INSERT r: READ - SELECT u: UPDATE - UPDATE d: DELETE -
   * DELETE
   */

  public void save(Pessoa pessoa) {

    String sql = "INSERT INTO pessoa(nome, data_nasc, cpf, rg, data_cadastro) VALUES (?, ?, ?, ?, sysdate())";

    Connection conn = null;
    PreparedStatement pstm = null;

    try {
      // Criar uma conexão com o banco de dados
      conn = ConnectorFactory.createConnectionToMySQL();

      // Criamos uma PreparedStatement, para executar uma query
      pstm = (PreparedStatement) conn.prepareStatement(sql);
      // Adicionar os valores que são esperados pela query
      pstm.setString(1, pessoa.getNome());
      pstm.setDate(2, new Date(pessoa.getDataNascimento().getTime()));
      pstm.setString(3, pessoa.getCPF());
      pstm.setString(4, pessoa.getRG());

      // Executar a query
      pstm.execute();

      System.out.println("Pessoa salva com sucesso!");
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

  public List<Pessoa> getPessoas() {

    String sql = "SELECT * FROM pessoa";

    List<Pessoa> pessoas = new ArrayList<Pessoa>();

    Connection conn = null;
    PreparedStatement pstm = null;
    // Classe que vai recuperar os dados do banco. ***SELECT****
    ResultSet rset = null;

    try {
      conn = ConnectorFactory.createConnectionToMySQL();

      pstm = (PreparedStatement) conn.prepareStatement(sql);

      rset = pstm.executeQuery();

      while (rset.next()) {

        Pessoa pessoa = new Pessoa();

        // Recuperar o id
        pessoa.setId(rset.getInt("id_pessoa"));
        // Recuperar o nome
        pessoa.setNome(rset.getString("nome"));
        // Recuperar a data de nascimento
        pessoa.setDataNascimento(rset.getDate("data_nasc"));
        // Recuperar o CPF
        pessoa.setCPF(rset.getString("cpf"));
        // Recuperar o RG
        pessoa.setRG(rset.getString("rg"));
        // Recuperar a data de cadastrado
        pessoa.setDataCadastro(rset.getDate("data_cadastro"));

        pessoas.add(pessoa);

      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (rset != null) {
          rset.close();
        }

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

    return pessoas;
  }

  public void update(Pessoa pessoa) {

    String sql = "UPDATE pessoa SET nome = ?, data_nasc = ?, cpf = ?, rg = ?, data_cadastro = sysdate()" +
        "WHERE id_pessoa = ?";

    Connection conn = null;
    PreparedStatement pstm = null;

    try {
      // Cria a conexão com o banco
      conn = ConnectorFactory.createConnectionToMySQL();

      // Cria a classe para executar a query
      pstm = (PreparedStatement) conn.prepareStatement(sql);

      // Adicionar valores para atualizar
      pstm.setString(1, pessoa.getNome());
      pstm.setDate(2, new Date(pessoa.getDataNascimento().getTime()));
      pstm.setString(3, pessoa.getCPF());
      pstm.setString(4, pessoa.getRG());

      // Qual ID do registro vai atualizar
      pstm.setInt(5, pessoa.getId());

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

    String sql = "DELETE FROM pessoa WHERE id_pessoa = ?";

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

  public Pessoa getPessoaById(int idPessoa) {

    String sql = "SELECT * FROM pessoa WHERE id_pessoa = ?";

    Connection conn = null;
    PreparedStatement pstm = null;
    // Classe que vai recuperar os dados do banco. ***SELECT****
    ResultSet rset = null;
    
    Pessoa pessoa = new Pessoa();

    try {
      conn = ConnectorFactory.createConnectionToMySQL();

      pstm = (PreparedStatement) conn.prepareStatement(sql);

      pstm.setInt(1, idPessoa);

      rset = pstm.executeQuery();

      while (rset.next()) {
      
	      // Recuperar o id
	      pessoa.setId(rset.getInt("id_pessoa"));
	      // Recuperar o nome
	      pessoa.setNome(rset.getString("nome"));
	      // Recuperar a data de nascimento
	      pessoa.setDataNascimento(rset.getDate("data_nasc"));
	      // Recuperar o CPF
	      pessoa.setCPF(rset.getString("cpf"));
	      // Recuperar o RG
	      pessoa.setRG(rset.getString("rg"));
	      // Recuperar a data de cadastrado
	      pessoa.setDataCadastro(rset.getDate("data_cadastro"));
      }
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
    return pessoa;
  }

    public Pessoa getIdByName(String nomePessoa) {

    String sql = "SELECT id_pessoa FROM pessoa WHERE nome like ?";

    Connection conn = null;
    PreparedStatement pstm = null;
    // Classe que vai recuperar os dados do banco. ***SELECT****
    ResultSet rset = null;
    
    Pessoa pessoa = new Pessoa();

    try {
      conn = ConnectorFactory.createConnectionToMySQL();

      pstm = (PreparedStatement) conn.prepareStatement(sql);

      pstm.setString(1, nomePessoa);

      rset = pstm.executeQuery();

      // Recuperar o id
      pessoa.setId(rset.getInt("id_pessoa"));

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
    return pessoa;
  }

}