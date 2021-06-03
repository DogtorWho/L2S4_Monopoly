package partie.fichiers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import partie.parser.Parser;

/**
 * La classe Fichier correspond au demandeur. Elle contient qu'une seule méthode, statique, appelée 'lire'. 
 * Tout se passe dans cette méthode
 */
public class Fichier {
	

	/**
	 * Cette méthode ouvre le fichier (en faisant tous les contrôles nécessaires). Puis, elle boucle sur
	 * chaque ligne et confie au parser le traitement de la ligne. S'il n'y a pas de parser,
	 * la ligne est tout simplement affichée dans la console
	 * @param nomFichier nom du fichier à lire et à parser
	 * @param parser c'est le premier parser de la liste
	 */
	public static void lire(String nomFichier, Parser parser) {
		if (nomFichier == null)
			throw new IllegalArgumentException("Le fichier est null");
		
		File fichier = new File(nomFichier);
		
		if (! fichier.isFile())
			throw new IllegalArgumentException("Le fichier n'existe pas ou n'a pas ete trouvé");
		
		BufferedReader reader = null;
		String ligne;

		try {
			reader = new BufferedReader(new FileReader(fichier));
				
			while ((ligne = reader.readLine()) != null) {
				// On a bien lu une ligne ue fichier, maintenant qu'est-ce qu'on en fait ??
				if (parser==null)
					// Si y a pas de parser, alors on ne sais vraiment pas quoi faire avec et on l'affiche...
					System.out.println("Ligne : "+ligne);
				else
					// Puisqu'on a un parser, on l'utilise. C'est lui qui traitera la ligne
					// pour créer des objets, afficher des trucs, ou je ne sais quoi !
					try {
						parser.traiter(ligne);
					}
					catch (ParserManquantException e) {
						System.err.println("Aucun parser n'existe pour la ligne "+ligne);
					}
					catch (Exception e) {
						// Je ne me foule pas dans le traitement de l'exception
						e.printStackTrace();
					}
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
