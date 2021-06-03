package partie.parser.parserCartesChance;

import cartes.Frais;
import partie.Plateau;
import partie.parser.Parser;

public class ParserFraisChance extends Parser {
	
	public ParserFraisChance(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		Frais Carte = new Frais(position[1], Integer.parseInt(position[2]),Integer.parseInt(position[3]), true);
		
		Plateau.getPlateau().ajouterCarteChance(Carte); 
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("FRAIS");
	}
}
