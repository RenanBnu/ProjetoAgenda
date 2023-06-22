package br.com.agenda.model;

import java.util.Date;

public class Endereco {
	
  private int id;
  private Pessoa pessoa;
  private String rua;
  private String numero;
  private String complemento;
  private String cidade;
  private String estado;
  private String cep;
  private Date dataCadastro;

  public int getId() {
		return id;
	}
  public void setId(int id) {
		this.id = id;
	}
  public Pessoa getPessoa() {
    return pessoa;
  }
  public void setPessoa(Pessoa pessoa) {
    this.pessoa = pessoa;
  } 
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
  public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
  public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}	
  public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}	
	public String getCep() {
		return cep;
	}
	public void setCep(String string) {
		this.cep = string;
	}	
  public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
