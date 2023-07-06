package hash;


import java.util.HashMap;
import java.util.Set;

public class hash<K, V,E> {
	HashMap <K,nodeH<V,E>> graf = new HashMap <K,nodeH<V,E>> ();
	
	public void Esborrar(K key) {
		graf.remove(key);
	}
	
	public void replace(K k,nodeH<V,E> v) {
		graf.replace(k, v);
	}
	
	public HashMap <K,nodeH<V,E>> retornaTaula(){
		return graf;
	}

	public void put(K k, nodeH<V,E> v) {
	graf.put(k, v);
	}
	
	public nodeH<V,E> get(K k) {
		return graf.get(k);
	}
	
	public int size() {
		return graf.size();
	}
	
	public boolean existeix() {
		return graf.isEmpty();
	}
	
	public boolean teK(K key) {
		return graf.containsKey(key);
	}
	
	public Set<K> setK(){
		return graf.keySet();
	}
	
        
	//----------------------------------------------------Calcular dist con la formula ---------------------------------------
	public double calcularDist(double l1,double long1, double l2, double long2) {
		l1= Math.toRadians(l1);
		long1= Math.toRadians(long1);
		l2= Math.toRadians(l2);
		long2= Math.toRadians(long2);
				
		final double Radi_Terra=6371.01;
		double dist = Radi_Terra * Math.acos(Math.sin(l1) * Math.sin(l2) + Math.cos(l1) * Math.cos(l2) * Math.cos(long1-long2));
		return dist;
	}	
}
