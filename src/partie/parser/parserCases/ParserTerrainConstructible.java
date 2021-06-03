package partie.parser.parserCases;

import cases.proprietes.TerrainConstructible;
import partie.Plateau;
import partie.parser.Parser;

public class ParserTerrainConstructible extends Parser {

	public ParserTerrainConstructible(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String [] position = ligne.split(";");
			
		TerrainConstructible Case = new TerrainConstructible(Integer.parseInt(position[0]), position[3], Integer.parseInt(position[4]), 
				position[2], Integer.parseInt(position[5]), Integer.parseInt(position[6]), Integer.parseInt(position[7]), Integer.parseInt(position[8]), 
					Integer.parseInt(position[9]), Integer.parseInt(position[10]), Integer.parseInt(position[11]));
		
		Plateau.getPlateau().ajouterCase(Case);
		//System.out.println("La " + position[3] + " est a la position " + position[0]);
	}

	@Override
	public boolean saitParser(String ligne) {	
		return ligne.contains("TERRAIN CONSTRUCTIBLE");
	}
}
