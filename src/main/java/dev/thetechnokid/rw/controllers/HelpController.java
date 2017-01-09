package dev.thetechnokid.rw.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HelpController implements Initializable {

	@FXML
	private Label text;
	@FXML
	private JFXButton close;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		text.setText("Controls:\n" + "<- & ->: Tilt Rocket\n" + "Up: Increase Acceleration\n" + "Space: Fullscreen\n"
				+ "Esc: Exit Fullscreen\n\n" + "Menu:\n" + "Change Settings: Edit -> Settings...\n"
				+ "Guide: Help -> Guide...");
		close.getStyleClass().add("button-raised1");
		close.setOnAction(event -> close.getParent().getScene().getWindow().hide());
	}

}
