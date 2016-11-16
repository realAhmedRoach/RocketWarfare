package dev.thetechnokid.rw.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.input.*;
import dev.thetechnokid.rw.net.User;
import dev.thetechnokid.rw.states.*;
import dev.thetechnokid.rw.utils.*;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.util.Duration;

public class MainGameController implements Initializable {

	private static MainGameController currentController;

	@FXML
	private AnchorPane parent;
	@FXML
	private Canvas theCanvas;
	@FXML
	private VBox buttons;
	@FXML
	private FlowPane integrations;
	@FXML
	private Label status;
	@FXML
	private MenuItem close;
	@FXML
	private MenuItem settings;
	@FXML
	private MenuItem guide;

	private GraphicsContext g;
	private Keyboard k = new Keyboard();
	private Mouse mouse = new Mouse();

	private Timeline gameLoop = new Timeline();

	private Logger log = new Logger();

	public boolean FIRST_TIME = false;
	public User USER;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		currentController = this;

		init();

		KeyFrame kf = new KeyFrame(Duration.millis(1000 / RocketWarfare.FPS), // 60
																				// FPS
				(event) -> {
					g.clearRect(0, 0, theCanvas.getWidth(), theCanvas.getHeight());

					g.setFill(Color.AQUA);
					g.fillRect(0, 0, theCanvas.getWidth(), theCanvas.getHeight());

					if (State.getCurrentState().gridEnabled())
						Grid.render(g);

					try {
						State.getCurrentState().render();
						State.tickAll();
					} catch (Exception e) {
						e.printStackTrace();
						log.log(e.getMessage(), Logger.Level.ERROR);
					}

					k.tick();
				});

		gameLoop.getKeyFrames().add(kf);
		gameLoop.play();
	}

	private void init() {
		g = theCanvas.getGraphicsContext2D();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		Assets.init();
		initMenu();
		initCanvas();
		State.setCurrentState(new MenuState(g));
	}

	private void initMenu() {
		close.setOnAction(event -> {
			Platform.exit();
		});
		settings.setOnAction(event -> {
			try {
				final Stage dialog = new Stage();
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.initOwner(RocketWarfare.getStage());
				dialog.setTitle("Settings");

				VBox parent = (VBox) FXMLLoader.load(RocketWarfare.class.getResource("fxml/Settings.fxml"));
				Scene scene = new Scene(parent, parent.getPrefWidth(), parent.getPrefHeight());
				dialog.setScene(scene);
				dialog.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		guide.setOnAction(event -> {
			final Stage dialog = new Stage();
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.initOwner(RocketWarfare.getStage());
			dialog.setTitle("Guide");

			try {
				VBox parent = (VBox) FXMLLoader.load(RocketWarfare.class.getResource("fxml/Help.fxml"));
				Scene scene = new Scene(parent, parent.getPrefWidth(), parent.getPrefHeight());
				dialog.setScene(scene);
				dialog.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
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

	public static Canvas getCanvas() {
		return currentController.theCanvas;
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

	public static Parent getParent() {
		return get().parent;
	}
}
