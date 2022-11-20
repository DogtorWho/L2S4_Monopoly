package partie.parser.parserCartesChance;

import cartes.Deplacement;
import partie.Plateau;
import partie.parser.Parser;

/**
 * La classe ParserDeplacementChance permet de parser les cartes chances qui deplacent le joueur
 */
public class ParserDeplacementChance extends Parser {

	public ParserDeplacementChance(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		Deplacement Carte = new Deplacement(position[1], position[2], Integer.parseInt(position[3]), true);
		
		Plateau.getPlateau().ajouterCarteChance(Carte); 
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("DEPLACEMENT");
	}
}
