package partie.parser.parserCases;

import cases.proprietes.Gare;
import partie.Plateau;
import partie.parser.Parser;

public class ParserGare extends Parser {

	public ParserGare(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
			
		Gare Case = new Gare(Integer.parseInt(position[0]), position[2], Integer.parseInt(position[3]));
		
		Plateau.getPlateau().ajouterCase(Case);
		//System.out.println("La " + position[2] + " est a la position " + position[0]);
	}

	@Override
	public boolean saitParser(String ligne) {	
		return ligne.contains("GARE");
	}
}
