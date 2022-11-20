package partie.parser.parserCases;

import cases.SimpleVisite;
import partie.Plateau;
import partie.parser.Parser;

/**
 * La classe ParserSimpleVisite permet de parser la case de visiteur dans la prison
 */
public class ParserSimpleVisite extends Parser {

	public ParserSimpleVisite(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		SimpleVisite Case = new SimpleVisite(Integer.parseInt(position[0]));
		
		Plateau.getPlateau().ajouterCase(Case);	
		//System.out.println("La " + position[1] + " est a la position " + position[0]);
	}

	@Override
	public boolean saitParser(String ligne) {	
		return ligne.contains("SIMPLE VISITE");
	}
}
