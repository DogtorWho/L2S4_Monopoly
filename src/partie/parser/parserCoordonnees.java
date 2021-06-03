package partie.parser;

import partie.Coordonnees;
import partie.Plateau;

public class parserCoordonnees extends Parser {

	public parserCoordonnees(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
		
		Coordonnees Coords = new Coordonnees(Integer.parseInt(position[0]), Integer.parseInt(position[1]), Integer.parseInt(position[2]));
		
		Plateau.getPlateau().ajouterCoordonnees(Coords);
	}

	@Override
	public boolean saitParser(String ligne) {
		return true;
	}

}
