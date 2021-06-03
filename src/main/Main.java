package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import partie.Plateau;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {	
			Plateau.getPlateau().initialiserPlateau();			
			
			Parent root = FXMLLoader.load(getClass().getResource("/main/Accueil.fxml"));
		   
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Monopoly");
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
		primaryStage.centerOnScreen();
		primaryStage.setOnCloseRequest(e -> Platform.exit());	
	} 
		
	public static void main(String[] args) {
		launch(args);
	}
}
