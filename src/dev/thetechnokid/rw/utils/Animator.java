package dev.thetechnokid.rw.utils;

public class Animator {
	private long lastTime;
	private int duration;
	private Runnable r;
	private boolean stopped;
	
	public Animator(int duration, Runnable r) {
		this.duration = duration;
		lastTime = System.currentTimeMillis();
		this.r = r;
	}

	public void tick() {
		if(stopped) return;
		if (System.currentTimeMillis() - lastTime > duration) {
			r.run();
			lastTime = System.currentTimeMillis();
		}
	}
	
	public void stop() {
		stopped = true;
	}
}
