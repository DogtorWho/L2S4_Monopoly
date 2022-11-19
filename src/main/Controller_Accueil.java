package main;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import partie.Joueur;
import partie.Plateau;

/**
 * La classe Controller_Accueil sert à controller la fenetre JavaFx de l'accueil du jeu
 */
public class Controller_Accueil {
	
	/**
	 * entier qui stock le nombre de joueurs dans la partie (max 4)
	 */
	private int nbJoueurs = 2;
	/**
	 * String qui stock le nom du pion choisit par le joueur 1
	 */
	private String Pion_1 = null;
	/**
	 * String qui stock le nom du pion choisit par le joueur 2
	 */
	private String Pion_2 = null;
	/**
	 * String qui stock le nom du pion choisit par le joueur 3
	 */
	private String Pion_3 = null;
	/**
	 * String qui stock le nom du pion choisit par le joueur 4
	 */
	private String Pion_4 = null;
	
	/**
	 * ArrayList<Image> qui stock les images de tous les pions qui existent
	 */
	private ArrayList<Image> PionsJoueurs = new ArrayList<Image>();
	
	/**
	 * ObservableList<String> qui stock le nom de tous les pions qui existent
	 */
	ObservableList<String> listePions = FXCollections.observableArrayList("Bateau", "Brouette", "Chapeau"
			, "Chat", "Chaussure", "Chien", "DeACoudre", "Voiture");
	
	@FXML private Slider _nbJoueurs;
	@FXML private TextField _nom1;
	@FXML private TextField _nom2;
	@FXML private TextField _nom3;
	@FXML private TextField _nom4;
	@FXML private ChoiceBox<String> _pion1;
	@FXML private ChoiceBox<String> _pion2;
	@FXML private ChoiceBox<String> _pion3;
	@FXML private ChoiceBox<String> _pion4;
	@FXML private VBox _joueur3;
	@FXML private VBox _joueur4;
	@FXML private Label _erreur;
	@FXML private Button _lancer;
	
