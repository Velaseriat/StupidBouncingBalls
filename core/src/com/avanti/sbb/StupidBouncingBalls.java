package com.avanti.sbb;

import com.avanti.sbb.screens.SplashScreen;
import com.badlogic.gdx.Game;

public class StupidBouncingBalls extends Game {
	//keep app-wide constants here, but since we don't have any in this demo, there is nothing.
	//things like game level, game state, target frame rate, user prefs goes here

	@Override
	public void create () {
		setScreen(new SplashScreen(this));
	}
}
