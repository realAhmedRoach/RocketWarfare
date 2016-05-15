package dev.thetechnokid.rw;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Preloader;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class RocketWarfarePreloader extends Preloader implements Initializable {
	
	private static final String NEWS = "https://github.com/theTechnoKid/RocketWarfare/blob/master/NEWS.md";
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		news.getEngine().load(NEWS);
	}
}
