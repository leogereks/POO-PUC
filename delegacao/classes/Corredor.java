package Delegacao.delegacao.classes;

public class Corredor extends Atleta {

	private static final long serialVersionUID = 1L;

	public String pratica() {
		return "corrida";
	}
	public Corredor(String nome, int idade, double altura, String cidade) {
		super(nome, idade, altura,cidade);
		this.esportista = "Corredor";
	}
}
