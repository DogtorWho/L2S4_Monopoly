package partie.parser.parserCases;

import cases.proprietes.Compagnie;
import partie.Plateau;
import partie.parser.Parser;

/**
 * La classe ParserCompagnie permet de parser les cases de compagnies
 */
public class ParserCompagnie extends Parser {

	public ParserCompagnie(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
			
		Compagnie Case = new Compagnie(Integer.parseInt(position[0]), position[2], Integer.parseInt(position[3]));
		
		Plateau.getPlateau().ajouterCase(Case);
		//System.out.println("La " + position[2] + " est a la position " + position[0]);
	}

	@Override
	public boolean saitParser(String ligne) {	
		return ligne.contains("COMPAGNIE");
	}
	// Ne pas melanger compagnie des eaux et electrique
}
