package it.polito.tdp.metrodeparis.model;

public class Connessione {
	
	private int idLinea;
	private Fermata f1;
	private Fermata f2;
	
	public Connessione(int idLinea,Fermata f1, Fermata f2) {
		super();
		this.idLinea=idLinea;
		this.f1 = f1;
		this.f2 = f2;
	}
	
	public int getIdLinea(){
		return idLinea;
	}
	
	public void setIdLinea(int idLinea){
		this.idLinea=idLinea;
	}

	public Fermata getF1() {
		return f1;
	}

	public void setF1(Fermata f1) {
		this.f1 = f1;
	}

	public Fermata getF2() {
		return f2;
	}

	public void setF2(Fermata f2) {
		this.f2 = f2;
	}

	@Override
	public String toString() {
		return idLinea+" "+f1+" "+f2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((f1 == null) ? 0 : f1.hashCode());
		result = prime * result + ((f2 == null) ? 0 : f2.hashCode());
		result = prime * result + idLinea;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connessione other = (Connessione) obj;
		if (f1 == null) {
			if (other.f1 != null)
				return false;
		} else if (!f1.equals(other.f1))
			return false;
		if (f2 == null) {
			if (other.f2 != null)
				return false;
		} else if (!f2.equals(other.f2))
			return false;
		if (idLinea != other.idLinea)
			return false;
		return true;
	}

}
