package partie.parser.parserCases;

import cases.CaseDepart;
import partie.Plateau;
import partie.parser.Parser;

/**
 * La classe ParserCaseDepart permet de parser la case de départ
 */
public class ParserCaseDepart extends Parser {

	public ParserCaseDepart(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception {	
		String [] position = ligne.split(";");
		
		CaseDepart Case = new CaseDepart(Integer.parseInt(position[0]), Integer.parseInt(position[2]));
		
		Plateau.getPlateau().ajouterCase(Case); 
		//System.out.println("La case depart est a la position " + position[0]);
	}

	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CASE DEPART");
	}	
}
