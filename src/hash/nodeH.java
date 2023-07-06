package hash;

import aresta.node_aresta;

public class nodeH<V,E> {
	 private node_aresta<V,E> areD;
	 private node_aresta<V,E> areB;
	 private V v;
	 
	 
	public nodeH(V v) {
		this.v = v;
	}

	public node_aresta<V, E> getAreD() {
		return areD;
	}

	public void setAreD(node_aresta<V, E> areD) {
		this.areD = areD;
	}

	public node_aresta<V, E> getAreB() {
		return areB;
	}

	public void setAreB(node_aresta<V, E> areB) {
		this.areB = areB;
	}

	public V getV() {
		return v;
	}

	public void setV(V v) {
		this.v = v;
	}

	

		 	
}
