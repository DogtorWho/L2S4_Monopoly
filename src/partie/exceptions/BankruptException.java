package partie.exceptions;

import partie.Joueur;

/**
 * Exception lancée quand un joueur n'a plus d'argent
 */
@SuppressWarnings("serial")
public class BankruptException extends PartieException {
	public BankruptException(Joueur joueur, String message) {
		super(message);
		joueur.bankrupt();
	}
}
