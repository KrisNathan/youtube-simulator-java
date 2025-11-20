package youtube_simulator;

/*
 * Honestly I think it makes more sense to just use abstract classes here.
 * Media (abstract) <- SkippableMedia (abstract)
 * SkippableMedia <- AudioFile
 * SkippableMedia <- VideoFile
 * Media <- LiveStream
 * 
 * But the specification requires the usage of interface..
 */

public class Main {

	public static void main(String[] args) {
		App app = new App();
		app.init();
	}

}
