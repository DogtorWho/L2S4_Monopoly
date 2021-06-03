package partie.parser.parserCases;

import cases.AllerEnPrison;
import partie.Plateau;
import partie.parser.Parser;

public class ParserAllerEnPrison extends Parser {

	public ParserAllerEnPrison(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
				
		AllerEnPrison Case = new AllerEnPrison(Integer.parseInt(position[0]));
		
		Plateau.getPlateau().ajouterCase(Case); 
		//System.out.println("La " + position[1] + " est a la position " + position[0]);
	}

	@Override
	public boolean saitParser(String ligne) {	
		return ligne.contains("ALLEZ EN PRISON");
	}
}
