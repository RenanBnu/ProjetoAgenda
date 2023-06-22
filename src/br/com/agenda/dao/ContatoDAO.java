package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import br.com.agenda.factory.ConnectorFactory;
import br.com.agenda.model.Contato;
import br.com.agenda.model.Pessoa;

public class ContatoDAO {

  public void save(Contato contato) {

    String sql = "INSERT INTO contato (celular_1, celular_2, telefone, email,"
        + " linkedin, instagram, facebook, pessoa_id, data_cadastro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, sysdate())";

    Connection conn = null;
    PreparedStatement pstm = null;

    try {

      // Cria a conexão com o banco
      conn = ConnectorFactory.createConnectionToMySQL();

      // Cria a classe para executar a query

      pstm = (PreparedStatement) conn.prepareStatement(sql);

      // Adicionar valores para atualizar
      pstm.setString(1, contato.getCelular1());
      pstm.setString(2, contato.getCelular2());
      pstm.setString(3, contato.getTelefone());
      pstm.setString(4, contato.getEmail());
      pstm.setString(5, contato.getLinkedin());
      pstm.setString(6, contato.getInstagram());
      pstm.setString(7, contato.getFacebook());
      pstm.setInt(8, contato.getPessoa().getId());

      pstm.execute();

      System.out.println("Contato salvo com sucesso!");
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

  public void update(Contato contato) {

    String sql = "UPDATE contato SET celular_1 = ?, celular_2 = ?, telefone = ?, email = ?, linkedin = ?, instagram = ?, facebook = ?, data_cadastro = sysdate()"
        +
        "WHERE id_contato = ?";

    Connection conn = null;
    PreparedStatement pstm = null;

    try {
      // Cria a conexão com o banco
      conn = ConnectorFactory.createConnectionToMySQL();

      // Cria a classe para executar a query
      pstm = (PreparedStatement) conn.prepareStatement(sql);

      // Adicionar valores para atualizar
      pstm.setString(1, contato.getCelular1());
      pstm.setString(2, contato.getCelular2());
      pstm.setString(3, contato.getTelefone());
      pstm.setString(4, contato.getEmail());
      pstm.setString(5, contato.getLinkedin());
      pstm.setString(6, contato.getInstagram());
      pstm.setString(7, contato.getFacebook());

      // Qual ID do registro vai atualizar
      pstm.setInt(8, contato.getId());

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

    String sql = "DELETE FROM contato WHERE id_contato = ?";

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

  public List<Contato> getContato() {

    String sql = "SELECT * FROM contato";

    List<Contato> contatos = new ArrayList<Contato>();

    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rset = null;

    try {
      conn = ConnectorFactory.createConnectionToMySQL();

      pstm = (PreparedStatement) conn.prepareStatement(sql);

      rset = pstm.executeQuery();

      while (rset.next()) {

        Contato contato = new Contato();

        Pessoa pessoa = new Pessoa();
        pessoa.setId(rset.getInt("pessoa_id"));

        contato.setId(rset.getInt("id_contato"));
        contato.setCelular1(rset.getString("celular_1"));
        contato.setCelular2(rset.getString("celular_2"));
        contato.setTelefone(rset.getString("telefone"));
        contato.setEmail(rset.getString("email"));
        contato.setLinkedin(rset.getString("linkedin"));
        contato.setInstagram(rset.getString("instagram"));
        contato.setFacebook(rset.getString("facebook"));
        contato.setPessoa(pessoa);
        contato.setDataCadastro(rset.getDate("data_cadastro"));

        contatos.add(contato);

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

    return contatos;
  }
}
