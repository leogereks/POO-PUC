package Delegacao.delegacao.classes;

public class Nadador extends Atleta {

    private static final long serialVersionUID = 1L;

    public String pratica() {
        return "Faz nados";
    }
    public Nadador(String nome, int idade, double altura, String cidade) {
        super(nome, idade, altura,cidade);
        this.esportista = "Nadador";
    }
}
