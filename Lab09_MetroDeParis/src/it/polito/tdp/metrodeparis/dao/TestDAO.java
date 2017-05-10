package it.polito.tdp.metrodeparis.dao;

import java.util.List;

import it.polito.tdp.metrodeparis.model.Connessione;
import it.polito.tdp.metrodeparis.model.Fermata;

public class TestDAO {

	public static void main(String[] args) {
		
		MetroDAO metroDAO = new MetroDAO();
		
		System.out.println("Lista fermate");
		List<Fermata> fermate = metroDAO.getAllFermate();
		for(Fermata f : fermate)
			System.out.println(f);
		
		System.out.println();
		System.out.println("Lista connessioni");
		List<Connessione> connessioni=metroDAO.getListConnessioni();
		for(Connessione c : connessioni)
			System.out.println(c);
		
		System.out.println();
		System.out.println("Velocità");
		int velocita=metroDAO.getVelocita(1024);
		System.out.println(velocita);
		
	}

}
