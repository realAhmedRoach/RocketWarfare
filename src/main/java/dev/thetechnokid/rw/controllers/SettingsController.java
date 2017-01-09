package dev.thetechnokid.rw.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;

import dev.thetechnokid.rw.Main;
import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.net.Preferences;
import dev.thetechnokid.rw.utils.Language;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class SettingsController implements Initializable {

	@FXML
	private JFXTextField displayName;
	@FXML
	private JFXTextField status;
	@FXML
	private JFXCheckBox rememberMe;
	@FXML
	private JFXComboBox<String> theme;
	@FXML
	private JFXComboBox<String> language;
	@FXML
	private JFXPasswordField password;
	@FXML
	private JFXButton ok;
	@FXML
	private JFXButton cancel;
	@FXML
	private Label statusText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Preferences prefs = MainGameController.get().USER.getPrefs();
		rememberMe.setSelected(prefs.isRemembered());

		language.getItems().addAll(Language.LOCALES.keySet());
		theme.getItems().addAll(RocketWarfare.getThemes());

		ok.setOnAction((event) -> {
			if (!MainGameController.get().USER.authenticate(password.getText())) {
				statusText.setText("Incorrect Password!");
				return;
			}
			if (!displayName.getText().isEmpty())
				prefs.setDisplayName(displayName.getText());
			if (!status.getText().isEmpty())
				prefs.setStatus(status.getText());
			prefs.setRemembered(rememberMe.isSelected());
			if (!rememberMe.isSelected()) {
				File remFile = new File(
						RocketWarfare.settingsFolder() + "/users/" + MainGameController.get().USER.getName() + "-");
				if (remFile.exists())
					remFile.delete();
			}
			if (theme.getValue() != null) {
				prefs.setTheme(theme.getValue().toLowerCase());
				MainGameController.getParent().getStylesheets().clear();
				MainGameController.getParent().getStylesheets().addAll(
						Main.class.getResource("fxml/" + prefs.getTheme()).toExternalForm(),
						Main.class.getResource("fxml/style.css").toExternalForm());
			}
			if (language.getValue() != null)
				prefs.setLanguage(Language.LOCALES.get(language.getValue()));

			close(event);
		});

		cancel.setOnAction(this::close);
	}

	private void close(ActionEvent e) {
		ok.getParent().getScene().getWindow().hide();
	}

}
