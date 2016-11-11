package dev.thetechnokid.rw.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.*;

public class SettingsController implements Initializable {

	@FXML
	private TextField displayName;
	@FXML
	private CheckBox rememberMe;
	@FXML
	private ChoiceBox<String> theme;
	@FXML
	private ChoiceBox<String> language;
	@FXML
	private Button ok;
	@FXML
	private Button cancel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ok.setOnAction((event) -> {
			if (!displayName.getText().isEmpty())
				MainGameController.get().USER.getPrefs().setDisplayName(displayName.getText());
		});
	}

}
