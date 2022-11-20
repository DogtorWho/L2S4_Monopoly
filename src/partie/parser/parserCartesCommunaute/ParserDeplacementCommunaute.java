package partie.parser.parserCartesCommunaute;

import cartes.Deplacement;
import partie.Plateau;
import partie.parser.Parser;

/**
 * La classe ParserDeplacementCommunaute permet de parser les cartes communautés qui deplacent le joueur
 */
public class ParserDeplacementCommunaute extends Parser {

	public ParserDeplacementCommunaute(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		Deplacement Carte = new Deplacement(position[1], position[2], Integer.parseInt(position[3]), false);
		
		Plateau.getPlateau().ajouterCarteCommunaute(Carte); 
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("DEPLACEMENT");
	}
}
