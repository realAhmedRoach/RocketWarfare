package dev.thetechnokid.rw.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import dev.thetechnokid.rw.*;
import dev.thetechnokid.rw.net.Preferences;
import dev.thetechnokid.rw.utils.Language;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;

public class SettingsController implements Initializable {

	@FXML
	private TextField displayName;
	@FXML
	private TextField status;
	@FXML
	private CheckBox rememberMe;
	@FXML
	private ComboBox<String> theme;
	@FXML
	private ComboBox<String> language;
	@FXML
	private PasswordField password;
	@FXML
	private Button ok;
	@FXML
	private Button cancel;
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
				MainGameController.getParent().getStylesheets()
						.add(Main.class.getResource("fxml/" + prefs.getTheme()).toExternalForm());
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
