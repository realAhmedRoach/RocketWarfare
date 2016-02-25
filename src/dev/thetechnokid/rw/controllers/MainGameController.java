package dev.thetechnokid.rw.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.input.*;
import dev.thetechnokid.rw.states.*;
import dev.thetechnokid.rw.utils.Grid;
import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.canvas.*;
import javafx.scene.input.*;
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
	private Keyboard k = new Keyboard();
	private Mouse mouse = new Mouse();

	Timeline gameLoop = new Timeline();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		currentController = this;
		g = theCanvas.getGraphicsContext2D();

		gameLoop.setCycleCount(Timeline.INDEFINITE);
		// final long timeStart = System.currentTimeMillis();

		theCanvas.setFocusTraversable(true);
		theCanvas.getParent().addEventHandler(KeyEvent.ANY, k);
		theCanvas.addEventHandler(MouseEvent.ANY, mouse);

		State.setCurrentState(new MenuState(g));

		KeyFrame kf = new KeyFrame(Duration.millis(1000 / RocketWarfare.FPS), // 60
																				// FPS
				(event) -> {
					g.clearRect(0, 0, theCanvas.getWidth(), theCanvas.getHeight());

					g.setFill(Color.AQUA);
					g.fillRect(0, 0, theCanvas.getWidth(), theCanvas.getHeight());

					if (State.getCurrentState().gridEnabled())
						Grid.render(g);
					State.getCurrentState().render();
					State.getCurrentState().tick();
				});

		gameLoop.getKeyFrames().add(kf);
		gameLoop.play();
	}

	public static MainGameController get() {
		return currentController;
	}

	public static ObservableList<Node> buttons() {
		return currentController.buttons.getChildren();
	}

	public static Keyboard getKeyboard() {
		return currentController.k;
	}

	public static Mouse getMouse() {
		return currentController.mouse;
	}

	public static int getWidth() {
		return (int) currentController.theCanvas.getWidth();
	}

	public static int getHeight() {
		return (int) currentController.theCanvas.getHeight();
	}
}
