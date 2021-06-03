package partie.parser.parserCases;

import cases.CaisseCommunaute;
import partie.Plateau;
import partie.parser.Parser;

public class ParserCaisseCommunaute extends Parser {

	public ParserCaisseCommunaute(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
			
		CaisseCommunaute Case = new CaisseCommunaute(Integer.parseInt(position[0]));
		
		Plateau.getPlateau().ajouterCase(Case);
		//System.out.println("La " + position[1] + " est a la position " + position[0]);
	}

	@Override
	public boolean saitParser(String ligne) {	
		return ligne.contains("CAISSE COMMUNAUTE");
	}
}
