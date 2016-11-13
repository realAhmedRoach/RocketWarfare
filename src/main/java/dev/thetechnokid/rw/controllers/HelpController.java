package dev.thetechnokid.rw.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.*;

public class HelpController implements Initializable {

	@FXML
	private Label text;
	@FXML
	private Button close;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		text.setText("Controls:\n" + "<- & ->: Tilt Rocket\n" + "Up: Increase Acceleration\n" + "Space: Fullscreen\n"
				+ "Esc: Exit Fullscreen\n\n" + "Menu:\n" + "Change Settings: Edit -> Settings...\n"
				+ "Guide: Help -> Guide...");
		close.setOnAction(event -> close.getParent().getScene().getWindow().hide());
	}

}
