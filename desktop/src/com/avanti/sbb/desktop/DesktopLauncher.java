package com.avanti.sbb.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.avanti.sbb.StupidBouncingBalls;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = true;
		config.height = 1080;
		config.width = 1920;
		new LwjglApplication(new StupidBouncingBalls(), config);
	}
}
