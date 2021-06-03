package main;

import java.io.IOException;
import java.util.ArrayList;

import cartes.Carte;
import cases.proprietes.Propriete;
import cases.proprietes.TerrainConstructible;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import partie.Des;
import partie.Joueur;
import partie.Plateau;
import partie.exceptions.BankruptException;
import partie.exceptions.PartieException;

public class Controller_Plateau {
	
	private ArrayList<Image> PionsJoueurs = new ArrayList<Image>();	
	private ArrayList<ImageView> AffichagePions = new ArrayList<ImageView>();	
	private Joueur joueurActif;
	
	@FXML private Label _msgGeneral;
	@FXML private Label _des;
	@FXML private Label _joueur;
	@FXML private Label _argent;
	@FXML private Label _casePosition;
	@FXML private Label _caseProprietaire;
	@FXML private Label _caseNom;
	@FXML private Label _casePrix;
	@FXML private Label _nbMaisons;
	@FXML private ListView<String> _listeProprietes;
	@FXML private Button _lancerDes;
	@FXML private Button _acheterTerrain;
	@FXML private Button _acheterMaison;
	@FXML private Button _carteSortirPrison;
	@FXML private Button _finDuTour;
	@FXML private Button _payerPrison;
	@FXML private Button _tenterDouble;
	@FXML private ImageView _plateau;
	@FXML private ImageView _pion1;
	@FXML private ImageView _pion2;
	@FXML private ImageView _pion3;
	@FXML private ImageView _pion4;

	public void getListePions(ArrayList<Image> listePions) {
		PionsJoueurs = listePions;
		
		_pion1.setImage(PionsJoueurs.get(0));
		AffichagePions.add(_pion1);
		
		_pion2.setImage(PionsJoueurs.get(1));
		AffichagePions.add(_pion2);
		
		if(PionsJoueurs.size() >= 3) {
			_pion3.setImage(PionsJoueurs.get(2));
			AffichagePions.add(_pion3);
		}
		
		if(PionsJoueurs.size() == 4) {
			_pion4.setImage(PionsJoueurs.get(3));
			AffichagePions.add(_pion4);
		}
		
		AfficherInformations();	
	}

	@FXML
	private void initialize() throws IOException {	
		joueurActif = Plateau.getPlateau().getListeJoueurs().get(Plateau.getPlateau().getIndexJoueurActif());    

		_acheterTerrain.setDisable(true);
		_finDuTour.setDisable(true);
	}
	
	private void afficherErreur(String erreur) {
		Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Erreur");
	    String s = erreur;
	    alert.setContentText(s);  
	    alert.showAndWait();
	}
	
	private void afficherVictoire(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/FinDePartie.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Monopoly : fin de la partie");
		stage.show();
		stage.centerOnScreen();
		stage.setOnCloseRequest(e -> Platform.exit());
	}
	
	private void actionBankrupt(String msg) throws PartieException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Bankrupt");
		alert.setHeaderText("Vous n'avez plus d'argent");
		String s = msg;
		alert.setContentText(s);
		alert.show();
		
		AfficherInformations();
		
		// suppression du joueur :
		int indexjoueur = Plateau.getPlateau().getIndexJoueurActif();
		PionsJoueurs.remove(indexjoueur);
		AffichagePions.get(indexjoueur).setImage(null);
		AffichagePions.remove(indexjoueur);
		Plateau.getPlateau().getListeJoueurs().remove(indexjoueur);	
		Plateau.getPlateau().setNbJoueurs(Plateau.getPlateau().getNbJoueurs() - 1);
		
