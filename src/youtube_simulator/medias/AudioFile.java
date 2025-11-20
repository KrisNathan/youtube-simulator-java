package youtube_simulator.medias;

import java.util.Scanner;

import youtube_simulator.Utils.TimeFormatter;
import youtube_simulator.interfaces.SkippableMedia;

public class AudioFile extends Media implements SkippableMedia {
	public AudioFile(Scanner sc, String title, String description, int duration) {
		super(sc, title, description, duration, 0);
	}

	@Override
	public void showInfo() {
		System.out.println(this.title);
		System.out.println(this.description);
		System.out.printf("Current play at %s/%s\n", TimeFormatter.formatMinutesSeconds(this.currentPlay),
				TimeFormatter.formatMinutesSeconds(duration));
	}

	@Override
	public void play() {
		addCurrentPlay(10);

		while (true) {			
			System.out.print("Currently Playing: ");
			showInfo();
			System.out.print("Choose (0: back|1: skip forward|2: skip backward): ");

			int choice;
			while (true) {
				try {
					choice = sc.nextInt();
					sc.nextLine();
					
					if (choice >= 0 && choice <= 2) {
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			switch (choice) {
			case 0:
				return;
			case 1:
				skipForward();
				break;
			case 2:
				skipBackward();
				break;
			}
		}
	}
	
	@Override
	public String getMediaType() {
		return "Audio";
	}

	@Override
	public void skipForward() {
		addCurrentPlay(5);
	}

	@Override
	public void skipBackward() {
		decreaseCurrentPlay(5);
	}
	
	@Override
	public int getCurrentPlay() {
		return this.currentPlay;
	}
}
