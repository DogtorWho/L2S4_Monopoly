package partie.parser.parserCartesCommunaute;

import cartes.Liberation;
import partie.Plateau;
import partie.parser.Parser;

/**
 * La classe ParserLiberationCommunaute permet de parser les cartes communautés qui permettent au joueur de sortir de prison
 */
public class ParserLiberationCommunaute extends Parser {

	public ParserLiberationCommunaute(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		Liberation Carte = new Liberation(position[1], false);
		
		Plateau.getPlateau().ajouterCarteCommunaute(Carte);
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("LIBERATION");
	}
	
}