		_finDuTour.setDisable(false);
		_lancerDes.setDisable(true);
		_acheterTerrain.setDisable(true);
		_acheterMaison.setDisable(true);
		_payerPrison.setDisable(true);
		_tenterDouble.setDisable(true);
	}
	
	private void AfficherInformations() {
		_msgGeneral.setText("Tour de : " + joueurActif.getNom());
		_joueur.setText(joueurActif.getNom());
		_argent.setText(""+joueurActif.getArgent());
		_listeProprietes.getItems().clear();
		for(Object Case : joueurActif.getCasesPossedees()) {
			_listeProprietes.getItems().add(((Propriete) Case).getNom());
		}
		
		AffichagePions.get(Plateau.getPlateau().getIndexJoueurActif()).setX(Plateau.getPlateau().getListeCoordonnees().get(joueurActif.getPosition()).getX());
		AffichagePions.get(Plateau.getPlateau().getIndexJoueurActif()).setY(Plateau.getPlateau().getListeCoordonnees().get(joueurActif.getPosition()).getY());
		
		_msgGeneral.setText("Position : " + joueurActif.getPosition());	
		_casePosition.setText(""+joueurActif.getPosition());
		if(Plateau.getPlateau().getCase(joueurActif.getPosition()).getProprietaire() != null) {
			_caseProprietaire.setText(Plateau.getPlateau().getCase(joueurActif.getPosition()).getProprietaire().getNom());
		}
		else {
			_caseProprietaire.setText("Aucun");
		}
		if(Plateau.getPlateau().getCase(joueurActif.getPosition()) instanceof Propriete) {
			_caseNom.setText(((Propriete) Plateau.getPlateau().getCase(joueurActif.getPosition())).getNom());
			_casePrix.setText(""+((Propriete) Plateau.getPlateau().getCase(joueurActif.getPosition())).getPrixAchat());
			if(Plateau.getPlateau().getCase(joueurActif.getPosition()) instanceof TerrainConstructible) {
				_nbMaisons.setText(""+((TerrainConstructible) Plateau.getPlateau().getCase(joueurActif.getPosition())).getNombreMaison());
			}
		}	
		else {
			_caseNom.setText("");
			_casePrix.setText("");
			_nbMaisons.setText("");
		}
	}
	
	private void ActionPrison() throws PartieException {
		try {
			_lancerDes.setDisable(true);
			_payerPrison.setVisible(true);
			_tenterDouble.setVisible(true);
			_payerPrison.setDisable(false);
			_tenterDouble.setDisable(false);
			_acheterMaison.setDisable(true);
			_des.setText("");
			AfficherInformations();
			_msgGeneral.setText("Tour de : " + joueurActif.getNom() + " [PRISON]");	
			
			Plateau.getPlateau().getCase(joueurActif.getPosition()).appliquerEffets(joueurActif);
		}
		catch(BankruptException e) {		
			actionBankrupt("La partie est finie pour vous");
		}		
	}
	
	@FXML 
	private void LancerDes(ActionEvent event) throws PartieException {	
		try {
			Des.getDes().LancerDes();
			_des.setText(""+Des.getDes().getSomme());

			joueurActif.avancer(Des.getDes().getSomme());

			if(joueurActif.isPrisonnier()) {
				_acheterTerrain.setDisable(true);
				_acheterMaison.setDisable(true);
			}
			
			AfficherInformations();
				
			if(joueurActif.isCarteSortirPrison()) {
				_carteSortirPrison.setVisible(true);
				if(! joueurActif.isPrisonnier()) {
					_carteSortirPrison.setDisable(true);
				}		
			}
			if( (Plateau.getPlateau().getCase(joueurActif.getPosition()) instanceof Propriete) && (Plateau.getPlateau().getCase(joueurActif.getPosition()).getProprietaire() == null) ) {
				_acheterTerrain.setDisable(false);
			}
			_lancerDes.setDisable(true);		
			_finDuTour.setDisable(false);	
		}
		catch(BankruptException e) {	
			actionBankrupt("La partie est finie pour vous");
		}
	}
	
	@FXML 
	private void AcheterTerrain(ActionEvent event) throws PartieException {
		try {
			if(Plateau.getPlateau().getCase(joueurActif.getPosition()) instanceof Propriete) {
				joueurActif.acheterPropriete((Propriete) Plateau.getPlateau().getCase(joueurActif.getPosition()));
				_argent.setText(""+joueurActif.getArgent());
				_listeProprietes.getItems().add(((Propriete) Plateau.getPlateau().getCase(joueurActif.getPosition())).getNom());
			}
			_acheterTerrain.setDisable(true);
		}
		catch(PartieException e) {	
			String [] msg = e.toString().split(":");	
		    afficherErreur(msg[1]);
		}
	}	
	
	@FXML 
	private void AcheterMaison(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/main/ChoixMaison.fxml"));
		   
		Scene scene = new Scene(root, 292, 124);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Choix du terrain pour la maison");	
		stage.show();
		stage.centerOnScreen();
	}	
	
	@FXML 
	private void UtiliserCarteSortirPrison(ActionEvent event) throws PartieException {
		try {
			for(Object Carte : joueurActif.getCartesPossedees()) {
				if(Carte.toString().contains("Liberation")) {
					joueurActif.utiliserCarte((Carte) Carte);
					joueurActif.teleporter(Plateau.getPlateau().trouverPositionCase("SimpleVisite"), false);
				}
			}
		}
		catch(PartieException e) {	
			String [] msg = e.toString().split(":");	
		    afficherErreur(msg[1]);
		}
	}
	
	@FXML 
	private void PasserAuJoueurSuivant(ActionEvent event) throws PartieException, IOException {
		try {
			if(Plateau.getPlateau().getIndexJoueurActif() >= Plateau.getPlateau().getNbJoueurs()-1) {
				Plateau.getPlateau().setIndexJoueurActif(0);	
			}
			else {
				Plateau.getPlateau().setIndexJoueurActif(Plateau.getPlateau().getIndexJoueurActif() + 1);
			}
			joueurActif = Plateau.getPlateau().getListeJoueurs().get(Plateau.getPlateau().getIndexJoueurActif());
			
			if(Plateau.getPlateau().getNbJoueurs() == 1) {
				afficherVictoire(event);
			}
				
			AfficherInformations();
			_lancerDes.setDisable(false);
			_acheterTerrain.setDisable(true);
			_finDuTour.setDisable(true);
			_acheterMaison.setDisable(false);
			_payerPrison.setVisible(false);
			_tenterDouble.setVisible(false);
			
			if(joueurActif.isPrisonnier()) {
				ActionPrison();
			}
		}
		catch(PartieException e) {	
			String [] msg = e.toString().split(":");	
		    afficherErreur(msg[1]);
		}	
	}

	@FXML 
	private void PayerPourSortirDePrison(ActionEvent event) throws PartieException {
		try {
			joueurActif.PayerPourSortirDePrison();
			AfficherInformations();
			
			_payerPrison.setDisable(true);
			_tenterDouble.setDisable(true);
			_finDuTour.setDisable(false);
		}
		catch(BankruptException e) {	
			actionBankrupt("La partie est finie pour vous");
		}	
	}
	
	@FXML 
	private void TenterDeFaireUnDouble(ActionEvent event) throws PartieException {
		try {
			if(joueurActif.TenterDeSortirDePrison()) {
				AfficherInformations();
				_des.setText(""+Des.getDes().getSomme());	
			}
			
			_payerPrison.setDisable(true);
			_tenterDouble.setDisable(true);
			_finDuTour.setDisable(false);
		}
		catch(BankruptException e) {	
			actionBankrupt("La partie est finie pour vous");
		}	
	}
	
}
