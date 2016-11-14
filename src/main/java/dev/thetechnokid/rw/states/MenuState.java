package dev.thetechnokid.rw.states;

import java.io.*;

import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.net.User;
import dev.thetechnokid.rw.utils.*;
import javafx.geometry.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

public class MenuState extends State {

	private String name;
	private boolean gridded;
	private int i = 0;
	private int z = 0;
	private Animator logo;
	private boolean go;

	public MenuState(GraphicsContext g) {
		super(g);
	}

	@Override
	protected void init() {

		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);

		logo = new Animator(150, () -> {
			i++;
			if (i == 10) {
				i = 0;
				z = 1;
			}
			if (i == 1 && z == 1) {
				i = 0;
				z = 0;
			}
		});

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		Label nameLabel = new Label(Language.get("name") + ": ");
		TextField nameField = new TextField();
		nameField.setPromptText(Language.get("name"));
		nameField.setMaxWidth(100);

		Label pwLabel = new Label(Language.get("password") + ":");
		PasswordField pwField = new PasswordField();
		pwField.setPromptText(Language.get("password"));
		pwField.setMaxWidth(100);

		CheckBox register = new CheckBox("Register");
		CheckBox rememberMe = new CheckBox("Remember Me");

		Button start = new Button(Language.get("login"));
		start.setOnAction((event) -> {
			name = nameField.getText();
			if (login(name, pwField.getText(), register.isSelected(), rememberMe.isSelected())) {
				go = true;
			}
			g.getCanvas().requestFocus();
		});

		grid.add(nameLabel, 0, 1);
		grid.add(nameField, 1, 1);
		grid.add(pwLabel, 0, 2);
		grid.add(pwField, 1, 2);
		grid.add(rememberMe, 1, 3);
		grid.add(register, 0, 3);

		MainGameController.buttons().addAll(grid, start);
		loadRemembered();
	}

	private void loadRemembered() {
		File folder = new File(RocketWarfare.settingsFolder() + "/users/");
		File rem = null;
		for (File file : folder.listFiles()) {
			if (file.getName().endsWith("-"))
				rem = file;
		}

		if (rem == null)
			return;

		User user = null;
		File file = new File(RocketWarfare.settingsFolder() + "/users/" + rem.getName());
		file.setReadable(true);
		try (ObjectInputStream i = new ObjectInputStream(new FileInputStream(file));) {
			user = (User) i.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MainGameController.get().USER = user;
		if (user != null) {
			Language.init();

			go = true;
		}
	}

	private boolean login(String name, String password, boolean register, boolean rememberMe) {
		File file = new File(RocketWarfare.settingsFolder() + "/users/" + name);
		try {
			User user = User.load(name, password);
			if (user == null) {
				if (!register) {
					MainGameController.setStatus("Incorrect username or password.");
					return false;
				} else if (register) {
					if (file.exists()) {
						MainGameController.setStatus("User with that name already exists!");
						return false;
					}
					MainGameController.get().FIRST_TIME = true;
					User newUser = new User(name, password);
					MainGameController.get().USER = newUser;
					newUser.save();
					return true;
				}
			} else if (user != null) {
				MainGameController.get().USER = user;
				MainGameController.get().USER.getPrefs().setRemembered(rememberMe);
				MainGameController.setStatus("Login Successfull!");
				Language.init();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public void render() {
		g.drawImage(Assets.crop(Assets.LOGO, i, z), MainGameController.getWidth() / 2 - Grid.SIZE,
				MainGameController.getHeight() / 2 - Grid.SIZE);

		logo.tick();
	}

	@Override
	public void tick() {
		if (go) {
			State.setCurrentState(new BuildingState(g));
		}
	}

	@Override
	public boolean gridEnabled() {
		return gridded;
	}
}
