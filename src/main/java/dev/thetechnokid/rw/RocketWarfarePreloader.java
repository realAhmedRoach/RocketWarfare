package dev.thetechnokid.rw;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class RocketWarfarePreloader extends Preloader implements Initializable {
	
	private static final String NEWS = "http://thetechnokid.github.io/RocketWarfare";
	@FXML
	private WebView news;
	@FXML
	private Label status;
	@FXML
	private ProgressBar progress;
	@FXML
	private MenuItem close;
	@FXML
	private MenuItem about;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane parent = (BorderPane) FXMLLoader.load(getClass().getResource("fxml/Preloader.fxml"));
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.sizeToScene();
		stage.setTitle("Rocket Warfare Launcher");
		stage.getIcons().add(new Image(RocketWarfare.class.getResourceAsStream("/images/logo.png")));
		stage.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		news.getEngine().load(NEWS);
	}
}
