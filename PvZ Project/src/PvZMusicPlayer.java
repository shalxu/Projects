import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class PvZMusicPlayer implements Runnable{
	private File audioFile;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceLine;
	private byte[] audioData;
	
	public static void main(String[] args){
		PvZMusicPlayer player = new PvZMusicPlayer();
		player.run();
	}
	
	public void run(){
		audioFile = new File("graphics/music.wav");
		try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		audioFormat = audioStream.getFormat();
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		try {
			sourceLine = (SourceDataLine) AudioSystem.getLine(info);
			sourceLine.open();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		sourceLine.start();
		audioData = new byte[10000];
		int byteCount = 0;
		while(byteCount != -1){
			try {
				byteCount = audioStream.read(audioData, 0, audioData.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(byteCount > 0){
				sourceLine.write(audioData, 0, audioData.length);
			}
		}
		sourceLine.drain();
		sourceLine.close();
	}
}

