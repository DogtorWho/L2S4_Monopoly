package partie.parser.parserCartesChance;

import cartes.Encaisser;
import partie.Plateau;
import partie.parser.Parser;

/**
 * La classe ParserEncaisserChance permet de parser les cartes chances qui donne de l'argent au joueur
 */
public class ParserEncaisserChance extends Parser {
	
	public ParserEncaisserChance(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		Encaisser Carte = new Encaisser(position[1], Integer.parseInt(position[2]), true);
		
		Plateau.getPlateau().ajouterCarteChance(Carte); 
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("ENCAISSER");
	}
}
