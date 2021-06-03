package partie.exceptions;

import partie.Joueur;

@SuppressWarnings("serial")
public class BankruptException extends PartieException {
	public BankruptException(Joueur joueur, String message) {
		super(message);
		joueur.bankrupt();
	}
}
