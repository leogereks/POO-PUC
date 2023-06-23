package Delegacao.delegacao.main;

import Delegacao.delegacao.classes.Atleta;
import Delegacao.delegacao.classes.Corredor;
import Delegacao.delegacao.classes.Nadador;
import Delegacao.delegacao.classes.Saltador;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Delegaccao {
	private ArrayList<Atleta> atletas;

	public Delegaccao() {
		this.atletas = new ArrayList<Atleta>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Corredor leCorredor(){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Idade", "Altura", "Cidade"};
		valores = leValores (nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Corredor corredor = new Corredor(valores[0], idade, Double.parseDouble(valores[2]), valores[3]);
		return corredor;
	}

	public Saltador leSaltador(){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Idade", "Altura", "Cidade"};
		valores = leValores (nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Saltador saltador = new Saltador(valores[0], idade, Double.parseDouble(valores[2]), valores[3]);
		return saltador;
	}

	public Nadador leNadador(){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Idade", "Altura", "Cidade"};
		valores = leValores (nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Nadador nadador = new Nadador(valores[0], idade, Double.parseDouble(valores[2]), valores[3]);
		return nadador;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // M�todo est�tico, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // N�o conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}
	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		int numInt;

		//Enquanto n�o for poss�vel converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um n�mero inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaMamiferos (ArrayList<Atleta> atletas){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\petStore.dados"));
			for (int i = 0; i < atletas.size(); i++)
				outputStream.writeObject(atletas.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Imposs�vel criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Atleta> recuperaMamiferos (){
		ArrayList<Atleta> atletasTemp = new ArrayList<Atleta>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\petStore.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Atleta) {
					atletasTemp.add((Atleta) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com atletas N�O existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return atletasTemp;
		}
	}

	public void menuPetStore (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Menu Delegacão \n" +
					"Opções:\n" +
					"1. Entrar Atletas\n" +
					"2. Exibir Atletas\n" +
					"3. Limpar Atletas\n" +
					"4. Gravar Atletas\n" +
					"5. Recuperar Atletas\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de Atletas\n" +
						"Opções:\n" +
						"1. Saltador\n" +
						"2. Corredor\n"+
						"3. Nadador";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: atletas.add((Atleta) leSaltador());
				break;
				case 2: atletas.add((Atleta) leCorredor());
				break;
				case 3: atletas.add((Atleta) leNadador());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Atleta para entrada não escolhido!");
				}

				break;
			case 2: // Exibir dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com atleta primeiramente");
					break;
				}
				String dados = "";
				for (int i = 0; i < atletas.size(); i++)	{
					dados += atletas.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com atleta primeiramente");
					break;
				}
				atletas.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com atleta primeiramente");
					break;
				}
				salvaMamiferos(atletas);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				atletas = recuperaMamiferos();
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Encerrando o aplicativo...");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		Delegaccao pet = new Delegaccao();
		pet.menuPetStore();

	}

}
