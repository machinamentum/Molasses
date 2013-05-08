/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

/**
 * For some odd reasons, the default java command for loading
 * jar files using a double-click on my machine doesn't allow
 * sound resources to be loaded. I added this simple Launcher
 * class to invoke the Start class properly.
 * @author joshuahuelsman
 */
public class Launcher {

	public static void main(String[] args) {
		File jarPath = null;
		try {
			jarPath = new File(Launcher.class.getProtectionDomain().getCodeSource()
					.getLocation().toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(new String[] { 
					System.getProperty("java.home") + File.separator + "bin"
							+ File.separator + "java", "-cp", jarPath.getAbsolutePath(), Start.class.getCanonicalName()
			});
			
			builder.redirectErrorStream(true);
			
			Process run = builder.start();
			
			InputStreamReader reader = new InputStreamReader(
					run.getInputStream());
			BufferedReader r = new BufferedReader(reader);
			String line;
			while ((line = r.readLine()) != null) {
				System.out.println(line);
			}
			
			run.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
	}

}
