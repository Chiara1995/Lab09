package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.metrodeparis.model.Connessione;
import it.polito.tdp.metrodeparis.model.Fermata;

public class MetroDAO {

	public List<Fermata> getAllFermate() {

		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC";
		List<Fermata> fermate = new ArrayList<Fermata>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata f = new Fermata(rs.getInt("id_Fermata"), rs.getString("nome"), new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")));
				fermate.add(f);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return fermate;
	}
	
	public List<Connessione> getListConnessioni() {

		final String sql = "SELECT connessione.id_linea,f1.id_fermata AS id1, f1.nome AS nome1, f1.coordX AS x1, f1.coordY AS y1, f2.id_fermata AS id2, f2.nome AS nome2, f2.coordX AS x2, f2.coordY AS y2 "+
						   "FROM fermata AS f1, fermata AS f2, connessione "+
						   "WHERE f1.id_fermata=connessione.id_stazP AND f2.id_fermata=connessione.id_stazA ";
		List<Connessione> connessioni = new ArrayList<Connessione>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata f1 = new Fermata(rs.getInt("id1"), rs.getString("nome1"), new LatLng(rs.getDouble("x1"), rs.getDouble("y1")));
				Fermata f2 = new Fermata(rs.getInt("id2"), rs.getString("nome2"), new LatLng(rs.getDouble("x2"), rs.getDouble("y2")));
				Connessione c=new Connessione(rs.getInt("id_linea"),f1,f2);
				//if(!connessioni.contains(new Connessione(rs.getInt("id_linea"),f2,f1)))
					connessioni.add(c);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return connessioni;
	}
	
	public int getVelocita(int idLinea) {

		final String sql = "SELECT velocita "+
						   "FROM linea "+
				           "WHERE id_linea=? ";
		int velocita=0;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, idLinea);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				velocita=rs.getInt("velocita");
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return velocita;
	}
	
}
