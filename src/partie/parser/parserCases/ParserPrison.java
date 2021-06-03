package partie.parser.parserCases;

import cases.Prison;
import partie.Plateau;
import partie.parser.Parser;

public class ParserPrison extends Parser {

	public ParserPrison(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		Prison Case = new Prison(Integer.parseInt(position[0]));
		
		Plateau.getPlateau().ajouterCase(Case);	
		//System.out.println("La " + position[1] + " est a la position " + position[0]);
	}

	@Override
	public boolean saitParser(String ligne) {	
		return ligne.contains(";PRISON");
	}
}
