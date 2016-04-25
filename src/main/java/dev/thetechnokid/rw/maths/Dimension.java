package dev.thetechnokid.rw.maths;

import java.io.Serializable;

public class Dimension implements Serializable{
	private static final long serialVersionUID = 1622431993408507008L;
	
	private int width, height;

	public Dimension() {
	}

	public Dimension(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
