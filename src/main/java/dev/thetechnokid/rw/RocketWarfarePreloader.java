package dev.thetechnokid.rw;

import java.net.URL;
import java.util.*;
import java.util.function.Function;

import javafx.application.Preloader;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class RocketWarfarePreloader extends Preloader implements Initializable {
	
	private static final String NEWS = "http://thetechnokid.github.io/RocketWarfare";
	@FXML
	private WebView news;
	@FXML
	private TextArea status;
	@FXML
	private Button go;
	@FXML
	private ProgressBar progress;

	private ArrayList<Function<Boolean, String>> tasks;

	public static void main(String[] args) {
		launch(args);
	}

	private void initTasks() {
		tasks = new ArrayList<>();
		tasks.add((stuff) -> {
			return Version.newVersion() ? "New version found:" : "You have the latest version:";
		});
		tasks.add((stuff) -> {
			return Version.getVersion() + ": " + Version.getFriendly() + "; " + Version.getDescription();
		});
		tasks.add((stuff) -> {
			return "Done!";
		});
	}

	private void startTasks() {
		double i = 0.0;
		for (Function<Boolean, String> function : tasks) {
			status.appendText(function.apply(true) + "\n");
			progress.setProgress(i / tasks.size());
			i++;
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		initTasks();

		BorderPane parent = (BorderPane) FXMLLoader.load(getClass().getResource("fxml/Preloader.fxml"));
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.sizeToScene();
		stage.setTitle("Rocket Warfare Launcher");
		stage.getIcons().add(new Image(RocketWarfare.class.getResourceAsStream("/images/logo.png")));
		stage.show();

		startTasks();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		news.getEngine().load(NEWS);
	}
}
