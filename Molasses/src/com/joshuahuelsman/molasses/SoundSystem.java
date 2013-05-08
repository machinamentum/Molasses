/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundSystem {

	private static boolean init = false;

	public static void play(String ref) {
		play(SoundSystem.class.getResourceAsStream("/" + ref));
	}

	public static void play(InputStream stream) {
		BufferedInputStream buff = new BufferedInputStream(stream);
		try {
			AudioInputStream in = AudioSystem.getAudioInputStream(buff);
			AudioFormat format = in.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(in);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void init() {
		if (!init) {
			init = true;
			System.out.print("Initializing SoundSystem...");
			play(SoundSystem.class.getResourceAsStream("empty.wav"));
			System.out.println("done");
		}
	}

}
