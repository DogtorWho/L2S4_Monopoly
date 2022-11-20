package partie.parser.parserCartesCommunaute;

import cartes.communautes.Anniversaire;
import partie.Plateau;
import partie.parser.Parser;

/**
 * La classe ParserAnniversaireCommunaute permet de parser les cartes communautés qui donne de l'argent au joueur pour son anniversaire
 */
public class ParserAnniversaireCommunaute extends Parser {

	public ParserAnniversaireCommunaute(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		Anniversaire Carte = new Anniversaire(position[1],Integer.parseInt(position[2]), false);
		
		Plateau.getPlateau().ajouterCarteCommunaute(Carte);
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("ANNIVERSAIRE");
	}
}
