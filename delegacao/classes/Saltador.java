package Delegacao.delegacao.classes;

public class Saltador extends Atleta {

	private static final long serialVersionUID = 1L;

	public String pratica() {
		return "Faz saltos";
	}
	public Saltador(String nome, int idade, double altura, String cidade) {
		super(nome, idade, altura,cidade);
		this.esportista = "Saltador";
	}
}
