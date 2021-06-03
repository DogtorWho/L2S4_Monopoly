package partie.parser.parserCartesCommunaute;

import cartes.Encaisser;
import partie.Plateau;
import partie.parser.Parser;

public class ParserEncaisserCommunaute extends Parser {
	public ParserEncaisserCommunaute(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		Encaisser Carte = new Encaisser(position[1], Integer.parseInt(position[2]), false);
		
		Plateau.getPlateau().ajouterCarteCommunaute(Carte); 
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("ENCAISSER");
	}
}
