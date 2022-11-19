package main;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import partie.Plateau;

/**
 * La classe Controller_FinDePartie sert à controller la fenetre JavaFx de la fin de partie
 */
public class Controller_FinDePartie {
	
	@FXML private Label _gagnant;
	
	@FXML
	private void initialize() throws IOException {
		_gagnant.setText(Plateau.getPlateau().getListeJoueurs().get(Plateau.getPlateau().getIndexJoueurActif()).getNom());
	}
	
	@FXML
	private void relancer(ActionEvent event) throws IOException {
		Plateau.getPlateau().initialiserPlateau();	
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/Accueil.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Monopoly");	
		stage.show();
		stage.centerOnScreen();
		stage.setOnCloseRequest(e -> Platform.exit());
	}
	
	@FXML
	private void quitter(ActionEvent event) throws IOException {
		Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
		primaryStage.close();
	}
}
