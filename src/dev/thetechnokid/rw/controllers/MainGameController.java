package dev.thetechnokid.rw.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.fxml.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MainGameController implements Initializable {

	@FXML
	private Canvas theCanvas;

	private GraphicsContext g;

	Timeline gameLoop = new Timeline();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		g = theCanvas.getGraphicsContext2D();

		gameLoop.setCycleCount(Timeline.INDEFINITE);
		// final long timeStart = System.currentTimeMillis();

		KeyFrame kf = new KeyFrame(Duration.seconds(0.017), // 60 FPS
				(event) -> {
					g.clearRect(0, 0, theCanvas.getWidth(), theCanvas.getHeight());

					g.setFill(Color.AQUA);
					g.fillRect(0, 0, theCanvas.getWidth(), theCanvas.getHeight());
				});

		gameLoop.getKeyFrames().add(kf);
		gameLoop.play();
	}

}
