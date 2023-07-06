package errors;

public class ErrorExisteixAresta extends Exception {
	private static final long serialVersionUID = 1L;
	public ErrorExisteixAresta() {
        super("Ja existeix una aresta entre els dos nodes que has passat per parametre, no pots tornar a afegir Aresta ");
    }
}
