package partie.parser.parserCartesCommunaute;

import cartes.communautes.PiocherChance;
import partie.Plateau;
import partie.parser.Parser;

public class ParserPiocherChanceCommunaute extends Parser{
	public ParserPiocherChanceCommunaute(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		PiocherChance Carte = new PiocherChance(position[1],Integer.parseInt(position[2]), false);
		
		Plateau.getPlateau().ajouterCarteCommunaute(Carte); 
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CHANCE;");
	}
}
