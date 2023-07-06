package objecte;

import java.util.ArrayList;

public class estacio<V> {
	private String id_estacio;
	private double latitud=0;
	private double longitud=0;
	private ArrayList<V> llista_endolls;
	
	public estacio(String id_estacio, double latitud, double longitud) {
		super();
		this.id_estacio = id_estacio;
		this.latitud = latitud;
		this.longitud = longitud;
		llista_endolls = new ArrayList<V>();
	}
	
	public String getId_estacio() {
		return id_estacio;
	}
	public void setId_estacio(String id_estacio) {
		this.id_estacio = id_estacio;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public ArrayList<V> getLlista_endolls() {
		return llista_endolls;
	}
	public void setALlista_endolls(V pot) {
		llista_endolls.add(pot);
	}
	@Override
	public String toString() {
		return "estacio [id_estacio=" + id_estacio + ", latitud=" + latitud + ", longitud=" + longitud
				+ ", llista_endolls=" + llista_endolls + "]";
	}
	
	
	
	
}
