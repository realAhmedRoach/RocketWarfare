package dev.thetechnokid.rw.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dev.thetechnokid.rw.input.Keyboard;
import dev.thetechnokid.rw.stages.*;
import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MainGameController implements Initializable {

	private static MainGameController currentController;
	
	@FXML
	private Canvas theCanvas;
	
	@FXML
	public VBox buttons;

	private GraphicsContext g;
	private Keyboard k;
	
	
	Timeline gameLoop = new Timeline();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		currentController = this;
		g = theCanvas.getGraphicsContext2D();

		gameLoop.setCycleCount(Timeline.INDEFINITE);
		// final long timeStart = System.currentTimeMillis();
		
		theCanvas.addEventHandler(KeyEvent.ANY, k);

		State.setCurrentState(new MenuState(g));
		
		KeyFrame kf = new KeyFrame(Duration.seconds(0.017), // 60 FPS
				(event) -> {
					g.clearRect(0, 0, theCanvas.getWidth(), theCanvas.getHeight());

					g.setFill(Color.AQUA);
					g.fillRect(0, 0, theCanvas.getWidth(), theCanvas.getHeight());
					
					State.getCurrentStage().render();
				});

		gameLoop.getKeyFrames().add(kf);
		gameLoop.play();
	}

	public static MainGameController get() {
		return currentController;
	}
	
	public ObservableList<Node> buttons() {
		return buttons.getChildren();
	}
	
	public Keyboard getKeyboard() {
		return k;
	}
}
