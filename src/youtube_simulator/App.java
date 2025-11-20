package youtube_simulator;

import java.util.ArrayList;
import java.util.Scanner;

import youtube_simulator.Utils.TimeFormatter;
import youtube_simulator.interfaces.SkippableMedia;
import youtube_simulator.medias.AudioFile;
import youtube_simulator.medias.LiveStream;
import youtube_simulator.medias.Media;
import youtube_simulator.medias.VideoFile;

public class App {
	private Scanner sc;
	private boolean isRunning;
	private ArrayList<Media> medias;

	public App() {
		sc = new Scanner(System.in);
		isRunning = false;
		medias = new ArrayList<Media>();
	}

	public void init() {
		isRunning = true;
		while (isRunning) {
			System.out.print(
					"Youtube\n-----------------\n1. Add Media\n2. Play Media\n3. Watch History\n4. Exit\nChoose:");
			try {
				int input = sc.nextInt();
				sc.nextLine();

				switch (input) {
				case 1:
					addMedia();
					break;
				case 2:
					playMedia();
					break;
				case 3:
					watchHistory();
					break;
				case 4:
					isRunning = false;
					break;
				default:
					System.out.println("Invalid input! Try again!");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occurred! Try again!");
			}
		}

	}

	private void addMedia() {

		int mediaType = 0;
		while (true) {
			try {
				System.out.print("1. Add Audio File\n2. Add Video File\n3. Add Live Stream\n4. Back\nChoose: ");

				mediaType = sc.nextInt();
				sc.nextLine();

				if (mediaType >= 1 && mediaType <= 4) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (mediaType == 4) {
			return;
		}

		String title;
		while (true) {
			try {
				System.out.print("Title (3-30 characters): ");
				title = sc.nextLine();
				if (title.length() >= 3 && title.length() <= 30) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String desc;
		while (true) {
			try {
				System.out.print("Description (5-50 characters): ");

				desc = sc.nextLine();
				if (desc.length() >= 5 && desc.length() <= 50) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Media newMedia;
		if (mediaType == 3) {
			medias.add(new LiveStream(sc, title, desc));
		} else {
			int duration;
			while (true) {
				try {
					System.out.print("Duration (10-999): ");

					duration = sc.nextInt();
					sc.nextLine();
					if (duration >= 10 && duration <= 999) {
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (mediaType == 1) {
				medias.add(new AudioFile(sc, title, desc, duration));
			} else if (mediaType == 2) {
				medias.add(new VideoFile(sc, title, desc, duration));
			}
		}

		System.out.println("Media added!");
	}

	private void playMedia() {
		int i = 1;
		for (Media media : medias) {
			System.out.printf("[%d] %s: %s", i, media.getMediaType(), media.getTitle());

			if (media instanceof SkippableMedia) {
				System.out.printf(" (%s)", TimeFormatter.formatMinutesSeconds(media.getDuration()));
			}

			System.out.println();

			i++;
		}

		int idx;
		while (true) {
			try {
				System.out.print("Choose index: ");

				idx = sc.nextInt();
				sc.nextLine();

				if (idx <= medias.size() && idx >= 1) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Media m = medias.get(idx - 1);
		m.play();
	}

	private void watchHistory() {
		int i = 1;
		for (Media media : medias) {
			System.out.printf("[%d] %s: %s", i, media.getMediaType(), media.getTitle());

			if (media instanceof SkippableMedia) {
				System.out.printf(" (played at %s)",
						TimeFormatter.formatMinutesSeconds(((SkippableMedia) media).getCurrentPlay()));
			}

			System.out.println();

			i++;
		}

		int idx;
		while (true) {
			try {
				System.out.print("Index to delete (0: back): ");

				idx = sc.nextInt();
				sc.nextLine();

				if (idx <= medias.size()) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (idx == 0) {
			return;
		}

		medias.remove(idx - 1);
	}
}
