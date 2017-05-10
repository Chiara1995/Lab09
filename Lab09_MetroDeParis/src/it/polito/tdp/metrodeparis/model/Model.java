package it.polito.tdp.metrodeparis.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {
	
	private WeightedMultigraph<Fermata,DefaultWeightedEdge> grafo;
	private List<Fermata> fermate;
	
	public List<Fermata> getFermate(){
		if(fermate==null){
			MetroDAO mdao=new MetroDAO();
			fermate=mdao.getAllFermate();
		}
		return fermate;
	}
	
	public void creaGrafo(){
		grafo=new WeightedMultigraph<Fermata,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		MetroDAO mdao=new MetroDAO();
		//creazione dei vertici 
		Graphs.addAllVertices(grafo, this.getFermate());
		//creazione degli archi
		for(Connessione c : mdao.getListConnessioni()){
			DefaultWeightedEdge e=grafo.addEdge(c.getF1(), c.getF2());
			//peso dell'arco pari al tempo di percorrenza (in ore)
			grafo.setEdgeWeight(e, LatLngTool.distance(c.getF1().getCoords(), c.getF2().getCoords(), LengthUnit.KILOMETER)/(mdao.getVelocita(c.getIdLinea())));
		}
	}
	
	public UndirectedGraph<Fermata,DefaultWeightedEdge> getGrafo(){
		if(grafo==null)
			this.creaGrafo();
		return grafo;
	}
	
	public List<Fermata> getCamminoMinimo(Fermata partenza, Fermata destinazione){
		UndirectedGraph<Fermata,DefaultWeightedEdge> g=this.getGrafo();
		DijkstraShortestPath<Fermata, DefaultWeightedEdge> dsp=new DijkstraShortestPath<Fermata, DefaultWeightedEdge>(g, partenza, destinazione);
		GraphPath<Fermata,DefaultWeightedEdge> percorso=dsp.getPath();
		List<Fermata> listVertex=new ArrayList<Fermata>();
		for(DefaultWeightedEdge dwe : percorso.getEdgeList()){
			if(!listVertex.contains(g.getEdgeSource(dwe)))
				listVertex.add(g.getEdgeSource(dwe));
			if(!listVertex.contains(g.getEdgeTarget(dwe)))
				listVertex.add(g.getEdgeTarget(dwe));
		}
		return listVertex;
	}
	
	public double getTempoPercorrenza(Fermata partenza, Fermata destinazione){
		UndirectedGraph<Fermata,DefaultWeightedEdge> g=this.getGrafo();
		DijkstraShortestPath<Fermata, DefaultWeightedEdge> dsp=new DijkstraShortestPath<Fermata, DefaultWeightedEdge>(g, partenza, destinazione);
		GraphPath<Fermata,DefaultWeightedEdge> percorso=dsp.getPath();
		double tempo=percorso.getWeight()+(30/3600)*(this.getCamminoMinimo(partenza, destinazione).size());
		return tempo;
	}
		
}