	@FXML
	private void initialize() throws IOException {
		_pion1.setItems(listePions);
		_pion2.setItems(listePions);
		_pion3.setItems(listePions);
		_pion4.setItems(listePions);
		
		_nbJoueurs.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number old_value, Number new_value) {
				if(new_value.intValue() == 2) {
					nbJoueurs = 2;
					_joueur3.setDisable(true);
					_joueur4.setDisable(true);
				}
				else if(new_value.intValue() == 3) {
					nbJoueurs = 3;
					_joueur3.setDisable(false);
					_joueur4.setDisable(true);
				}
				else {
					nbJoueurs = 4;
					_joueur3.setDisable(false);
					_joueur4.setDisable(false);
				}
			}
	    });
	}
	
	private String choixPion(String pion) {
		switch(pion) {
			case "Bateau" : return "/BateauTrans.png";
			case "Brouette" : return "/BrouetteTrans.png";
			case "Chapeau" : return "/ChapeauTrans.png";
			case "Chat" : return "/ChatTrans.png";
			case "Chaussure" : return "/ChaussureTrans.png";
			case "Chien" : return "/ChienTrans.png";
			case "DeACoudre" : return "/DeACoudreTrans.png";
			case "Voiture" : return "/VoitureTrans.png";
			default : return "erreur";
		}
	}
	
	private boolean Verifications() {
		if(_nom1 == null || _nom1.getText().trim().isEmpty()){
			_erreur.setTextFill(Color.RED);
			_erreur.setText("Le joueur 1 ne peut pas s'appeler comme ca");
			return false;
		}
		else if(_pion1.getValue() == null) {
			_erreur.setTextFill(Color.RED);
			_erreur.setText("Le joueur 1 n'a pas choisit de pion");
			return false;
		}
		else if(_pion1.getValue() == _pion2.getValue() || _pion1.getValue() == _pion3.getValue() || _pion1.getValue() == _pion4.getValue()) {
			_erreur.setTextFill(Color.RED);
			_erreur.setText("Le joueur 1 ne peut pas prendre ce pion");
			return false;
		}
		else if(_nom2 == null || _nom2.getText().trim().isEmpty()){
			_erreur.setTextFill(Color.RED);
			_erreur.setText("Le joueur 2 ne peut pas s'appeler comme ca");
			return false;
		}
		else if(_pion2.getValue() == null) {
			_erreur.setTextFill(Color.RED);
			_erreur.setText("Le joueur 2 n'a pas choisit de pion");
			return false;
		}
		else if(_pion2.getValue() == _pion1.getValue() || _pion2.getValue() == _pion3.getValue() || _pion2.getValue() == _pion4.getValue()) {
			_erreur.setTextFill(Color.RED);
			_erreur.setText("Le joueur 2 ne peut pas prendre ce pion");
			return false;
		}
		
		if(nbJoueurs >= 3) {
			if(_nom3 == null || _nom3.getText().trim().isEmpty()){
				_erreur.setTextFill(Color.RED);
				_erreur.setText("Le joueur 3 ne peut pas s'appeler comme ca");
				return false;
			}
			else if(_pion3.getValue() == null) {
				_erreur.setTextFill(Color.RED);
				_erreur.setText("Le joueur 3 n'a pas choisit de pion");
				return false;
			}
			else if(_pion3.getValue() == _pion1.getValue() || _pion3.getValue() == _pion2.getValue() || _pion3.getValue() == _pion4.getValue()) {
				_erreur.setTextFill(Color.RED);
				_erreur.setText("Le joueur 3 ne peut pas prendre ce pion");
				return false;
			}
		
			if(nbJoueurs == 4) {
				if(_nom4 == null || _nom4.getText().trim().isEmpty()){
					_erreur.setTextFill(Color.RED);
					_erreur.setText("Le joueur 4 ne peut pas s'appeler comme ca");
					return false;
				}
				else if(_pion4.getValue() == null) {
					_erreur.setTextFill(Color.RED);
					_erreur.setText("Le joueur 4 n'a pas choisit de pion");
					return false;
				}
				else if(_pion4.getValue() == _pion1.getValue() || _pion4.getValue() == _pion2.getValue() || _pion4.getValue() == _pion3.getValue()) {
					_erreur.setTextFill(Color.RED);
					_erreur.setText("Le joueur 4 ne peut pas prendre ce pion");
					return false;
				}
			}
		}
		return true;
	}
	
	@FXML void LancerPartie(ActionEvent event) throws IOException {
		// Creation joueurs		
		if(Verifications()) {
			// joueur 1
			Joueur joueur_1 = new Joueur(_nom1.getText());
			Plateau.getPlateau().ajouterJoueur(joueur_1);
			Pion_1 = choixPion(_pion1.getValue());
			Image image_j1 = new Image(Pion_1);
			PionsJoueurs.add(image_j1);
			
			// joueur 2
			Joueur joueur_2 = new Joueur(_nom2.getText());
			Plateau.getPlateau().ajouterJoueur(joueur_2);
			Pion_2 = choixPion(_pion2.getValue());
			Image image_j2 = new Image(Pion_2);
			PionsJoueurs.add(image_j2);
			
			if(nbJoueurs >= 3) {
				// joueur 3
				Joueur joueur_3 = new Joueur(_nom3.getText());
				Plateau.getPlateau().ajouterJoueur(joueur_3);
				Pion_3 = choixPion(_pion3.getValue());
				Image image_j3 = new Image(Pion_3);
				PionsJoueurs.add(image_j3);
					
				if(nbJoueurs == 4) {
					// joueur 4
					Joueur joueur_4 = new Joueur(_nom4.getText());
					Plateau.getPlateau().ajouterJoueur(joueur_4);
					Pion_4 = choixPion(_pion4.getValue());
					Image image_j4 = new Image(Pion_4);
					PionsJoueurs.add(image_j4);
				}
			}
								
			// Affichage plateau			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/Plateau.fxml"));
			Parent root = loader.load();
			
			Controller_Plateau plateau = loader.getController();
			plateau.getListePions(PionsJoueurs);
			
			Scene scene = new Scene(root);
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setTitle("Monopoly");	
			stage.show();
			stage.centerOnScreen();
			stage.setOnCloseRequest(e -> Platform.exit());
		}
	}	
	
}
