package partie.parser.parserCartesCommunaute;

import cartes.Payer;
import partie.Plateau;
import partie.parser.Parser;

/**
 * La classe ParserPayerCommunaute permet de parser les cartes communautés qui retire de l'argent au joueur
 */
public class ParserPayerCommunaute extends Parser {
	
	public ParserPayerCommunaute(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		Payer Carte = new Payer(position[1], Integer.parseInt(position[2]), false);
		
		Plateau.getPlateau().ajouterCarteCommunaute(Carte);
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("PAYER");
	}
}
