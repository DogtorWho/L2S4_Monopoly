package main;

import java.io.IOException;

import cases.proprietes.Propriete;
import cases.proprietes.TerrainConstructible;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import partie.Joueur;
import partie.Plateau;
import partie.exceptions.PartieException;


public class Controller_ChoixMaison {
	
	private Joueur joueurActif;
	
	ObservableList<String> listeMaisonPossedees = FXCollections.observableArrayList();
	
	@FXML private ChoiceBox<String> _liste;
	@FXML private Button _ok;
	@FXML private Button _terminer;
	@FXML private Button _annuler;
	@FXML private Label _erreur;
	
	@FXML
	private void initialize() throws IOException {
		joueurActif = Plateau.getPlateau().getListeJoueurs().get(Plateau.getPlateau().getIndexJoueurActif()); 
		
		_liste.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number old_value, Number new_value) {
				_ok.setDisable(false);
			}
	    });	
		
		for(Object Case : joueurActif.getCasesPossedees()) {
			if(Case instanceof TerrainConstructible) {
				listeMaisonPossedees.add(((TerrainConstructible)Case).getCouleur() + " | " + ((TerrainConstructible) Case).getNom());
			}	
		}
		listeMaisonPossedees = listeMaisonPossedees.sorted();	
		_liste.setItems(listeMaisonPossedees);
	}
	
	@FXML
	private void Confirmer(ActionEvent event) throws IOException, PartieException {
		try {
			_erreur.setText("");
			if(_liste.getValue() != null) {
				for(Object Case : joueurActif.getCasesPossedees()) {
					if(((Propriete) Case).getNom().contentEquals(_liste.getValue())) {
						joueurActif.acheterMaison((TerrainConstructible) Case);
						
						_erreur.setTextFill(Color.BLACK);
						_erreur.setText("La maison a ete achetee");
						_ok.setVisible(false);
						_annuler.setVisible(false);
						_terminer.setVisible(true);				
					}	
				}	
			}
		}
		catch(PartieException e) {
			_ok.setDisable(true);
			String [] msg = e.toString().split(":");
			_erreur.setTextFill(Color.RED);
			_erreur.setText(msg[1]);
		}
	}
	
	@FXML
	private void Quitter(ActionEvent event) {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}
}
