package youtube_simulator.medias;

import java.util.Scanner;

public class LiveStream extends Media {
	public LiveStream(Scanner sc, String title, String description) {
		super(sc, title, description, 0, 0);
	}

	@Override
	public void showInfo() {
		System.out.println(this.title);
		System.out.println(this.description);
	}

	@Override
	public void play() {
		addCurrentPlay(10);

		System.out.print("Currently Playing: ");
		showInfo();
		System.out.print("Choose (0: back): ");

		int choice;
		while (true) {
			try {
				choice = sc.nextInt();
				sc.nextLine();

				if (choice == 0) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	

	@Override
	public String getMediaType() {
		return "LiveStream";
	}

}
