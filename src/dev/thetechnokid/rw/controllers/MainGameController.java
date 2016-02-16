package dev.thetechnokid.rw.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

public class MainGameController implements Initializable {

	@FXML
	private Canvas theCanvas;
	
	private GraphicsContext g;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println("adf");
		g = theCanvas.getGraphicsContext2D();
		g.setFill(Color.AQUA);
		g.fillRect(0, 0, theCanvas.getWidth(), theCanvas.getHeight());
	}

}
