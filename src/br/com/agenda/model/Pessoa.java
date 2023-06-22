package br.com.agenda.model;

import java.util.Date;

public class Pessoa {

	private int id;
	private String nome;
	private Date dataNascimento;
	private String rg;
	private String cpf;
	private Date dataCadastro;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getRG() {
    return rg;
	} 
	public void setRG(String rg){
	this.rg = rg;
	}
	public String getCPF() {
    return cpf;
	} 
	public void setCPF(String cpf){
    this.cpf = cpf;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}