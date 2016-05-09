package dev.thetechnokid.rw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RocketWarfare extends Application {

	public static final String NAME = "Rocket Warfare";
	public static final String VERSION = "Dev Edition";
	public static final int BUILD_NUM = 0;
	public static final int FPS = 60;
	public static final String OS = (System.getProperty("os.name")).toUpperCase();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		try {
			AnchorPane parent = (AnchorPane) FXMLLoader.load(RocketWarfare.class.getResource("fxml/Game.fxml"));
			Scene scene = new Scene(parent);

			stage.setScene(scene);
			stage.setResizable(false);
			stage.sizeToScene();
			stage.setTitle(NAME + " | " + VERSION);
			stage.getIcons().add(new Image(RocketWarfare.class.getResourceAsStream("/images/logo.png")));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String settingsFolder() {
		if (OS.contains("WIN"))
			return System.getenv("APPDATA");
		else if (OS.contains("MAC"))
			return System.getProperty("user.home") + "/Library/Application Support";
		else if (OS.contains("NUX"))
			return System.getProperty("user.home");
		return System.getProperty("user.dir");
	}

}
