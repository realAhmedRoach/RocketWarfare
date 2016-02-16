package dev.thetechnokid.rw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RocketWarfare extends Application {

	public static final String NAME = "Rocket Warfare";
	public static final String VERSION = "Pre-Alpha";
	public static final int BUILD_NUM = 0;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		try {
			AnchorPane parent = (AnchorPane) FXMLLoader.load(getClass().getResource("fxml/Game.fxml"));
			Scene scene = new Scene(parent);

			stage.setScene(scene);
			stage.setResizable(false);
			stage.sizeToScene();
			stage.setTitle(NAME + " | " + VERSION);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
