package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;

public class DBConnect {

	private static final String jdbcUrl = "jdbc:mysql://localhost/metroparis?user=root";
	private static DataSource ds=null;

	public static Connection getConnection() {
		
		if(ds==null){
			try{
				ds=DataSources.pooledDataSource(DataSources.unpooledDataSource(jdbcUrl));
			} catch(SQLException e){
				e.printStackTrace();
				System.exit(1);
			}
		}
		try {
			
			Connection conn = ds.getConnection();
			return conn;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al database");
		}
	}
}
