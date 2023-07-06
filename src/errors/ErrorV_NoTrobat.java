package errors;

public class ErrorV_NoTrobat extends Exception {
		private static final long serialVersionUID = 1L;

		public ErrorV_NoTrobat() {
	        super("No s'ha trobat la dada<V> que has passat per parametre, revisa que estigui bé ;)");
	    }
}
