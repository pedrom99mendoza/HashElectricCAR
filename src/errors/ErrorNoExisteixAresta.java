package errors;

public class ErrorNoExisteixAresta extends Exception {
	private static final long serialVersionUID = 1L;
	public ErrorNoExisteixAresta() {
        super("No existeix una aresta entre els dos nodes que has passat per parametre, per això no es pot trobar el valor d'aquest ");
    }
}
