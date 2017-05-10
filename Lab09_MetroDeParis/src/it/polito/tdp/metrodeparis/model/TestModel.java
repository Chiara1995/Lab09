package it.polito.tdp.metrodeparis.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		System.out.println("Elenco fermate");
		List<Fermata> fermate=model.getFermate();
		for(Fermata f : fermate)
			System.out.println(f);
		
		System.out.println();
		
		System.out.println("Grafo creato");
		model.creaGrafo();

		System.out.println();
		
		System.out.println("Cammino minimo da "+fermate.get(14)+" a "+fermate.get(19));
		List<Fermata> percorso=model.getCamminoMinimo(fermate.get(14), fermate.get(19));
		for(Fermata f : percorso)
			System.out.println(f);
		
		System.out.println();
		System.out.println("Tempo percorrenza "+model.getTempoPercorrenza(fermate.get(14), fermate.get(19)));
		
	
	}

}
