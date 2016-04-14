package dev.thetechnokid.rw.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.input.Keyboard;
import dev.thetechnokid.rw.input.Mouse;
import dev.thetechnokid.rw.states.MenuState;
import dev.thetechnokid.rw.states.State;
import dev.thetechnokid.rw.utils.Grid;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MainGameController implements Initializable {

	private static MainGameController currentController;

	@FXML
	private Canvas theCanvas;
	@FXML
	private VBox buttons;
	@FXML
	private FlowPane integrations;
	@FXML
	private Label status;

	private GraphicsContext g;
	private Keyboard k = new Keyboard();
	private Mouse mouse = new Mouse();

	private Timeline gameLoop = new Timeline();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		currentController = this;
		g = theCanvas.getGraphicsContext2D();

		gameLoop.setCycleCount(Timeline.INDEFINITE);
		// final long timeStart = System.currentTimeMillis();

		initCanvas();

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

					k.tick();
				});

		gameLoop.getKeyFrames().add(kf);
		gameLoop.play();
	}

	private void initCanvas() {
		theCanvas.setFocusTraversable(true);
		theCanvas.getParent().addEventHandler(KeyEvent.ANY, k);
		theCanvas.addEventHandler(MouseEvent.ANY, mouse);

		g.setLineWidth(0.3);
	}

	public static MainGameController get() {
		return currentController;
	}

	public static ObservableList<Node> buttons() {
		return currentController.buttons.getChildren();
	}

	public static ObservableList<Node> integrations() {
		return currentController.integrations.getChildren();
	}
	
	public static void setStatus(String statusText) {
		currentController.status.setText(statusText);
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
