package Delegacao.delegacao.main;

import Delegacao.delegacao.classes.Atleta;
import Delegacao.delegacao.classes.Corredor;
import Delegacao.delegacao.classes.Saltador;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Delegacao1 {

	private ArrayList<Atleta> atletas;

	public Delegacao1( ) {
		this.atletas = new ArrayList<Atleta>();
	}

	public void adicionaAtleta(Atleta mani) {
		this.atletas.add(mani);
	}

	public void listarAtletas() {
		for(Atleta mani: atletas) {
			System.out.println(mani.toString());
		}
		System.out.println("Total = " + this.atletas.size() + " atletas listados com sucesso!\n");
	}
	
	public void excluirAtleta(Atleta mani) {
		if (this.atletas.contains(mani)) {
			this.atletas.remove(mani);
			System.out.println("[Atleta " + mani.toString() + "excluido com sucesso!]\n");
		}
		else
			System.out.println("Atleta inexistente!\n");
	}

	public void excluirAtletas() {
		atletas.clear();
		System.out.println("Atletas excluidos com sucesso!\n");
	}
	public void gravarAtletas()  {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\animais.dat"));
			for(Atleta mani: atletas) {
				outputStream.writeObject(mani);
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (outputStream != null ) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void recuperarAtleta() {
		ObjectInputStream inputStream = null;
		try {
			inputStream	= new ObjectInputStream (new FileInputStream ("c:\\temp\\animais.dat"));
			Object obj = null;
			while((obj = inputStream.readObject ()) != null) {
				if (obj instanceof Corredor)
					this.atletas.add((Corredor)obj);
				else if (obj instanceof Saltador)
					this.atletas.add((Saltador)obj);
			}
		}catch (EOFException ex) {     // when EOF is reached
			System.out.println ("End of file reached");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (inputStream != null ) {
					inputStream.close();
					System.out.println("Atletas recuperados com sucesso!\n");
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		Delegacao1 pet  = new Delegacao1();

		Corredor felix    = new Corredor("Felix",    31, 1.80, "SP");
		Corredor garfield = new Corredor("Jose",    35, 1.80, "SC");
		Saltador rex      = new Saltador("Lucas",    23, 1.80, "RJ");
		Saltador toto     = new Saltador("Abel",    18, 1.80, "ES");
		pet.adicionaAtleta(felix);
		pet.adicionaAtleta(garfield);
		pet.adicionaAtleta(rex);
		pet.adicionaAtleta(toto);
		pet.listarAtletas();
		pet.gravarAtletas();
		pet.excluirAtleta(garfield);
		pet.listarAtletas();
		pet.excluirAtletas();
		pet.listarAtletas();
		pet.recuperarAtleta();
		pet.listarAtletas();
	}

}
