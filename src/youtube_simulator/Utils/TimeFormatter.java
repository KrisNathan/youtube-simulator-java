package youtube_simulator.Utils;

public class TimeFormatter {
	public static String formatMinutesSeconds(int durationSeconds) {
		int minutes = durationSeconds / 60;
		int seconds = durationSeconds % 60;
		
		return String.format("%02d:%02d", minutes, seconds);
	}
}
