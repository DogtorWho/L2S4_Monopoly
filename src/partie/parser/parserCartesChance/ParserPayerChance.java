package partie.parser.parserCartesChance;

import cartes.Payer;
import partie.Plateau;
import partie.parser.Parser;

/**
 * La classe ParserPayerChance permet de parser les cartes chances qui retire de l'argent au joueur
 */
public class ParserPayerChance extends Parser {
	
	public ParserPayerChance(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		Payer Carte = new Payer(position[1], Integer.parseInt(position[2]), true);
		
		Plateau.getPlateau().ajouterCarteChance(Carte); 
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("PAYER");
	}
}
