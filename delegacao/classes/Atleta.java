package Delegacao.delegacao.classes;

import java.io.Serializable;

public abstract class Atleta implements Serializable {

	private static final long serialVersionUID = 1L;
	private   String nome;
	private   int idade;
	private   double altura;
	private  String cidade;
	protected String esportista;
	
	public Atleta(String nome, int idade, double altura, String cidade) {
		this.nome = nome;
		this.idade = idade;
		this.altura = altura;
		this.cidade = cidade;
	}
	public String toString() {
		String retorno = "";
		retorno += "Nome: "     + this.nome     + "\n";
		retorno += "Idade: "    + this.idade    + " anos\n";
		retorno += "Altura: "     + this.altura + "\n";
		retorno += "Cidade: "     + this.cidade + "\n";
		retorno += "Esportista: "  + this.esportista + "\n";
		retorno += "Pratica: "  + pratica()        + "\n";
		return retorno;
	}
	public abstract String pratica();
}
