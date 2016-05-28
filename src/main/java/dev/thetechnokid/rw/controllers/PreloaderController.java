package dev.thetechnokid.rw.controllers;

import java.net.URL;
import java.util.*;
import java.util.function.Function;

import dev.thetechnokid.rw.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.web.WebView;

public class PreloaderController implements Initializable {

	@FXML
	private WebView news;
	@FXML
	private TextArea status;
	@FXML
	private Button go;
	@FXML
	private ProgressBar progress;

	private ArrayList<Function<Boolean, String>> tasks;

	private void initTasks() {
		tasks = new ArrayList<>();
		tasks.add((stuff) -> {
			return Version.newVersion() ? "New version found:" : "You have the latest version:";
		});
		tasks.add((stuff) -> {
			return Version.getVersion() + ": " + Version.getFriendly() + "; " + Version.getDescription();
		});
		tasks.add((stuff) -> {
			return "You are in " + (Version.isOffline() ? "offline" : "online") + " mode";
		});
		tasks.add((stuff) -> {
			return "Done!";
		});
	}

	private void startTasks() {
		double i = 0.0;
		for (Function<Boolean, String> function : tasks) {
			status.appendText(function.apply(true) + "\n");
			progress.setProgress(i / (tasks.size() - 1));
			i++;
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		go.setOnAction(event -> {
			RocketWarfare.getStage().show();
			go.getParent().getScene().getWindow().hide();
		});
		news.getEngine().load(RocketWarfarePreloader.NEWS);
		initTasks();
		startTasks();
	}

}
