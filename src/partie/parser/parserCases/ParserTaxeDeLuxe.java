package partie.parser.parserCases;

import cases.TaxeDeLuxe;
import partie.Plateau;
import partie.parser.Parser;

/**
 * La classe ParserTaxeDeLuxe permet de parser la case de taxe de luxe
 */
public class ParserTaxeDeLuxe extends Parser {

	public ParserTaxeDeLuxe(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
			
		TaxeDeLuxe Case = new TaxeDeLuxe(Integer.parseInt(position[0]), Integer.parseInt(position[2]));
		
		Plateau.getPlateau().ajouterCase(Case); 
		//System.out.println("La " + position[1] + " est a la position " + position[0]);
	}

	@Override
	public boolean saitParser(String ligne) {	
		return ligne.contains("TAXE DE LUXE");
	}
}
