package partie.parser.parserCases;

import cases.ImpotSurLeRevenu;
import partie.Plateau;
import partie.parser.Parser;

/**
 * La classe ParserImpotSurLeRevenu permet de parser les cases d'impot sur le revenu
 */
public class ParserImpotSurLeRevenu extends Parser {

	public ParserImpotSurLeRevenu(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		ImpotSurLeRevenu Case = new ImpotSurLeRevenu(Integer.parseInt(position[0]), Integer.parseInt(position[2]));
		
		Plateau.getPlateau().ajouterCase(Case); 		
		//System.out.println("La " + position[1] + " est a la position " + position[0]);
	}

	@Override
	public boolean saitParser(String ligne) {	
		return ligne.contains("IMPOT SUR LE REVENU");
	}
}
