package youtube_simulator.medias;

import java.util.Scanner;

public abstract class Media {
	protected String title;
	protected String description;
	protected int duration;
	protected int currentPlay;
	
	protected Scanner sc;
	
	public Media(Scanner sc, String title, String description, int duration, int currentPlay) {
		this.sc = sc;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.currentPlay = currentPlay;
	}
	
	public abstract void showInfo();
	
	public abstract void play();
	
	public abstract String getMediaType();
	
	// preserve invariant
	protected void addCurrentPlay(int amount) {
		if (currentPlay + amount > duration) {
			currentPlay = duration;
			return;
		}
		currentPlay += amount;
	}
	
	protected void decreaseCurrentPlay(int amount) {
		if (amount > currentPlay) {
			currentPlay = 0;
			return;
		}
		currentPlay -= amount;
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public String getTitle() {
		return this.title;
	}
}
