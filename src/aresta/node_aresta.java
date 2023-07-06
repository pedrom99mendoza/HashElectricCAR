package aresta;

import hash.nodeH;

public class node_aresta<V, E> {
	private node_aresta<V, E> dreta;
    private node_aresta<V, E> baix;
    private nodeH<V,E> PNodeDreta;
    private nodeH<V,E> PNodeBaix;
    private E dist;
    
	public node_aresta(nodeH<V, E> PNodeDreta, nodeH<V, E> PNodeBaix,E dist) {
		super();
		this.PNodeDreta = PNodeDreta;
		this.PNodeBaix = PNodeBaix;
		this.dist = dist;
	}

	public node_aresta<V, E> getDreta() {
		return dreta;
	}

	public void setDreta(node_aresta<V, E> dreta) {
		this.dreta = dreta;
	}

	public node_aresta<V, E> getBaix() {
		return baix;
	}

	public void setBaix(node_aresta<V, E> baix) {
		this.baix = baix;
	}

	public nodeH<V, E> getPNodeDreta() {
		return PNodeDreta;
	}

	public void setPNodeDreta(nodeH<V, E> PNodeDreta) {
		this.PNodeDreta = PNodeDreta;
	}

	public nodeH<V, E> getPNodeBaix() {
		return PNodeBaix;
	}

	public void setPNodeBaix(nodeH<V, E> PNodeBaix) {
		this.PNodeBaix = PNodeBaix;
	}

	public E getDist() {
		return dist;
	}

	public void setDist(E dist) {
		this.dist = dist;
	}

	
	
	
	
}
