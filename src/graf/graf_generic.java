package graf;

import java.util.ArrayList;
import java.util.HashMap;

import aresta.node_aresta;
import errors.ErrorNoExisteixAresta;
import errors.ErrorV_NoTrobat;
import hash.nodeH;



public class graf_generic<K,V,E> {
	 
	private HashMap <K,nodeH<V,E>> graf;
	
	
	public void crearGraf(HashMap <K,nodeH<V,E>> graf) {
		
		this.graf=graf;
	}
	
	
	
	
	
	public boolean existeixAresta(K v1, K v2){
		boolean te= false;
		boolean fet1 = false;
		boolean fet2 = false;
		if(graf.get(v1) != null || graf.get(v2) != null) {
	
			//inicilaitzem nodes i arestes auxiliars
			nodeH<V,E> n1 = graf.get(v1);
			nodeH<V,E> n2 = graf.get(v2);
			node_aresta<V, E> a1 = null;
			node_aresta<V, E> a2 = null;
					
					
			//mirem si podem anar per la dreta
			if (n1.getAreD() != null) {
			
			a1 = n1.getAreD(); // inicialitzem aresta
			}else {
				fet1= true;
			}
			while (fet1==false && a1 != null && !te) { // recorrem per la dreta 
				if(a1.getPNodeBaix().equals(n2)) {
						te=true;
				}
				a1= a1.getDreta();
			}
					
			//mirem si podem anar per baix
			if (n1.getAreB() != null) {
				a2 = n1.getAreB(); // inicialitzem aresta
			} else {
					
				fet2=true;
			}
					
			while (fet2==false && a2 != null && !te) { // recorrem per baix
				if( a2.getPNodeDreta().equals(n2)) {
					te=true;
						
				}
				a2= a2.getBaix();
			}
		}else {
			return false;
			
		}
		return te;
	}

	public void afegirAresta(K v1, K v2, E e) {
        nodeH<V, E> aux = graf.get(v1);
        nodeH<V, E> aux2 = graf.get(v2);
        node_aresta<V, E> a = new node_aresta<V, E>(aux, aux2, e);
        node_aresta<V, E> arestaAux = null;
        node_aresta<V, E> arestaAux2 = null;

        if(!existeixAresta(v1, v2)) {
            if(aux.getAreD()!=null) {
                arestaAux=aux.getAreD();
                
                while(arestaAux.getDreta()!=null) {
                    arestaAux = arestaAux.getDreta();
                }
                arestaAux.setDreta(a);
                
            }else aux.setAreD(a);

            if(aux2.getAreB()!=null) {
                arestaAux2=aux2.getAreB();
                
                while(arestaAux2.getBaix()!=null) {
                    arestaAux2=arestaAux2.getBaix();
                    
                }
                arestaAux2.setBaix(a);;
            }else aux2.setAreB(a);
        }
    }
	
	
	
	public E valorAresta(K v1, K v2) throws ErrorV_NoTrobat, ErrorNoExisteixAresta{
		boolean nofi1= false;
		boolean nofi2= false;
		boolean te= false;
		E dist= null;
		if(graf.get(v1) != null || graf.get(v2) != null) {
			//inicilaitzem nodes i arestes auxiliars
			nodeH<V,E> n1 = graf.get(v1);
			nodeH<V,E> n2 = graf.get(v2);
			node_aresta<V, E> a1 = null;
			node_aresta<V, E> a2 = null;
			
			if (n1.getAreD() != null) {
				
				a1 = n1.getAreD(); // inicialitzem aresta
			}else {
				nofi1= true;
			}
			while (nofi1==false && a1 != null && !te) { // recorrem per la dreta 
				if(a1.getPNodeBaix().equals(n2)) {
						te=true;
						dist =a1.getDist();
				}
				a1= a1.getDreta();
			}
						
			//mirem si podem anar per baix
			if (n1.getAreB() != null) {
				a2 = n1.getAreB(); // inicialitzem aresta
			} else {
						
				nofi2=true;
			}
						
			while (nofi2==false && a2 != null && !te) { // recorrem per baix
				if( a2.getPNodeDreta().equals(n2)) {
					te=true;
					 dist =a2.getDist();
							
				}
				a2= a2.getBaix();
			}
		}else {
			throw new ErrorV_NoTrobat();
		}
		
		return dist;
		
		
	}
	
	public ArrayList<V> adjacents(K v) throws ErrorV_NoTrobat{
		boolean nofi1= false;
		boolean nofi2= false;
		
		ArrayList<V> aux = new ArrayList<V>();
		if(graf.get(v) != null) {
			//inicilaitzem nodes i arestes auxiliars
			nodeH<V,E> n1 = graf.get(v);
			nodeH<V,E> n2 = graf.get(v);
			node_aresta<V, E> a1 = null;
			
			//mirem si podem anar per la dreta
			if (n1.getAreD() != null) {
				a1 = n1.getAreD(); // inicialitzem aresta
				nofi1= true;
			}
			while (nofi1 && a1 != null ) { // recorrem per la dreta 
				n2 =a1.getPNodeBaix();
				aux.add(n2.getV());
				a1=a1.getDreta();
			}
			
			if (n1.getAreB() != null) {
				a1 = n1.getAreB(); // inicialitzem aresta
				nofi2= true;
			}
			while (nofi2 && a1 != null ) { // recorrem per la dreta 
				n2 =a1.getPNodeDreta();
				aux.add(n2.getV());
				a1=a1.getBaix();
			}
			
		}
		else {
			throw new ErrorV_NoTrobat();
		}
	
		return aux;
	}






	 

}
