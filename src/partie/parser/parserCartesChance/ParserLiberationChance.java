package partie.parser.parserCartesChance;

import cartes.Liberation;
import partie.Plateau;
import partie.parser.Parser;

public class ParserLiberationChance extends Parser {

	public ParserLiberationChance(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		Liberation Carte = new Liberation(position[1], true);
		
		Plateau.getPlateau().ajouterCarteChance(Carte); 
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("LIBERATION");
	}
	
}
