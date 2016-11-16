package dev.thetechnokid.rw.net;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Preferences implements java.io.Serializable {
	private static final long serialVersionUID = 9435437432L;

	private String theme;
	private Image pic;
	private String displayName;
	private ArrayList<User> friends;
	private String status;
	private String language;
	private boolean remembered;

	public Preferences(User user) {
		theme = "dark.css";
		pic = null;
		displayName = user.getName();
		friends = new ArrayList<>();
		status = "Hi";
		language = "en_US";
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Image getPic() {
		return pic;
	}

	public void setPic(Image pic) {
		this.pic = pic;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public boolean isRemembered() {
		return remembered;
	}

	public void setRemembered(boolean remembered) {
		this.remembered = remembered;
	}
}
